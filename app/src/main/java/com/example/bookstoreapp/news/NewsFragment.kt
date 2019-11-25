package com.example.bookstoreapp.news

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
import com.example.bookstoreapp.database.Api
import com.example.bookstoreapp.database.ApiInterface
import com.example.bookstoreapp.utils.LineItemDecoration
import kotlinx.android.synthetic.main.layout_news_list.*
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment: Fragment() {

    private lateinit var newsMap: MutableList<NewsItem>
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.layout_news_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView = view!!.findViewById(R.id.news_item_list)

        newsMap = mutableListOf()
        val apiInterface = ApiInterface.create().getNews("getNews")

        apiInterface.enqueue( object : Callback<List<NewsItem>> {

            override fun onResponse(call: Call<List<NewsItem>>, response: Response<List<NewsItem>>?) {
                if(response?.body() != null) {
                    setNewsListItems(response.body()!! as MutableList)
                }
            }

            override fun onFailure(call: Call<List<NewsItem>>?, t: Throwable?) {
                Toast.makeText(context, "Wystąpił problem przy pobieraniu danych", Toast.LENGTH_SHORT).show()

            }
        })
    }

    fun setNewsListItems(newsList: MutableList<NewsItem>) {

        this.newsMap = newsList
        if (newsMap.size == 0)
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
            recyclerView.adapter = NewsRecyclerViewAdapter(newsMap, this.context!!)
        }
    }

//    private fun loadNews() {
//        val stringRequest = StringRequest(Request.Method.GET,
//            Api.URL_GET_NEWS,
//            Response.Listener<String> { s ->
//                try {
//
//                    val obj = JSONObject(s)
//
//                    if (!obj.getBoolean("error")) {
//
//                        val array = obj.getJSONArray("news")
//
//                        for (i in 0..array.length() - 1) {
//                            val objectNews = array.getJSONObject(i)
//                            val news = NewsItem(
//                                objectNews.getInt("id"),
//                                objectNews.getString("content"),
//                                objectNews.getString("title"),
//                                objectNews.getString("login"),
//                                objectNews.getString("picture")
//                            )
//                            newsMap.add(news)
//                        }
//                        news_item_list.adapter?.notifyDataSetChanged()
//                        no_data_text_view.visibility = View.GONE
//
//                    } else {
//                        Toast.makeText(this.context, obj.getString("message"), Toast.LENGTH_LONG).show()
//                    }
//                } catch (e: JSONException) {
//                    Toast.makeText(this.context, "Problem z połączeniem", Toast.LENGTH_SHORT).show()
//                    e.printStackTrace()
//                }
//            }, Response.ErrorListener { volleyError -> Toast.makeText(this.context, volleyError.message, Toast.LENGTH_LONG).show() })
//
//        val requestQueue = Volley.newRequestQueue(this.context)
//        requestQueue.add<String>(stringRequest)
//    }
}