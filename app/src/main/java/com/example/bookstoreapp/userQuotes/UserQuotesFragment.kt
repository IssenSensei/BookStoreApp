package com.example.bookstoreapp.userQuotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookstoreapp.R
import kotlinx.android.synthetic.main.layout_book_quote_list.*
import kotlinx.android.synthetic.main.layout_user_quotes_list.*

class UserQuotesFragment: Fragment() {

    private val userQuotesMap: HashMap<String, UserQuotesItem> = hashMapOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return inflater.inflate(R.layout.layout_user_quotes_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userQuotesMap["cos"] = UserQuotesItem(2, "dupa", "kupa", "pupa")
        userQuotesMap["we"] = UserQuotesItem(3, "rrrr", "qqq", "eee")
        userQuotesMap["ew"] = UserQuotesItem(1, "yyyy", "kuwwwpa", "rrrr")

        setUpRecycler()
    }

    private fun setUpRecycler() {
        user_quote_item_list.layoutManager = LinearLayoutManager(context)
        user_quote_item_list.adapter = UserQuotesRecyclerViewAdapter(userQuotesMap)
    }

    interface OnUserQuotesInteractionListener {
        //fun onUserSelected(user: UserItem, image: View)
    }
}