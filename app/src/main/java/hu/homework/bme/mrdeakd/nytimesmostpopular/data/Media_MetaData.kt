package hu.homework.bme.mrdeakd.nytimesmostpopular.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "media_metadataClass")
data class Media_MetaData(@PrimaryKey(autoGenerate = true) val id : Int?, val url: String?, val format: String?, val height: String?, val width: String?, val id_article : Int)