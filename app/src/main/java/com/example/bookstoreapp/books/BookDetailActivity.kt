package com.example.bookstoreapp.books

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bookstoreapp.BookReaderActivity
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.getAsTempFile
import io.ktor.client.HttpClient
import kotlinx.android.synthetic.main.activity_book_details.*
import kotlinx.coroutines.runBlocking
import java.io.File


class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val data: BooksItem = intent.getSerializableExtra("data") as BooksItem

        val bookTitle = findViewById<TextView>(R.id.bookTitle)!!
        val bookDescription = findViewById<TextView>(R.id.bookDescription)!!
        val year = findViewById<TextView>(R.id.yearView)!!
        val publisher = findViewById<TextView>(R.id.publisherView)!!
        val image = findViewById<ImageView>(R.id.image)!!
        val author = findViewById<TextView>(R.id.authorView)!!

        author.text = data.author
        publisher.text = data.publisher
        year.text = data.year
        bookDescription.text = data.description
        bookTitle.text = data.title
        Glide.with(applicationContext)
            .load(data.picture)
            .into(image)

        fab.setOnClickListener{
            runBlocking {
                val client = HttpClient {
                    followRedirects = true
                }
                client.getAsTempFile(data.file) { file ->
                    println(file.readBytes().size)

                    val ebookDirectory = File(getExternalFilesDir(null), "Ebook")
                    ebookDirectory.mkdirs()

                    val bookName = data.file.substring(data.file.lastIndexOf("/"))
                    val extension = data.file.substring(data.file.lastIndexOf("."))
                    val newfile = File(ebookDirectory, bookName)

                    val ebookPath = ebookDirectory.toString() + bookName

                    file.copyTo(newfile, overwrite = true)

                    val intent = Intent(applicationContext, BookReaderActivity::class.java)
                    intent.putExtra("file", ebookPath)
                    intent.putExtra("extension", extension)
                    startActivity(intent)

                }
            }

        }
    }
}
