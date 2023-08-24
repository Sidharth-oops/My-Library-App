package com.example.thelibraryapp.repository


import android.util.Log
import com.example.thelibraryapp.Room.BookEntity
import com.example.thelibraryapp.Room.BooksDb

class Repository(private val bookDb: BooksDb) {
    suspend fun addBookToTheRoom(bookEntity: BookEntity) {


        bookDb.bookDao().addBook(bookEntity)
    }

    fun getAllBooks() = bookDb.bookDao().getAllBooks()
    suspend fun  deleteBookFromTheRoom(bookEntity: BookEntity){
        bookDb.bookDao().deleteBook(bookEntity)
    }
    suspend fun  updateBookInRoom(bookEntity: BookEntity){
        bookDb.bookDao().updateBook(bookEntity)
    }

}