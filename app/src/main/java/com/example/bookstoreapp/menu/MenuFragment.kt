package com.example.bookstoreapp.menu

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

class MenuFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

}