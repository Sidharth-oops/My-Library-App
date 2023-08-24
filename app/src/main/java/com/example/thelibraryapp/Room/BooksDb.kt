package com.example.thelibraryapp.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BooksDb:RoomDatabase() {
    abstract fun bookDao(): BookDao

    companion object{
        @Volatile
        private  var INSTANCE: BooksDb?  = null
        fun getInstance(context:Context): BooksDb {
            synchronized(this){
                var instance= INSTANCE;
                if(instance==null){
                    instance=Room.databaseBuilder(
                        context.applicationContext,
                        BooksDb::class.java,
                        "books_db"
                    ).build()

                }
                return instance;
            }


        }

    }
}