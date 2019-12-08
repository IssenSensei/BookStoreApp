package com.example.bookstoreapp.publisher

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookstoreapp.R
import com.example.bookstoreapp.books.BooksItem
import com.example.bookstoreapp.books.BooksRecyclerViewAdapter
import com.example.bookstoreapp.database.ApiInterface
import com.example.bookstoreapp.utils.LineItemDecoration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PublisherBooksActivity : AppCompatActivity() {

    private lateinit var booksMap: MutableList<BooksItem>
    private lateinit var recyclerView: RecyclerView
    lateinit var publisherId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_book_list)

        publisherId = intent.getSerializableExtra("publisherId") as String
        recyclerView = findViewById(R.id.books_item_list)
        booksMap = mutableListOf()
        recyclerView.adapter = BooksRecyclerViewAdapter(booksMap, applicationContext)
        getData()

    }

    fun getData(){
        val apiInterface = ApiInterface.create().getPublisherBooks("getPublisherBooks", publisherId)
        apiInterface.enqueue( object : Callback<List<BooksItem>> {

            override fun onResponse(call: Call<List<BooksItem>>?, response: Response<List<BooksItem>>?) {
                if(response?.body() != null) {
                    setBookListItems(response.body()!! as MutableList)
                }
            }

            override fun onFailure(call: Call<List<BooksItem>>?, t: Throwable?) {
                Toast.makeText(applicationContext, "Wystąpił problem przy pobieraniu danych", Toast.LENGTH_SHORT).show()

            }
        })
    }

    fun setBookListItems(bookList: MutableList<BooksItem>) {

        this.booksMap.clear()
        this.booksMap.addAll(bookList)
        if (booksMap.size == 0)
        else {
            recyclerView.layoutManager = LinearLayoutManager(applicationContext)
            recyclerView.addItemDecoration(
                LineItemDecoration(
                    applicationContext,
                    LinearLayout.VERTICAL
                )
            )
        }
        recyclerView.adapter!!.notifyDataSetChanged()
    }

}
