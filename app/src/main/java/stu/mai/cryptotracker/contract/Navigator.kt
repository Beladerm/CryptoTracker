package stu.mai.cryptotracker.contract

import androidx.fragment.app.Fragment

interface Navigator {
    fun launchNext()
}

fun Fragment.navigator(): Navigator {
    return requireActivity() as Navigator
}