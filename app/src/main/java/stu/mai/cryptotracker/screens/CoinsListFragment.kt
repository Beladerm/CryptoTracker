package stu.mai.cryptotracker.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import stu.mai.cryptotracker.R
import stu.mai.cryptotracker.adapters.CoinInfoAdapter
import stu.mai.cryptotracker.contract.Navigator
import stu.mai.cryptotracker.databinding.FragmentPricelistBinding
import stu.mai.cryptotracker.pojo.CoinPriceInfo


class CoinsListFragment: Fragment(), Navigator {

    private lateinit var binding: FragmentPricelistBinding

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
        binding = FragmentPricelistBinding.inflate(inflater, container, false)
        val adapter = CoinInfoAdapter(requireContext())
        binding.rvCoinPriceList.adapter = adapter
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                launchNext(coinPriceInfo.fromSymbol)
            }

        }
        with(viewModel) {
            getCoinPriceList().observe(viewLifecycleOwner) {
                adapter.coinInfoList = it
            }
        }

        return binding.root
    }

    override fun launchNext(fsym: String) {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, createInfoFragment(fsym))
            .addToBackStack(null)
            .commit()

    }


    private fun createInfoFragment(coinSymbol:String): Fragment {
        return InfoFragment.newInstance(coinSymbol)
    }
    companion object {
        @JvmStatic
        fun newInstance(): CoinsListFragment {
            return CoinsListFragment()
        }
    }

}