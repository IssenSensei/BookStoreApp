package com.example.bookstoreapp.userQuotes

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
import kotlinx.android.synthetic.main.layout_user_quotes_list.*
import org.json.JSONException
import org.json.JSONObject

class UserQuotesFragment: Fragment() {

    private lateinit var userQuotesMap: MutableList<UserQuotesItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.layout_user_quotes_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        userQuotesMap = mutableListOf()
        loadQuotes()
        setUpRecycler()
    }

    private fun setUpRecycler() {
        user_quote_item_list.layoutManager = LinearLayoutManager(context)
        user_quote_item_list.addItemDecoration(LineItemDecoration(this.context, LinearLayout.VERTICAL))
        user_quote_item_list.adapter = UserQuotesRecyclerViewAdapter(userQuotesMap, this.context!!)
    }

    private fun loadQuotes() {
        val stringRequest = StringRequest(Request.Method.GET,
            Api.URL_GET_USER_QUOTES,
            Response.Listener<String> { s ->
                try {

                    val obj = JSONObject(s)

                    if (!obj.getBoolean("error")) {

                        val array = obj.getJSONArray("quotes")

                        for (i in 0..array.length() - 1) {
                            val objectQuote = array.getJSONObject(i)
                            val userQuote = UserQuotesItem(
                                objectQuote.getInt("id"),
                                objectQuote.getString("bookTitle"),
                                objectQuote.getString("content"),
                                objectQuote.getString("picture")
                            )
                            userQuotesMap.add(userQuote)
                        }
                        user_quote_item_list.adapter?.notifyDataSetChanged()
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