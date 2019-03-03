package hu.homework.bme.mrdeakd.nytimesmostpopular.data

import com.google.gson.annotations.SerializedName

data class Media(val type: String?, val subtype: String?, val caption: String?, val copyright: String?, val approved_for_syndication: Number?,@SerializedName("media-metadata") val media_metadata: List<MediaMeta>?)