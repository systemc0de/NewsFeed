package com.example.newsfeed.feature.mainscreen.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsfeed.base.openUrl
import com.example.newsfeed.databinding.FragmentMainscreenBinding
import com.example.newsfeed.feature.mainscreen.ui.adapter.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainScreenFragment : Fragment() {
    private var _binding: FragmentMainscreenBinding? = null
    private val binding get() = _binding!!

    private val mainScreenViewModel: MainScreenViewModel by viewModel<MainScreenViewModel>()
    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter(
            news = listOf(),
            onBookmarkClick = mainScreenViewModel::onBookmarkClick,
            onItemClick = { article ->
                mainScreenViewModel.processUiEvent(OpenArticleEvent.OnArticleClick(article))
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainscreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvArticles.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }

        binding.search.setOnClickListener {
            mainScreenViewModel.processUiEvent(UIEvent.OnSearchClicked)
        }

        binding.searchField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mainScreenViewModel.processUiEvent(UIEvent.OnSearchTextInput(p0.toString()))
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        mainScreenViewModel.viewState.observe(viewLifecycleOwner, ::render)
        mainScreenViewModel.singleEvent.observe(viewLifecycleOwner, ::openArticle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(viewState: ViewState) {
        updateProgressBar(viewState)
        updateErrorText(viewState)
        showResults(viewState)
        binding.searchFieldContainer.isGone = !viewState.isSearchVisible

    }

    private fun updateProgressBar(viewState: ViewState) {
        binding.newsProgressBar.isGone = !viewState.isLoading
    }

    private fun updateErrorText(viewState: ViewState) {
        binding.errorTextView.apply {
            text = viewState.errorMessage
            //isVisible = viewState.isInErrorState
        }
    }

    private fun updateList(viewState: ViewState) {
        newsAdapter.updateArticles(viewState.articles)
    }

    private fun openArticle(event: OpenArticleEvent.OnArticleClick) {
            openUrl(requireActivity(), event.article.url)
    }

    private fun showResults(viewState: ViewState) {
        if (viewState.isSearchVisible) {
            newsAdapter.updateArticles(viewState.searchResult)
        } else {
            newsAdapter.updateArticles(viewState.articles)
        }
    }
}