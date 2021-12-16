package com.example.newsfeed.feature.mainscreen.di

import com.example.newsfeed.data.api.NewsApi
import com.example.newsfeed.data.api.NewsRemoteSource
import com.example.newsfeed.data.api.NewsRepository
import com.example.newsfeed.data.api.NewsRepositoryImpl
import com.example.newsfeed.feature.bookmarksscreen.data.BookmarksRepository
import com.example.newsfeed.feature.bookmarksscreen.domain.BookmarksInteractor
import com.example.newsfeed.feature.mainscreen.domain.MainScreenNewsInteractor
import com.example.newsfeed.feature.mainscreen.ui.MainScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainScreenModule = module {
    viewModel<MainScreenViewModel>{
        MainScreenViewModel(get<MainScreenNewsInteractor>(), get<BookmarksInteractor>())
    }

    single<NewsRemoteSource> {
        NewsRemoteSource(get<NewsApi>())
    }

    single<NewsRepository> {
        NewsRepositoryImpl(get<NewsRemoteSource>())
    }

    single<MainScreenNewsInteractor> {
        MainScreenNewsInteractor(get<NewsRepository>(), get<BookmarksRepository>())
    }
}