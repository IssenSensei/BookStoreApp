package com.issen.ebooker.bookQuoteList

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver


class BookQuotesFirebaseRecyclerObserver(
    private val mRecycler: RecyclerView,
    private val mAdapter: RecyclerView.Adapter<*>,
    private val mManager: LinearLayoutManager
) : AdapterDataObserver() {
    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        super.onItemRangeInserted(positionStart, itemCount)
        val count = mAdapter.itemCount
        val lastVisiblePosition = mManager.findLastCompletelyVisibleItemPosition()
        val loading = lastVisiblePosition == -1
        val atBottom = positionStart >= count - 1 && lastVisiblePosition == positionStart - 1
        if (loading || atBottom) {
            mRecycler.scrollToPosition(positionStart)
        }
    }
}