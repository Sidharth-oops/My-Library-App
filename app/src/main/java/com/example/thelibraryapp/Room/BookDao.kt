package com.example.thelibraryapp.Room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert
    suspend fun addBook(entity: BookEntity)
    @Query("SELECT * FROM BookEntity")
    fun getAllBooks():Flow<List<BookEntity>>

    @Delete
     suspend fun deleteBook(bookEntity: BookEntity)
     @Update
     suspend fun updateBook(bookEntity: BookEntity)



}