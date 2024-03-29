package hu.homework.bme.mrdeakd.nytimesmostpopular.database


import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import hu.homework.bme.mrdeakd.nytimesmostpopular.dbmodel.ArticleToShow

@Dao
interface DatabaseDao {
    @Query("DELETE FROM articleClass")
    fun deleteAllArticles()

    @Query("SELECT * FROM articleClass")
    fun getAllArticle() : LiveData<List<ArticleToShow>>

    @Insert
    fun insertArticle(art: Array<out ArticleToShow>)
}