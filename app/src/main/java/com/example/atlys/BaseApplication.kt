package com.example.atlys

import android.app.Application
import com.example.atlys.entrypoints.GsonEntryPoint
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication: Application() {

    companion object {
        lateinit var gsonProvider: GsonEntryPoint
    }

    override fun onCreate() {
        super.onCreate()

        gsonProvider = EntryPointAccessors.fromApplication(applicationContext, GsonEntryPoint::class.java)
    }
}