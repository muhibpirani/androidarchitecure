package com.sample.newsandroidarchitecture.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.sample.newsandroidarchitecture.R
import com.sample.newsandroidarchitecture.databinding.ItemArticleBinding
import com.sample.newsandroidarchitecture.model.Article
import com.sample.newsandroidarchitecture.utils.ItemClickListener

class ArticlesAdapter(
    private val articles: List<Article>,
    private val itemClickListener: ItemClickListener<Article>
) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.itemBinding.articleContainer.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.item_slide_bottom)
        holder.itemBinding.imgArticle.transitionName = article.id.toString()
        holder.itemBinding.articleContainer.setOnClickListener {
            itemClickListener.onClick(holder.itemBinding.imgArticle, articles, position)
        }
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ViewHolder(val itemBinding: ItemArticleBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(article: Article) {
            itemBinding.article = article
            itemBinding.executePendingBindings()
        }

    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.itemBinding.articleContainer.clearAnimation()
    }
}