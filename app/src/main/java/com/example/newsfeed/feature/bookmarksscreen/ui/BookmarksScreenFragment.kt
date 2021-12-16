package com.example.newsfeed.feature.bookmarksscreen.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsfeed.base.openUrl
import com.example.newsfeed.databinding.FragmentBookmarksScreenBinding
import com.example.newsfeed.feature.mainscreen.ui.adapter.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarksScreenFragment : Fragment() {
    private var _binding: FragmentBookmarksScreenBinding? = null
    private val binding get() = _binding!!

    private val bookmarksScreenViewModel by viewModel<BookmarksScreenViewModel>()
    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter(
            news = listOf(),
            onBookmarkClick = bookmarksScreenViewModel::onBookmarkClick,
            onItemClick = { article ->
                bookmarksScreenViewModel.processUiEvent(OpenArticleEvent.OnArticleClick(article))
            }
        )
    }

    companion object {
        fun newInstance(): BookmarksScreenFragment {
            return BookmarksScreenFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookmarksScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvArticles.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
        }

        bookmarksScreenViewModel.viewState.observe(viewLifecycleOwner, ::render)
        bookmarksScreenViewModel.singleEvent.observe(viewLifecycleOwner, ::singleEvent)
    }

    private fun render(viewState: BookmarksViewState) {
        val articles = viewState.articles.sortedByDescending { it.publishedAt }
        newsAdapter.updateArticles(articles)
    }

    private fun singleEvent(event: OpenArticleEvent.OnArticleClick) {
        openUrl(requireContext(), event.article.url)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}