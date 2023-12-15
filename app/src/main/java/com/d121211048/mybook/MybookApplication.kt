package com.d121211048.mybook

import android.app.Application
import com.d121211048.mybook.di.AppContainer
import com.d121211048.mybook.di.DefaultAppContainer

class MybookApplication: Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}