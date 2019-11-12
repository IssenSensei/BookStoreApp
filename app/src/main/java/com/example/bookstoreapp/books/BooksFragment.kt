package com.example.bookstoreapp.books

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstoreapp.R
import kotlinx.android.synthetic.main.layout_book_list.*

class BooksFragment: Fragment() {

    private val booksMap: HashMap<String, BooksItem> = hashMapOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.layout_book_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        booksMap["cos"] = BooksItem(
            6,
            "Dawca",
            "2013",
            "Dawca",
            "http://localhost/BookstoreApi/books/book5.pdf",
            "http://192.168.0.164/BookstoreApi/images/image3.jpg",
            "Trzymający w napięciu thriller medyczny",
            "4"
        )
        booksMap["za"] = BooksItem(
            7,
            "Dom na Wyrębach",
            "2014",
            "Nietoperz człowiek",
            "http://localhost/BookstoreApi/books/book7.pdf",
            "http://192.168.0.164/BookstoreApi/images/image6.jpg",
            "Trzymający w napięciu horror",
            "3"
        )
        booksMap["ssa"] = BooksItem(
            8,
            "Biorca",
            "2018",
            "Albatros",
            "http://localhost/BookstoreApi/books/book8.pdf",
            "http://192.168.0.164/BookstoreApi/images/image9.jpg",
            "Trzymający w napięciu thriller medyczny ale nie",
            "1"
        )
        booksMap["sss"] = BooksItem(
            9,
            "Wyrąb na domach",
            "2018",
            "Albatros",
            "http://localhost/BookstoreApi/books/book8.pdf",
            "http://192.168.0.164/BookstoreApi/images/image4.jpg",
            "Trzymający w napięciu horror ale nie",
            "1"
        )

        setUpRecycler()
    }

    private fun setUpRecycler() {
        books_item_list.layoutManager = LinearLayoutManager(context)
        books_item_list.adapter = BooksRecyclerViewAdapter(booksMap)
    }


}