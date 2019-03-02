package hu.homework.bme.mrdeakd.nytimesmostpopular.data

data class Article(var url: String?, var adx_keywords: String?, var column: String?, var section: String?, var byline: String?, var type: String?, var title: String?,
                   var abstract: String?, var published_date: String?, var source: String?, var id: Number?, var asset_id: Number?, var views: Number?, val media : Array<Media>)