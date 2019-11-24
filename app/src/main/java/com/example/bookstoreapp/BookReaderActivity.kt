package com.example.bookstoreapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.layout_book_reader.*
import java.io.File

class BookReaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_book_reader)

        val newfile = File(intent.getStringExtra("file"))
        Log.d("ebookPath", newfile.toString())

        pdfView.fromFile(newfile).nightMode(true).load()

    }



}
