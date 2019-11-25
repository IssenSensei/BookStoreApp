package com.example.bookstoreapp.userQuotes

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
import kotlinx.android.synthetic.main.layout_user_quotes_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserQuotesFragment: Fragment() {

    private lateinit var userQuotesMap: MutableList<UserQuotesItem>
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_user_quotes_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView = view!!.findViewById(R.id.user_quote_item_list)

        userQuotesMap = mutableListOf()
        val apiInterface = ApiInterface.create().getUserQuotes("getUserQuotes", 3)

        apiInterface.enqueue(object : Callback<List<UserQuotesItem>> {

            override fun onResponse(
                call: Call<List<UserQuotesItem>>,
                response: Response<List<UserQuotesItem>>?
            ) {
                if (response?.body() != null) {
                    setBookListItems(response.body()!! as MutableList)
                }
            }

            override fun onFailure(call: Call<List<UserQuotesItem>>?, t: Throwable?) {
                Toast.makeText(
                    context,
                    "Wystąpił problem przy pobieraniu danych",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })
    }

    fun setBookListItems(userQuoteList: MutableList<UserQuotesItem>) {

        this.userQuotesMap = userQuoteList
        if (userQuotesMap.size == 0)
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
            recyclerView.adapter = UserQuotesRecyclerViewAdapter(userQuotesMap, this.context!!)
        }
    }
}
