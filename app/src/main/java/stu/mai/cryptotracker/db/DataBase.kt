package stu.mai.cryptotracker.db

import android.app.Application

object DataBase {
    private lateinit var application: Application
    fun init(application: Application) {
        this.application = application
    }
    val db by lazy { AppDatabase.getInstance(application) }
    // TODO исправить by lazy, без него краш, так как application = null

}