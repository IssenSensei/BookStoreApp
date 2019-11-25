package com.example.bookstoreapp.books

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
import kotlinx.android.synthetic.main.layout_book_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksFragment: Fragment() {

    private lateinit var booksMap: MutableList<BooksItem>
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.layout_book_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView = view!!.findViewById(R.id.books_item_list)

        booksMap = mutableListOf()
        val apiInterface = ApiInterface.create().getBooks("getBooks")

        apiInterface.enqueue( object : Callback<List<BooksItem>> {

            override fun onResponse(call: Call<List<BooksItem>>?, response: Response<List<BooksItem>>?) {
                if(response?.body() != null) {
                    setBookListItems(response.body()!! as MutableList)
                }
            }

            override fun onFailure(call: Call<List<BooksItem>>?, t: Throwable?) {
                Toast.makeText(context, "Wystąpił problem przy pobieraniu danych", Toast.LENGTH_SHORT).show()

            }
        })
    }

    fun setBookListItems(bookList: MutableList<BooksItem>) {

        this.booksMap = bookList
        if (booksMap.size == 0)
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
            recyclerView.adapter = BooksRecyclerViewAdapter(booksMap, this.context!!)
        }
    }
}