package com.example.admin.thebestapp.app

import android.app.Application
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

class App: Application()
{
    companion object
    {
        lateinit var instance: App
            private set
    }
    
    val componentHolder: ComponentsHolder by lazy {
        ComponentsHolder(this).init()
    }
    
    override fun onCreate()
    {
        super.onCreate()
        instance = this
        initStetho()
        initTimberWithCrashlitics()
        initLeakCanary()
    }
    
    private fun initTimberWithCrashlitics()
    {
        Timber.plant(Timber.DebugTree())
    }
    
    private fun initStetho()
    {
        Stetho.initializeWithDefaults(this)
    }
    
    private fun initLeakCanary()
    {
        if(LeakCanary.isInAnalyzerProcess(this))
        {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
        // Normal app init code...
    }
}