package com.example.bookstoreapp.bookQuotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstoreapp.R
import kotlinx.android.synthetic.main.layout_book_quote_list.*

class BookQuotesFragment: Fragment() {

    private val bookQuotesMap: HashMap<String, BookQuotesItem> = hashMapOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.layout_book_quote_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bookQuotesMap["cos"] = BookQuotesItem(2, "dupa", "kupa", "pupa")
        bookQuotesMap["we"] = BookQuotesItem(3, "rrrr", "qqq", "eee")
        bookQuotesMap["ew"] = BookQuotesItem(4, "yyyy", "kuwwwpa", "rrrr")

        setUpRecycler()
    }

    private fun setUpRecycler() {
        book_quote_item_list.layoutManager = LinearLayoutManager(context)
        book_quote_item_list.adapter = BookQuotesRecyclerViewAdapter(bookQuotesMap)
    }

    interface OnBookQuotesInteractionListener {
        //fun onUserSelected(user: UserItem, image: View)
    }
}