package hu.homework.bme.mrdeakd.nytimesmostpopular.model

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import hu.homework.bme.mrdeakd.nytimesmostpopular.database.MyRepository
import hu.homework.bme.mrdeakd.nytimesmostpopular.data.ArticleToShow

class ItemsViewModel(application: Application) : AndroidViewModel(application) {

    private val myRepository: MyRepository = MyRepository(application)
    internal var allArticles: LiveData<List<ArticleToShow>>

    init {
        allArticles = myRepository.getAllArticle()
    }

    fun insertArticle(article: ArticleToShow) {
        myRepository.insertArticle(article)
    }

    fun deleteAllArticles() {
        myRepository.deleteAllArticles()
    }

    fun destoryINSTANCE(){
        myRepository.destroyINSTANCE()
    }
}