package hu.homework.bme.mrdeakd.nytimesmostpopular.data

data class Response(val copyright : String, val num_results : Int, val results : Array<Article>, val status : String)