package com.sample.newsandroidarchitecture.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.newsandroidarchitecture.databinding.ItemCarouselBinding
import com.sample.newsandroidarchitecture.model.Article
import com.sample.newsandroidarchitecture.utils.ItemClickListener

class CarouselAdapter(
    private val articles: List<Article>,
    private val itemClickListener: ItemClickListener<Article>
) : RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
         holder.itemBinding.cardCarouselContainer.setOnClickListener {
             itemClickListener.onClick(holder.itemBinding.imgArticle, articles,position)
         }
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ViewHolder(val itemBinding: ItemCarouselBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(article: Article) {
            itemBinding.article = article
            itemBinding.executePendingBindings()
        }

    }
}