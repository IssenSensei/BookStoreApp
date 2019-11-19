package com.example.bookstoreapp.news

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
import kotlinx.android.synthetic.main.layout_news_list.*
import org.json.JSONException
import org.json.JSONObject

class NewsFragment: Fragment() {

    private lateinit var newsMap: MutableList<NewsItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.layout_news_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newsMap = mutableListOf()
        loadNews()
        setUpRecycler()
    }

    private fun setUpRecycler() {
        news_item_list.layoutManager = LinearLayoutManager(context)
        news_item_list.addItemDecoration(LineItemDecoration(this.context, LinearLayout.VERTICAL))
        news_item_list.adapter = NewsRecyclerViewAdapter(newsMap, this.context!!)
    }

    private fun loadNews() {
        val stringRequest = StringRequest(Request.Method.GET,
            Api.URL_GET_NEWS,
            Response.Listener<String> { s ->
                try {

                    val obj = JSONObject(s)

                    if (!obj.getBoolean("error")) {

                        val array = obj.getJSONArray("news")

                        for (i in 0..array.length() - 1) {
                            val objectNews = array.getJSONObject(i)
                            val news = NewsItem(
                                objectNews.getInt("id"),
                                objectNews.getString("content"),
                                objectNews.getString("title"),
                                objectNews.getString("login"),
                                objectNews.getString("picture")
                            )
                            newsMap.add(news)
                        }
                        news_item_list.adapter?.notifyDataSetChanged()
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