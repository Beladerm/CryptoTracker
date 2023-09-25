package stu.mai.cryptotracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import stu.mai.cryptotracker.databinding.ActivityMainBinding
import stu.mai.cryptotracker.db.DataBase
import stu.mai.cryptotracker.screens.CoinViewModel
import stu.mai.cryptotracker.screens.CoinsListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBase.init(application)
        binding = ActivityMainBinding.inflate(layoutInflater).also {setContentView(it.root)}
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, createCoinListFragment())
                .commit()
        }

    }
    private fun createCoinListFragment(): Fragment {
        return CoinsListFragment.newInstance()
    }

}
