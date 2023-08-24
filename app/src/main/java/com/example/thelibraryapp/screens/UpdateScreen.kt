package com.example.thelibraryapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thelibraryapp.Room.BookEntity
import com.example.thelibraryapp.ViewModelPackage.BookViewModel


@Composable
fun UpdateScreen(viewModel: BookViewModel, bookId: String?) {
    var inputBook by remember {
        mutableStateOf("")
    }
    Column(Modifier.padding(16.dp)) {
        Text(text = "Update The Existing Book", fontSize = 24.sp)
        OutlinedTextField(

            value = inputBook, onValueChange = { enteredText ->
                inputBook = enteredText
            },
            label = {
                Text(text = "Update Book Name")
            },
            placeholder = { Text(text = "New Book Name") },
            modifier = Modifier.padding(16.dp)

        )
        Button(
            colors = ButtonDefaults.buttonColors(Color.Red),
            onClick = {
                var newBook = BookEntity(bookId!!.toInt(), inputBook)
                viewModel.updateBook(newBook)


            },
            modifier = Modifier.padding(16.dp),

            ) {
            Text(text = "Update Book")
        }

    }
}
