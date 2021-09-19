package com.sample.newsandroidarchitecture.model

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.sample.newsandroidarchitecture.model.Article.Companion.TABLE_NAME
import com.sample.newsandroidarchitecture.utils.loadImg
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @SerializedName("source")
    var source: Source? = null,

    @SerializedName("author")
    var author: String? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("urlToImage")
    var urlToImage: String? = null,

    @SerializedName("publishedAt")
    var publishedAt: String? = null,

    @SerializedName("content")
    var content: String? = null
) : Parcelable {
    companion object {
        const val TABLE_NAME = "articles"

        @JvmStatic
        @BindingAdapter("android:glideImage")
        fun loadProfileImage(imageView: ImageView, url: String?) {
            imageView.loadImg(url)
        }

    }
}
