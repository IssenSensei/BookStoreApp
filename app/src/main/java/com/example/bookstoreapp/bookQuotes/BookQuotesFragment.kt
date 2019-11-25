package com.example.bookstoreapp.bookQuotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.ApiInterface
import com.example.bookstoreapp.utils.LineItemDecoration
import kotlinx.android.synthetic.main.layout_book_quote_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookQuotesFragment: Fragment() {

    private lateinit var bookQuotesMap: MutableList<BookQuotesItem>
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.layout_book_quote_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView = view!!.findViewById(R.id.book_quote_item_list)

        bookQuotesMap = mutableListOf()
        val apiInterface = ApiInterface.create().getBookQuotes("getBookQuotes", 3)

        apiInterface.enqueue( object : Callback<List<BookQuotesItem>> {

            override fun onResponse(call: Call<List<BookQuotesItem>>, response: Response<List<BookQuotesItem>>?) {
                if(response?.body() != null) {
                    setBookListItems(response.body()!! as MutableList)
                }
            }

            override fun onFailure(call: Call<List<BookQuotesItem>>?, t: Throwable?) {
                Toast.makeText(context, "Wystąpił problem przy pobieraniu danych", Toast.LENGTH_SHORT).show()

            }
        })
    }

    fun setBookListItems(bookQuoteList: MutableList<BookQuotesItem>) {

        this.bookQuotesMap = bookQuoteList
        if (bookQuotesMap.size == 0)
            no_data_text_view.visibility = View.VISIBLE
        else {
            no_data_text_view.visibility = View.GONE
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.addItemDecoration(
                LineItemDecoration(
                    this.context,
                    LinearLayout.VERTICAL
                )
            )
            recyclerView.adapter = BookQuotesRecyclerViewAdapter(bookQuotesMap, this.context!!)
        }
    }

}