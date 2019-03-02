package hu.homework.bme.mrdeakd.nytimesmostpopular.database

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import hu.homework.bme.mrdeakd.nytimesmostpopular.data.ArticleToShow

class MyRepository(application: Application) {

    private val dbDao: DatabaseDao
    private val listLiveDataArticle: LiveData<List<ArticleToShow>>

    init {
        val articleRoomDatabase = MyRoomDatabase.getInstance(application)
        dbDao = articleRoomDatabase!!.articleDataDao()
        listLiveDataArticle = dbDao.getAllArticle()
    }

    fun getAllArticle(): LiveData<List<ArticleToShow>> {
        return listLiveDataArticle
    }

    fun insertArticle(art: ArticleToShow) {
        insertArticleAsyncTask(dbDao).execute(art)
    }

    fun deleteAllArticles() {
        deleteArticleAsyncTask(dbDao).execute()
    }

    fun destroyINSTANCE(){
        MyRoomDatabase.destroyInstance()
    }

    private class insertArticleAsyncTask internal constructor(private val mAsyncTaskDao: DatabaseDao) : AsyncTask<ArticleToShow, Void, Void>() {
        override fun doInBackground(vararg params: ArticleToShow): Void? {
            mAsyncTaskDao.insertArticle(params)
            return null
        }
    }

    private class deleteArticleAsyncTask internal constructor(private val mAsyncTaskDao: DatabaseDao) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg params: Void?): Void? {
            mAsyncTaskDao.deleteAllArticles()
            return null
        }
    }

}