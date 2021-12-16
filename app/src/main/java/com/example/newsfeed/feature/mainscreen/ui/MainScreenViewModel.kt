package com.example.newsfeed.feature.mainscreen.ui

import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.newsfeed.base.BaseViewModel
import com.example.newsfeed.base.Event
import com.example.newsfeed.base.mapToList
import com.example.newsfeed.base.utils.SingleLiveEvent
import com.example.newsfeed.feature.bookmarksscreen.domain.BookmarksInteractor
import com.example.newsfeed.feature.mainscreen.domain.MainScreenNewsInteractor
import com.example.newsfeed.feature.mainscreen.domain.model.NewsDomainModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val newsInteractor: MainScreenNewsInteractor,
    private val bookmarksInteractor: BookmarksInteractor
) :
    BaseViewModel<ViewState>() {
    val singleEvent = SingleLiveEvent<OpenArticleEvent.OnArticleClick>()

    init {
        viewModelScope.launch {
            bookmarksInteractor.subscribeByDesc().asFlow().collect {
                processUiEvent(UIEvent.OnBookmarksFetched(articles = it))
            }
        }
        processUiEvent(UIEvent.GetCurrentNews)
    }

    override fun initialViewState(): ViewState {
        return ViewState(
            articles = listOf(),
            searchResult = listOf(),
            article = null,
            errorMessage = null,
            isLoading = false,
            isSearchVisible = false,
            searchText = ""
        )
    }

    override suspend fun reduce(event: Event, previousState: ViewState): ViewState? {
        when (event) {
            is UIEvent.GetCurrentNews -> {
                processDataEvent(DataEvent.OnDataLoad)
                newsInteractor.fetchNews().fold(
                    onError = {
                        processDataEvent(DataEvent.ErrorNewsRequest(it.localizedMessage ?: ""))
                    },
                    onSuccess = {
                        processDataEvent(DataEvent.SuccessNewsRequest(it))
                    }
                )
            }
            is UIEvent.OnBookmarkClick -> {
                bookmarksInteractor.create(event.article.copy(isBookmarked = true))
            }
            is UIEvent.OnBookmarksFetched -> {
                val oldArticles = previousState.articles
                val newArticles = event.articles

                val articles = mapToList(oldList = oldArticles, newList = newArticles)
                return previousState.copy(articles = articles)
            }
            is UIEvent.OnSearchClicked -> {
                return previousState.copy(isSearchVisible = !previousState.isSearchVisible)
            }
            is UIEvent.OnSearchTextInput -> {
                val results = previousState.articles.filter { article ->
                    article.title.contains(event.searchText)
                }

                return previousState.copy(searchResult = results)
            }
            is DataEvent.OnDataLoad -> {
                return previousState.copy(isLoading = true)
            }
            is DataEvent.SuccessNewsRequest -> {
                return previousState.copy(
                    articles = event.articles,
                    errorMessage = null,
                    isLoading = false
                )
            }
            is DataEvent.ErrorNewsRequest -> {
                return previousState.copy(
                    isLoading = false,
                    errorMessage = event.errorMessage
                )
            }
            is OpenArticleEvent.OnArticleClick -> {
                singleEvent.value = event
            }
        }
        return null
    }

    fun onBookmarkClick(article: NewsDomainModel) {
        processUiEvent(UIEvent.OnBookmarkClick(article))
    }
}