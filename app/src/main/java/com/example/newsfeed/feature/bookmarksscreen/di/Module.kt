package com.example.newsfeed.feature.bookmarksscreen.di

import com.example.newsfeed.feature.bookmarksscreen.data.BookmarksRepository
import com.example.newsfeed.feature.bookmarksscreen.data.BookmarksRepoImpl
import com.example.newsfeed.feature.bookmarksscreen.data.local.BookmarkDAO
import com.example.newsfeed.feature.bookmarksscreen.domain.BookmarksInteractor
import com.example.newsfeed.feature.bookmarksscreen.ui.BookmarksScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookmarksModule = module {
    single<BookmarksRepository> {
        BookmarksRepoImpl(get<BookmarkDAO>())
    }

    single<BookmarksInteractor> {
        BookmarksInteractor(get<BookmarksRepository>())
    }

    viewModel<BookmarksScreenViewModel> {
        BookmarksScreenViewModel(get<BookmarksInteractor>())
    }
}