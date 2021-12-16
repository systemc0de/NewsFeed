package com.example.newsfeed.feature.mainscreen.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsfeed.R
import com.example.newsfeed.base.formatDate
import com.example.newsfeed.base.setThrottledClickListener
import com.example.newsfeed.databinding.ArticlesListItemBinding
import com.example.newsfeed.feature.mainscreen.domain.model.NewsDomainModel

class NewsAdapter(
    private var news: List<NewsDomainModel>,
    private val onItemClick: (article: NewsDomainModel) -> Unit,
    private val onBookmarkClick: (article: NewsDomainModel) -> Unit
) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val articlesBinding =
            ArticlesListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )

        return ViewHolder(articlesBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = news[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int = news.size

    inner class ViewHolder(private val articlesBinding: ArticlesListItemBinding) :
        RecyclerView.ViewHolder(articlesBinding.root) {
        fun bind(article: NewsDomainModel) {
            articlesBinding.apply {
                val url = if (article.urlToImage != null) "${article.urlToImage}?w=360" else null
                Glide.with(itemView)
                    .load(url)
                    .centerCrop()
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_non_existing_url)
                    .fallback(R.drawable.ic_placeholder)
                    .into(ivArticlePhoto)

                tvArticleTitle.text = article.title
                tvArticleDescription.text = article.description ?: ""
                tvArticlePublishedAt.text = formatDate(article.publishedAt)
            }

            articlesBinding.root.setThrottledClickListener  {
                onItemClick(article)
            }

            articlesBinding.bookmarkIcon.apply {
                if (article.isBookmarked) {
                    setButtonDrawable(R.drawable.ic_favourite_filled_24dp)
                } else {
                    setButtonDrawable(R.drawable.ic_favourite_outlined_24dp)
                }
            }

            articlesBinding.bookmarkIcon.apply {
                setThrottledClickListener {
                    onBookmarkClick(article)
                }
            }
        }
    }

    fun updateArticles(newArticles: List<NewsDomainModel>) {
        news = newArticles
        notifyDataSetChanged()
    }
}