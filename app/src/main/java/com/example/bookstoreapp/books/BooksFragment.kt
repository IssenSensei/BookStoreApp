package com.example.bookstoreapp.books

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookstoreapp.R
import com.example.bookstoreapp.database.ApiInterface
import com.example.bookstoreapp.utils.LineItemDecoration
import com.example.bookstoreapp.utils.getBooksAdapter
import kotlinx.android.synthetic.main.layout_book_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class BooksFragment : Fragment() {

    private lateinit var booksMap: MutableList<BooksItem>
    private lateinit var booksRecycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_book_list,
            container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        appbarlayout.visibility = View.GONE
        booksRecycler = view!!.findViewById(R.id.books_recycler_view)
        setUpRecycler()
        getData()

        book_fab.setOnClickListener {
            showCustomDialog()
        }

        book_swipe_layout.setOnRefreshListener {
            getData()
            book_swipe_layout.isRefreshing = false
        }
    }

    private fun setUpRecycler() {
        booksRecycler.layoutManager = LinearLayoutManager(context)
        booksRecycler.addItemDecoration(
            LineItemDecoration(
                this.context,
                LinearLayout.VERTICAL
            )
        )
        booksRecycler.adapter = BooksRecyclerViewAdapter(mutableListOf(), this.context!!)
    }


    private fun getData() {
        val apiInterface = ApiInterface
            .create().getBooks("getBooks", 1)
        Log.d("qqqq", apiInterface.request().toString())
        apiInterface.enqueue(object : Callback<List<BooksItem>> {
            override fun onResponse(
                call: Call<List<BooksItem>>?,
                response: Response<List<BooksItem>>?
            ) {
                Log.d("qqqq", response?.body().toString())

                if (response?.body() != null) {
                    booksMap = response.body() as MutableList<BooksItem>
                    if (booksMap.size == 0) {
                        no_book_data_text_view.visibility = View.VISIBLE
                    }
                    else {
                        booksRecycler.getBooksAdapter().updateList(booksMap)
                    }
                }
            }
            override fun onFailure(call: Call<List<BooksItem>>?, t: Throwable?) {
                Toast.makeText(
                    context,
                    "Wystąpił problem przy pobieraniu danych",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun showCustomDialog() {

        val dialog = Dialog(this.context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.dialog_book_search)
        dialog.setCancelable(true)

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        val years = ArrayList<String>()
        val thisYear = Calendar.getInstance().get(Calendar.YEAR)
        for (i in 1900..thisYear) {
            years.add(i.toString())
        }
        val yearAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, years)
        val spinYearMin = dialog.findViewById(R.id.book_search_spinner_year_min) as Spinner
        val spinYearMax = dialog.findViewById(R.id.book_search_spinner_year_max) as Spinner
        spinYearMin.adapter = yearAdapter
        spinYearMax.adapter = yearAdapter
        spinYearMax.setSelection(years.size - 1)

        (dialog.findViewById(R.id.book_search_button_cancel) as Button).setOnClickListener { dialog.dismiss() }
        (dialog.findViewById(R.id.book_search_button_save) as Button).setOnClickListener {
            val title = dialog.findViewById<EditText>(R.id.book_search_name)
            val publisher = dialog.findViewById<EditText>(R.id.book_search_publisher)
            val author = dialog.findViewById<EditText>(R.id.book_search_author)
            val description = dialog.findViewById<EditText>(R.id.book_search_content)
            var list = mutableListOf<BooksItem>()
            list.addAll(booksMap)
            booksMap.clear()
            if (title.text.toString() != "") {
                list = list.filter {
                    it.title.toLowerCase().contains(title.text.toString().toLowerCase())
                } as MutableList<BooksItem>
            }
            if (publisher.text.toString() != "") {
                list = list.filter {
                    it.print.toLowerCase().contains(publisher.text.toString().toLowerCase())
                } as MutableList<BooksItem>
            }
            if (author.text.toString() != "") {
                list = list.filter {
                    it.authorName.toLowerCase().contains(author.text.toString().toLowerCase())
                } as MutableList<BooksItem>
            }
            if (author.text.toString() != "") {
                list = list.filter {
                    it.authorSurname.toLowerCase().contains(author.text.toString().toLowerCase())
                } as MutableList<BooksItem>
            }
            if (description.text.toString() != "") {
                list = list.filter {
                    it.description.toLowerCase().contains(description.text.toString().toLowerCase())
                } as MutableList<BooksItem>
            }
            list = list.filter {
                it.year > spinYearMin.selectedItem.toString()
            } as MutableList<BooksItem>
            list = list.filter {
                it.year < spinYearMax.selectedItem.toString()
            } as MutableList<BooksItem>

            booksMap.addAll(list)

            booksRecycler.getBooksAdapter().notifyDataSetChanged()
            dialog.dismiss()
        }


        dialog.show()
        dialog.window!!.attributes = lp
    }
}