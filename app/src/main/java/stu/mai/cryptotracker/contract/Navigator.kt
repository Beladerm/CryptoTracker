package stu.mai.cryptotracker.contract

import androidx.fragment.app.Fragment

interface Navigator {
    fun launchNext(fsym:String)
}

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}