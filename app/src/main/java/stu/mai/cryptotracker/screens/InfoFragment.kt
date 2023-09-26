package stu.mai.cryptotracker.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import stu.mai.cryptotracker.contract.Navigator
import stu.mai.cryptotracker.databinding.FragmentCoinInfoBinding

class InfoFragment: Fragment(),Navigator {

    private var coinSymbol: String? = null
    private lateinit var binding: FragmentCoinInfoBinding
    private lateinit var viewModel: CoinViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        arguments?.let {
             coinSymbol = it.getString("COIN_SYMBOL") }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinInfoBinding.inflate(inflater, container, false)
        with(viewModel) {
            coinSymbol?.let { it ->
                getDetailInfo(it).observe(viewLifecycleOwner) {
                    Log.d("CoinsListFragment", "onCreateView: $it")
                    with(binding) {
                        tvPrice.text=it.price
                        tvMinPrice.text=it.low24Hour
                        tvMaxPrice.text=it.high24Hour
                        tvLastMarket.text=it.lastMarket
                        tvLastUpdate.text=it.getFormattedTime()
                        tvFromSymbol.text=it.fromSymbol
                        tvToSymbol.text=it.toSymbol
                        Picasso.get().load(it.getFullImageUrl()).into(ivLogoCoin)
                    }
                }
            }
        }


        return binding.root
    }
    override fun launchNext(fsym:String) {
       // TODO("Not yet implemented")
    }



    companion object {
        @JvmStatic
        fun newInstance(coinSymbol: String): InfoFragment {
            val fragment = InfoFragment()
            val args = Bundle()
            args.putString("COIN_SYMBOL", coinSymbol)
            fragment.arguments = args
            return fragment
        }
    }

}