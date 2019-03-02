package hu.homework.bme.mrdeakd.nytimesmostpopular.database


import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import hu.homework.bme.mrdeakd.nytimesmostpopular.R
import hu.homework.bme.mrdeakd.nytimesmostpopular.data.ArticleToShow

@Database(entities = arrayOf(ArticleToShow::class), version = 5)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun articleDataDao(): DatabaseDao

    companion object {
        private var INSTANCE: MyRoomDatabase? = null

        fun getInstance(context: Context): MyRoomDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, MyRoomDatabase::class.java, context.getString(
                                    R.string.db)).fallbackToDestructiveMigration().build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}