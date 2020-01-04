package com.example.bookstoreapp.bookQuotes

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.ApiInterface
import com.example.bookstoreapp.utils.getBookQuotesAdapter
import kotlinx.android.synthetic.main.layout_book_quote_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookQuotesFragment : Fragment() {

    private lateinit var bookQuotesMap: MutableList<BookQuotesItem>
    private lateinit var bookQuotesRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_book_quote_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bookQuotesRecycler = view!!.findViewById(R.id.book_quote_recycler_view)
        setUpRecycler()
        getData()


        book_quote_fab.setOnClickListener {
            showCustomDialog()
        }

        book_quote_swipe_layout.setOnRefreshListener {
            getData()
            book_quote_swipe_layout.isRefreshing = false
        }
    }


    private fun setUpRecycler() {
        bookQuotesRecycler.layoutManager = LinearLayoutManager(context)
//        user_quote_recycler_view.addItemDecoration(
//            LineItemDecoration(
//                this.context,
//                LinearLayout.VERTICAL
//            )
//        )
        bookQuotesRecycler.adapter = BookQuotesRecyclerViewAdapter(mutableListOf(), this.context!!)
    }

    private fun getData() {
        val apiInterface = ApiInterface.create().getBookQuotes("getBookQuotes", 3)

        apiInterface.enqueue(object : Callback<List<BookQuotesItem>> {

            override fun onResponse(
                call: Call<List<BookQuotesItem>>,
                response: Response<List<BookQuotesItem>>?
            ) {
                if (response?.body() != null) {
                    bookQuotesMap = response.body() as MutableList<BookQuotesItem>
                    if (bookQuotesMap.size == 0) {
                        no_book_quote_data_text_view.visibility = View.VISIBLE
                    }
                    else {
                        bookQuotesRecycler.getBookQuotesAdapter().updateList(bookQuotesMap)
                    }
                }
            }

            override fun onFailure(call: Call<List<BookQuotesItem>>?, t: Throwable?) {
                Toast.makeText(
                    requireContext(),
                    "Wystąpił problem przy pobieraniu danych bk",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })
    }

    private fun showCustomDialog() {

        val dialog = Dialog(this.context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.dialog_book_quote_search)
        dialog.setCancelable(true)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        (dialog.findViewById(R.id.book_quote_search_button_cancel) as Button).setOnClickListener { dialog.dismiss() }
        (dialog.findViewById(R.id.book_quote_search_button_save) as Button).setOnClickListener {
            val title = dialog.findViewById<EditText>(R.id.book_quote_search_title)
            val description = dialog.findViewById<EditText>(R.id.book_quote_search_content)
            val userName = dialog.findViewById<EditText>(R.id.book_quote_search_user)
            var list = mutableListOf<BookQuotesItem>()
            list.addAll(bookQuotesMap)
            bookQuotesMap.clear()
            if (title.text.toString() != "") {
                list = list.filter {
                    it.bookTitle.toLowerCase().contains(title.text.toString().toLowerCase())
                } as MutableList<BookQuotesItem>
            }
            if (description.text.toString() != "") {
                list = list.filter {
                    it.content.toLowerCase().contains(description.text.toString().toLowerCase())
                } as MutableList<BookQuotesItem>
            }
            if (userName.text.toString() != "") {
                list = list.filter {
                    it.userName.toLowerCase().contains(userName.text.toString().toLowerCase())
                } as MutableList<BookQuotesItem>
            }

            bookQuotesMap.addAll(list)
            bookQuotesRecycler.getBookQuotesAdapter().notifyDataSetChanged()
            dialog.dismiss()
        }


        dialog.show()
        dialog.window!!.attributes = lp
    }
}