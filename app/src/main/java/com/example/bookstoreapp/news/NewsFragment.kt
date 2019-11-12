package com.example.bookstoreapp.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstoreapp.R
import com.example.bookstoreapp.news.NewsItem
import com.example.bookstoreapp.news.NewsRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_news_list.*

class NewsFragment: Fragment() {

    private val newsMap: HashMap<String, NewsItem> = hashMapOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.layout_news_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        newsMap["cos"] = NewsItem(2, "content", "ffffff", "login", "http://192.168.0.164/BookstoreApi/images/image3.jpg")
        newsMap["we"] = NewsItem(3, "rrrr", "qqq", "eee", "http://192.168.0.164/BookstoreApi/images/image6.jpg")
        newsMap["ew"] = NewsItem(4, "yyyy", "kuwwwpa", "rrrr", "http://192.168.0.164/BookstoreApi/images/image9.jpg")

        setUpRecycler()
    }

    private fun setUpRecycler() {
        news_item_list.layoutManager = LinearLayoutManager(context)
        news_item_list.adapter = NewsRecyclerViewAdapter(newsMap)
    }

    interface OnNewsInteractionListener {
        //fun onUserSelected(user: UserItem, image: View)
    }
}