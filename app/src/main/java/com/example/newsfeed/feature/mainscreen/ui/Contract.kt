package com.example.newsfeed.feature.mainscreen.ui

import com.example.newsfeed.base.Event
import com.example.newsfeed.feature.mainscreen.domain.model.NewsDomainModel

data class ViewState(
    val articles: List<NewsDomainModel>,
    val searchResult: List<NewsDomainModel>,
    val article: NewsDomainModel?,
    val errorMessage: String?,
    val isLoading: Boolean,
    val isSearchVisible: Boolean,
    val searchText: String
) {
    val isInErrorState: Boolean = errorMessage != null
}


sealed class UIEvent : Event {
    object GetCurrentNews : UIEvent()
    object OnSearchClicked : UIEvent()
    data class OnSearchTextInput(val searchText: String) : UIEvent()
    data class OnBookmarksFetched(val articles: List<NewsDomainModel>) : UIEvent()
    data class OnBookmarkClick(val article: NewsDomainModel) : UIEvent()
}

sealed class DataEvent : Event {
    object OnDataLoad : DataEvent()
    data class SuccessNewsRequest(
        val articles: List<NewsDomainModel>
    ) : DataEvent()
    data class ErrorNewsRequest(val errorMessage: String) : DataEvent()
}

sealed class OpenArticleEvent : Event {
    data class OnArticleClick(val article: NewsDomainModel) : UIEvent()
}