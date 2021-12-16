package com.example.newsfeed

import android.app.Application
import com.example.newsfeed.di.appModule
import com.example.newsfeed.di.databaseModule
import com.example.newsfeed.feature.bookmarksscreen.di.bookmarksModule
import com.example.newsfeed.feature.mainscreen.di.mainScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class NewsFeedApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@NewsFeedApp)
            modules(appModule, mainScreenModule, databaseModule, bookmarksModule)
        }
    }
}