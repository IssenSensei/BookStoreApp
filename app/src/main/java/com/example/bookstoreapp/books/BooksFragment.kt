package com.example.bookstoreapp.books

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.Api
import com.example.bookstoreapp.utils.LineItemDecoration
import kotlinx.android.synthetic.main.layout_book_list.*
import org.json.JSONException
import org.json.JSONObject

class BooksFragment: Fragment() {

    private lateinit var booksMap: MutableList<BooksItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.layout_book_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        booksMap = mutableListOf()
        loadBooks()

        setUpRecycler()
    }

    private fun setUpRecycler() {
        books_item_list.layoutManager = LinearLayoutManager(context)
        books_item_list.addItemDecoration(LineItemDecoration(this.context, LinearLayout.VERTICAL))
        books_item_list.adapter = BooksRecyclerViewAdapter(booksMap, this.context!!)
    }

    private fun loadBooks() {
        val stringRequest = StringRequest(Request.Method.GET,
            Api.URL_GET_BOOKS,
            Response.Listener<String> { s ->
                try {

                        val obj = JSONObject(s)

                    if (!obj.getBoolean("error")) {

                        val array = obj.getJSONArray("books")

                        for (i in 0..array.length() - 1) {
                            val objectBook = array.getJSONObject(i)
                            val book = BooksItem(
                                objectBook.getInt("id"),
                                objectBook.getString("title"),
                                objectBook.getString("year"),
                                objectBook.getString("publisher"),
                                objectBook.getString("file"),
                                objectBook.getString("picture"),
                                objectBook.getString("description"),
                                objectBook.getString("author")
                            )
                            booksMap.add(book)
                        }
                        books_item_list.adapter?.notifyDataSetChanged()
                    } else {
                        Toast.makeText(this.context, obj.getString("message"), Toast.LENGTH_LONG).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }, Response.ErrorListener { volleyError -> Toast.makeText(this.context, volleyError.message, Toast.LENGTH_LONG).show() })

        val requestQueue = Volley.newRequestQueue(this.context)
        requestQueue.add<String>(stringRequest)
    }
}