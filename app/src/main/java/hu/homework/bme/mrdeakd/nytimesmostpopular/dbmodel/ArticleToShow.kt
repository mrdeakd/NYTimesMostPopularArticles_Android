package hu.homework.bme.mrdeakd.nytimesmostpopular.dbmodel

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "articleClass")
data class ArticleToShow(var url: String,
                         var byline: String,
                         var title: String,
                         var published_date: String,
                         @PrimaryKey var id: Int,
                         var smallpic : String,
                         var largepic : String)