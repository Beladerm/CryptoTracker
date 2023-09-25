package stu.mai.cryptotracker.screens

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import stu.mai.cryptotracker.R
import stu.mai.cryptotracker.contract.Navigator
import stu.mai.cryptotracker.databinding.FragmentPricelistBinding

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

        with(viewModel) {
            getCoinPriceList().observe(viewLifecycleOwner) {
                TODO()
            }
        }

        binding = FragmentPricelistBinding.inflate(inflater, container, false)
        with(binding) {

            return root
        }
    }
    override fun launchNext() {
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction()
            .replace(R.id.fragment_container, createInfoFragment())
            .addToBackStack(null)
            .commit()
    }


    private fun createInfoFragment(): Fragment {
        return InfoFragment.newInstance()
    }
    companion object {
        @JvmStatic
        fun newInstance(): CoinsListFragment {
            return CoinsListFragment()
        }
    }

}