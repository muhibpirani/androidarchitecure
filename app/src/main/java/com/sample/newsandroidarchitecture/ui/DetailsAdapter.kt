package com.sample.newsandroidarchitecture.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.newsandroidarchitecture.databinding.ItemCarouselBinding
import com.sample.newsandroidarchitecture.databinding.ItemDetailsWithCarouselBinding
import com.sample.newsandroidarchitecture.model.Article
import com.sample.newsandroidarchitecture.utils.ItemClickListener
import kotlin.math.roundToInt

class DetailsAdapter (
    private val articles: List<Article>,
    private val itemClickListener: ItemClickListener<Article>
) : RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    private var hasInitParentDimensions = false
    private var maxImageWidth: Int = 0
    private var maxImageHeight: Int = 0
    private var maxImageAspectRatio: Float = 1f


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemDetailsWithCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        if (!hasInitParentDimensions) {
            maxImageWidth = (parent.width * 0.75f).roundToInt()
            maxImageHeight = parent.height
            maxImageAspectRatio = maxImageWidth.toFloat() / maxImageHeight.toFloat()
            hasInitParentDimensions = true
        }
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articles[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ViewHolder(val itemBinding: ItemDetailsWithCarouselBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(article: Article) {
            itemBinding.article = article
            itemBinding.executePendingBindings()
        }

    }
}