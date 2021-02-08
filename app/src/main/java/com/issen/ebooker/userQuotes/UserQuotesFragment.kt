package com.issen.ebooker.userQuotes

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.issen.ebooker.R
import com.issen.ebooker.utils.LineItemDecoration
import com.issen.ebooker.utils.getUserQuotesAdapter
import kotlinx.android.synthetic.main.layout_user_quotes_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserQuotesFragment: Fragment() {

    private lateinit var userQuotesMap: MutableList<UserQuotesItem>
    private lateinit var userQuotesRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_user_quotes_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        userQuotesRecycler = view!!.findViewById(R.id.user_quote_recycler_view)
        setUpRecycler()
        getData()
        user_quote_fab.setOnClickListener {
            showCustomDialog()
        }

        user_quote_swipe_layout.setOnRefreshListener {
            getData()
            user_quote_swipe_layout.isRefreshing = false
        }
    }

    private fun setUpRecycler() {
        userQuotesRecycler.layoutManager = LinearLayoutManager(context)
        userQuotesRecycler.addItemDecoration(
            LineItemDecoration(
                this.context,
                LinearLayout.VERTICAL
            )
        )
        userQuotesRecycler.adapter = UserQuotesRecyclerViewAdapter(mutableListOf(), this.context!!)
    }

    private fun getData() {
//        val apiInterface = ApiInterface.create().getUserQuotes("getUserQuotes", ApiInterface.USER_ID)
//        apiInterface.enqueue(object : Callback<List<UserQuotesItem>> {
//
//            override fun onResponse(
//                call: Call<List<UserQuotesItem>>,
//                response: Response<List<UserQuotesItem>>?
//            ) {
//                if (response?.body() != null) {
//                    userQuotesMap = response.body() as MutableList<UserQuotesItem>
//                    if (userQuotesMap.size == 0) {
//                        no_user_quote_data_text_view.visibility = View.VISIBLE
//                    }
//                    else {
//                        userQuotesRecycler.getUserQuotesAdapter().updateList(userQuotesMap)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<List<UserQuotesItem>>?, t: Throwable?) {
//                Toast.makeText(
//                    context,
//                    "Wystąpił problem przy pobieraniu danych uk",
//                    Toast.LENGTH_SHORT
//                ).show()
//
//            }
//        })
    }

    private fun showCustomDialog() {

        val dialog = Dialog(this.context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.dialog_user_quote_search)
        dialog.setCancelable(true)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        (dialog.findViewById(R.id.user_quote_search_button_cancel) as Button).setOnClickListener { dialog.dismiss() }
        (dialog.findViewById(R.id.user_quote_search_button_save) as Button).setOnClickListener {
            val title = dialog.findViewById<EditText>(R.id.user_quote_search_title)
            val description = dialog.findViewById<EditText>(R.id.user_quote_search_content)
            var list = mutableListOf<UserQuotesItem>()
            list.addAll(userQuotesMap)
            userQuotesMap.clear()
            if (!title.text.toString().equals("")) {
                list = list.filter {
                    it.bookTitle.toLowerCase().contains(title.text.toString().toLowerCase())
                } as MutableList<UserQuotesItem>
            }
            if (!description.text.toString().equals("")) {
                list = list.filter {
                    it.content.toLowerCase().contains(description.text.toString().toLowerCase())
                } as MutableList<UserQuotesItem>
            }
            userQuotesMap.addAll(list)
            userQuotesRecycler.getUserQuotesAdapter().notifyDataSetChanged()
            dialog.dismiss()
        }


        dialog.show()
        dialog.window!!.attributes = lp
    }
}
