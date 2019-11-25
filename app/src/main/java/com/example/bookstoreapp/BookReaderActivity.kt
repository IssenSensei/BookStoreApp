package com.example.bookstoreapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.layout_book_reader.*
import java.io.File
import com.folioreader.FolioReader



class BookReaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_book_reader)

        val extension = intent.getStringExtra("extension")!!

        when {
            extension.equals("pdf") -> {
                pdfView.fromFile(File(intent.getStringExtra("file")!!)).nightMode(true).load()
            }
            extension.equals("epub") -> {
                val folioReader = FolioReader.get()
                folioReader.openBook(intent.getStringExtra("file"))
            }
        }

    }



}
