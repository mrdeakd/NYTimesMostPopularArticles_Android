package hu.homework.bme.mrdeakd.nytimesmostpopular.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import hu.homework.bme.mrdeakd.nytimesmostpopular.repository.DatabaseRepository
import hu.homework.bme.mrdeakd.nytimesmostpopular.dbmodel.ArticleToShow
import hu.homework.bme.mrdeakd.nytimesmostpopular.repository.WebServiceRepository

class ArticleViewModel(application: Application) : AndroidViewModel(application), ViewModelCallback {

    private val dbRepository: DatabaseRepository = DatabaseRepository(application)
    private val webserviceRepository: WebServiceRepository = WebServiceRepository(application, this)
    internal var allArticles: LiveData<List<ArticleToShow>>

    init {
        allArticles = dbRepository.getAllArticle()
    }

    override fun getAllArticles() : LiveData<List<ArticleToShow>>{
        return allArticles
    }

    override fun insertArticle(article: ArticleToShow) {
        dbRepository.insertArticle(article)
    }

    override fun deleteAllArticles() {
        dbRepository.deleteAllArticles()
    }

    fun getNewArticles(){
        webserviceRepository.providesWebService()
    }
}