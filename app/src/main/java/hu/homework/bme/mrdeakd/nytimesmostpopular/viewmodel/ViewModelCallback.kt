package hu.homework.bme.mrdeakd.nytimesmostpopular.viewmodel

import android.arch.lifecycle.LiveData
import hu.homework.bme.mrdeakd.nytimesmostpopular.dbmodel.ArticleToShow

interface ViewModelCallback {
    fun insertArticle(article: ArticleToShow)
    fun deleteAllArticles()
    fun getAllArticles() : LiveData<List<ArticleToShow>>
}