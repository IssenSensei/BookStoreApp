package com.issen.ebooker.print

import androidx.fragment.app.Fragment


class PrintBooksFragment : Fragment() {

//    private lateinit var booksMap: MutableList<BooksItem>
//    private lateinit var booksRecycler: RecyclerView
//
//    lateinit var print: String
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.layout_book_list)
//        initToolbar()
//
//        book_fab.visibility = View.GONE
//        booksRecycler = findViewById(R.id.books_recycler_view)
//        setUpRecycler()
//        print = intent.getSerializableExtra("print") as String
//        getData()
//
//    }
//
//
//    private fun setUpRecycler() {
//        booksRecycler.layoutManager = LinearLayoutManager(this)
//        booksRecycler.addItemDecoration(
//            LineItemDecoration(
//                this,
//                LinearLayout.VERTICAL
//            )
//        )
//        booksRecycler.adapter = BooksRecyclerViewAdapter(mutableListOf(), this)
//    }
//
//    fun getData(){
//        val apiInterface = ApiInterface.create().getPrintBooks("getPrintBooks", print, ApiInterface.USER_ID)
//        apiInterface.enqueue( object : Callback<List<BooksItem>> {
//
//            override fun onResponse(call: Call<List<BooksItem>>?, response: Response<List<BooksItem>>?) {
//                if(response?.body() != null) {
//                    booksMap = response.body() as MutableList<BooksItem>
//                        booksRecycler.getBooksAdapter().updateList(booksMap)
//                }
//            }
//
//            override fun onFailure(call: Call<List<BooksItem>>?, t: Throwable?) {
//                Toast.makeText(applicationContext, "Wystąpił problem przy pobieraniu danych", Toast.LENGTH_SHORT).show()
//
//            }
//        })
//    }
//
//    private fun initToolbar() {
//        val toolbar: Toolbar = findViewById(R.id.books_toolbar)
//        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == android.R.id.home) {
//            setResult(Activity.RESULT_CANCELED)
//            finish()
//        } else {
//            Toast.makeText(applicationContext, item.title, Toast.LENGTH_SHORT).show()
//        }
//        return super.onOptionsItemSelected(item)
//    }
}
