package stu.mai.cryptotracker.screens

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import stu.mai.cryptotracker.api.ApiFactory
import stu.mai.cryptotracker.db.AppDatabase
import stu.mai.cryptotracker.db.DataBase
import stu.mai.cryptotracker.pojo.CoinPriceInfo
import stu.mai.cryptotracker.pojo.CoinPriceInfoRawData
import java.util.concurrent.TimeUnit

class CoinViewModel : ViewModel() {
    init { loadData() }

    private val compositeDisposable = CompositeDisposable()
    private val database = DataBase.db

    fun getCoinPriceList(): LiveData<List<CoinPriceInfo>> =
        database.coinPriceInfoDao().getPriceList()

    fun getDetailInfo(fSym: String): LiveData<CoinPriceInfo> =
        database.coinPriceInfoDao().getPriceInfoAboutCoin(fSym)

    private fun loadData() {
        val disposable = ApiFactory.apiService.getTopCoinsInfo(limit = 30)
            .map { it.data?.map { it.coinInfo?.name }
                ?.joinToString(",") ?: "Error CoinViewModel" }
            .flatMap { ApiFactory.apiService.getFullPriceList(fSyms = it) }
            .map { getPriceListFromRawData(it) }
            .delaySubscription(1, TimeUnit.MINUTES)
            .repeat()
            .retry()
            .subscribeOn(Schedulers.io())
            .subscribe({
                database.coinPriceInfoDao().insertPriceList(it)
            }, {
                Log.e("TEST SYSTEM", it.message.toString())
            })
        //compositeDisposable.add(disposable)
    }

    private fun getPriceListFromRawData(
        coinPriceInfoRawData: CoinPriceInfoRawData
    ): List<CoinPriceInfo> {
        val result = ArrayList<CoinPriceInfo>()
        val jsonObject = coinPriceInfoRawData.coinPriceInfoJsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currencyJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currencyJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currencyJson.getAsJsonObject(currencyKey),
                    CoinPriceInfo::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}