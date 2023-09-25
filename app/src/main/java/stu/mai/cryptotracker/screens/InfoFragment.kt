package stu.mai.cryptotracker.screens

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import stu.mai.cryptotracker.contract.Navigator
import stu.mai.cryptotracker.databinding.FragmentCoinInfoBinding

class InfoFragment: Fragment(),Navigator {

    private lateinit var binding: FragmentCoinInfoBinding
    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        with(viewModel) {
            getCoinPriceList().observe(viewLifecycleOwner) {
                Log.d("CoinsListFragment", "onCreateView: $it")
            }
        }

        binding = FragmentCoinInfoBinding.inflate(inflater, container, false)
        with(binding) {
            textView2.text = "InfoFragment" //TODO remove
            return root
        }
    }
    override fun launchNext() {
        TODO("Not yet implemented")
    }



    companion object {
        @JvmStatic
        fun newInstance(): InfoFragment {
            return InfoFragment()
        }
    }

}