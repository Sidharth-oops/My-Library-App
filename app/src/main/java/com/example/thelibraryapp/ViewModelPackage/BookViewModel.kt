package com.example.thelibraryapp.ViewModelPackage

import android.util.Log
import com.example.thelibraryapp.Room.BookEntity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.thelibraryapp.repository.Repository

class BookViewModel(val repository: Repository) : ViewModel() {
    fun addBook(book: BookEntity) {
        Log.d("hi", "After insertion")

        viewModelScope.launch {

            repository.addBookToTheRoom(book)
        }

    }

    val books = repository.getAllBooks()

    fun deleteBook(book: BookEntity) {
        viewModelScope.launch {
            repository.deleteBookFromTheRoom(book)
        }
    }

    fun updateBook(book: BookEntity) {
        viewModelScope.launch {
            repository.updateBookInRoom(book)
        }
    }


}