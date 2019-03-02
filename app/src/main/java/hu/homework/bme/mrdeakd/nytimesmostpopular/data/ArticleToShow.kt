package hu.homework.bme.mrdeakd.nytimesmostpopular.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "articleClass")
data class ArticleToShow(var url: String?, var adx_keywords: String?, var column: String?, var section: String?, var byline: String?, var type: String?, var title: String?,
                         var abstract: String?, var published_date: String?, var source: String?, @PrimaryKey var id: Int?, var asset_id: Int?, var views: Int?, var smallpic : String?, var largepic : String?){
    constructor() : this("","","","","","","","","","",null,null,null,"","")
}