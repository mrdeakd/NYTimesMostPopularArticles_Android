package hu.homework.bme.mrdeakd.nytimesmostpopular.apimodel

import com.google.gson.annotations.SerializedName

data class Media(@SerializedName("media-metadata") val media_metadata: List<MediaMeta>)