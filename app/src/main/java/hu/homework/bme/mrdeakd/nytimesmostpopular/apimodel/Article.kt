package hu.homework.bme.mrdeakd.nytimesmostpopular.apimodel

data class Article(var url: String,
                   var byline: String,
                   var title: String,
                   var published_date: String,
                   var id: Number,
                   val media : List<Media>)