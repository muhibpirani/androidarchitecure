package com.sample.newsandroidarchitecture.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.sample.newsandroidarchitecture.model.Article
import com.sample.newsandroidarchitecture.model.Source

class SourceConverter {

    @TypeConverter
    fun fromTag(tag: Source): String? {
        return Gson().toJson(tag)
    }

    @TypeConverter
    fun toTag(tagString: String?): Source? {
        return Gson().fromJson(tagString, Source::class.java)
    }
}