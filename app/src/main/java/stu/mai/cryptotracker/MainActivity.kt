package stu.mai.cryptotracker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import stu.mai.cryptotracker.screens.CoinViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this) {
            Log.d("TEST SYSTEM", "Success $it")
        }
        viewModel.getDetailInfo("BTC").observe(this) {
            Log.d("TEST SYSTEM", "Success $it")
        }



    }


}
