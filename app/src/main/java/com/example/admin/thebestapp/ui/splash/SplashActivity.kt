package com.example.admin.thebestapp.ui.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.admin.thebestapp.R
import com.example.admin.thebestapp.app.extensions.ProgressIndicator
import com.example.admin.thebestapp.ui.movie.MovieActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SplashActivity: AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        
        startSplash()
    }
    
    private fun startSplash()
    {
        Observable.just("")//
                .doOnNext { ProgressIndicator(this).setProgressDialog(true, window) }
                .delay(2000, TimeUnit.MILLISECONDS)//
                .observeOn(AndroidSchedulers.mainThread())//
                .doOnNext { ProgressIndicator(this).setProgressDialog(false, window) }
                .subscribe({ goToMovieActivity() })
    }
    
    private fun goToMovieActivity()
    {
        startActivity(Intent(this, MovieActivity::class.java))
    }
}
