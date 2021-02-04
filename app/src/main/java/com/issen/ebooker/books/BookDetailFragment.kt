package com.issen.ebooker.books

import androidx.fragment.app.Fragment


class BookDetailFragment : Fragment() {
//
//    private lateinit var commentsMap: MutableList<CommentItem>
//    private lateinit var recyclerView: RecyclerView
//    var bookId = 0
//    @SuppressLint("SetTextI18n")
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_book_details)
//        initToolbar()
//
//        val data: BooksItem = intent.getSerializableExtra("data") as BooksItem
//        bookId = intent.getSerializableExtra("bookId") as Int
//
//        authorView.text = "${data.authorName}${data.authorSurname}"
//        publisherView.text = data.print
//        yearView.text = data.year
//        bookDescription.text = data.description
//        bookTitle.text = data.title
//        Glide.with(applicationContext)
//            .load(ApiInterface.photoPath + data.picture)
//            .into(image)
//
//        showComments.setOnClickListener {
//            showCommentDialog()
//        }
//
//        authorView.setOnClickListener {
//            val intent = Intent(this, AuthorBooksFragment::class.java)
//            intent.putExtra("authorName", data.authorName)
//            intent.putExtra("authorSurname", data.authorSurname)
//            startActivityForResult(intent, AUTHOR_CODE)
//        }
//
//        publisherView.setOnClickListener {
//            val intent = Intent(this, PrintBooksActivity::class.java)
//            intent.putExtra("print", data.print)
//            startActivityForResult(intent, PRINT_CODE)
//        }
//
//        fab.setOnClickListener {
//            runBlocking {
//                val client = HttpClient {
//                    followRedirects = true
//                }
//                client.getAsTempFile(data.file) { file ->
//                    println(file.readBytes().size)
//
//                    val ebookDirectory = File(getExternalFilesDir(null), "Ebook")
//                    ebookDirectory.mkdirs()
//
//                    val bookName = data.file.substring(data.file.lastIndexOf("/"))
//                    val extension = data.file.substring(data.file.lastIndexOf("."))
//                    val newfile = File(ebookDirectory, bookName)
//
//                    val ebookPath = ebookDirectory.toString() + bookName
//
//                    file.copyTo(newfile, overwrite = true)
//
//                    val intent = Intent(applicationContext, BookReaderActivity::class.java)
//                    intent.putExtra("file", ebookPath)
//                    intent.putExtra("extension", extension)
//                    intent.putExtra("bookId", bookId)
//
//                    startActivityForResult(intent, BOOK_READER_CODE)
//                }
//            }
//
//        }
//    }
//
//    private fun showCommentDialog() {
//        val dialog = Dialog(this)
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
//        dialog.setContentView(R.layout.dialog_add_book_review)
//        dialog.setCancelable(true)
//
//        recyclerView = dialog.findViewById(R.id.comment_list)
//        commentsMap = mutableListOf()
//        recyclerView.adapter = CommentsRecyclerViewAdapter(commentsMap)
//
//        getData()
//
//        val lp = WindowManager.LayoutParams()
//        lp.copyFrom(dialog.window!!.attributes)
//        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
//
//        val content = dialog.findViewById(R.id.rating_content) as EditText
//        val rating = dialog.findViewById(R.id.rating_bar) as AppCompatRatingBar
//        (dialog.findViewById(R.id.bt_cancel) as AppCompatButton).setOnClickListener { dialog.dismiss() }
//
//        (dialog.findViewById(R.id.bt_submit) as AppCompatButton).setOnClickListener {
//            addRating(
//                content.text.toString().trim(),
//                rating.rating.toInt(),
//                bookId
//            )
//            dialog.dismiss()
//        }
//
//        dialog.show()
//        dialog.window!!.attributes = lp
//    }
//
//    private fun getData() {
//        val apiInterface = ApiInterface.create().getBookComments("getBookComments", bookId)
//        apiInterface.enqueue(object : Callback<List<CommentItem>> {
//
//            override fun onResponse(
//                call: Call<List<CommentItem>>?,
//                response: Response<List<CommentItem>>?
//            ) {
//                if (response?.body() != null) {
//                    setCommentListItems(response.body()!! as MutableList)
//                }
//            }
//
//            override fun onFailure(call: Call<List<CommentItem>>?, t: Throwable?) {
//                Toast.makeText(
//                    applicationContext,
//                    "Wystąpił problem przy pobieraniu danych",
//                    Toast.LENGTH_SHORT
//                ).show()
//
//            }
//        })
//    }
//
//    fun setCommentListItems(commentList: MutableList<CommentItem>) {
//
//        this.commentsMap.clear()
//        this.commentsMap.addAll(commentList)
//        if (commentsMap.size == 0)
//        else {
//            recyclerView.layoutManager = LinearLayoutManager(this.applicationContext)
//            recyclerView.addItemDecoration(
//                LineItemDecoration(
//                    this.applicationContext,
//                    LinearLayout.VERTICAL
//                )
//            )
//        }
//        recyclerView.adapter!!.notifyDataSetChanged()
//    }
//
//    private fun addRating(content: String, rating: Int, bookId: Int) {
//        val apiInterface =
//            ApiInterface.create().addBookComment("addBookComment", content, rating, bookId, ApiInterface.USER_ID)
//
//        apiInterface.enqueue(object : Callback<NewCommentItem> {
//
//            override fun onResponse(
//                call: Call<NewCommentItem>,
//                response: Response<NewCommentItem>
//            ) {
//                if (response.isSuccessful) {
//                    Log.i("addresponse", "post submitted to API." + response.body().toString())
//                }
//            }
//
//            override fun onFailure(call: Call<NewCommentItem>, t: Throwable?) {
//                Log.d("Wystąpił błąd, spróbuj ponownie później", t.toString())
//
//            }
//        })
//    }
//
//    private fun initToolbar() {
//        val toolbar: Toolbar = findViewById(R.id.book_detail_toolbar)
//        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.title = "Powrót"
//
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
