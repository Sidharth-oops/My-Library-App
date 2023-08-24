package com.example.thelibraryapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.thelibraryapp.Room.BookEntity
import com.example.thelibraryapp.Room.BooksDb
import com.example.thelibraryapp.ViewModelPackage.BookViewModel
import com.example.thelibraryapp.repository.Repository
import com.example.thelibraryapp.screens.UpdateScreen

import com.example.thelibraryapp.ui.theme.TheLibraryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheLibraryAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    val mContext = LocalContext.current
                    val db = BooksDb.getInstance(mContext)
                    val repository = Repository(db)
                    val myViewModel = BookViewModel(repository = repository)

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "MainScreen") {
                        composable("MainScreen") {
                            MainScreen(viewModel = myViewModel, navController = navController)
                        }
                        composable("UpdateScreen/{bookId}") {
                            UpdateScreen(
                                viewModel = myViewModel,
                                bookId = it.arguments?.getString("bookId")
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: BookViewModel, navController: NavHostController) {
    var inputBook by remember {
        mutableStateOf("")
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

        modifier = Modifier.fillMaxSize().padding(top = 22.dp, start = 6.dp, end = 6.dp)
    ) {
        Text(text = "Insert Books In Room DB", fontSize = 22.sp)
        OutlinedTextField(value = inputBook,
            onValueChange = { enteredText ->
                inputBook = enteredText
            },
            label = { Text(text = " Book Name") },
            placeholder = { Text(text = "Enter Your Book Name") })
        Button(
            onClick = {

                viewModel.addBook(BookEntity(0, inputBook))


            },
            colors = ButtonDefaults.buttonColors(Color.Blue)
        ) {
            Text(text = "Insert Book In Db")
        }
        BooksList(viewModel = viewModel, navController)
    }
}

@Composable
fun BooKCard(viewModel: BookViewModel, book: BookEntity, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "" + book.id,
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp),
                color = Color.Blue
            )
            Text(
                text = book.title,
                fontSize = 24.sp,
                modifier = Modifier.fillMaxSize(0.7f),
                color = Color.Black
            )
            Row(horizontalArrangement = Arrangement.End) {
                IconButton(onClick = { viewModel.deleteBook(book = book) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "DELETE")

                }
                IconButton(onClick = { navController.navigate("UpdateScreen/${book.id}") }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                }
            }


        }
    }

}

@Composable
fun BooksList(viewModel: BookViewModel, navController: NavHostController) {
    val books by viewModel.books.collectAsState(initial = emptyList())
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
        Text(text = "My Library", fontSize = 24.sp, color = Color.Red)
        LazyColumn {
            items(items = books) { item ->
                BooKCard(viewModel = viewModel, book = item, navController)
            }
        }
    }

}

