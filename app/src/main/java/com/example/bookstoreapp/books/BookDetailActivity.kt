package com.example.bookstoreapp.books

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookstoreapp.BookReaderActivity
import com.example.bookstoreapp.R
import com.example.bookstoreapp.author.AuthorBooksActivity
import com.example.bookstoreapp.publisher.PublisherBooksActivity
import com.example.bookstoreapp.comments.CommentItem
import com.example.bookstoreapp.comments.CommentsRecyclerViewAdapter
import com.example.bookstoreapp.comments.NewCommentItem
import com.example.bookstoreapp.database.ApiInterface
import com.example.bookstoreapp.database.ApiInterface.Companion.AUTHOR_CODE
import com.example.bookstoreapp.database.ApiInterface.Companion.BOOK_READER_CODE
import com.example.bookstoreapp.database.ApiInterface.Companion.PUBLISHER_CODE
import com.example.bookstoreapp.database.getAsTempFile
import com.example.bookstoreapp.utils.LineItemDecoration
import com.example.bookstoreapp.utils.SharedPreference
import io.ktor.client.HttpClient
import kotlinx.android.synthetic.main.activity_book_details.*
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class BookDetailActivity : AppCompatActivity() {

    private lateinit var commentsMap: MutableList<CommentItem>
    private lateinit var recyclerView: RecyclerView
    var id = 0
    private lateinit var currentTheme: String
    private lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        sharedPreference = SharedPreference(this)
        currentTheme = sharedPreference.getValueString("current_theme").toString()
        setAppTheme(currentTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        val data: BooksItem = intent.getSerializableExtra("data") as BooksItem
        id = intent.getSerializableExtra("id") as Int

        authorView.text = data.author
        publisherView.text = data.publisher
        yearView.text = data.year
        bookDescription.text = data.description
        bookTitle.text = data.title
        Glide.with(applicationContext)
            .load(data.picture)
            .into(image)

        showComments.setOnClickListener {
            showCommentDialog()
        }

        authorView.setOnClickListener {
            val intent = Intent(this, AuthorBooksActivity::class.java)
            intent.putExtra("authorId", data.author)
            startActivityForResult(intent, AUTHOR_CODE)
        }

        publisherView.setOnClickListener {
            val intent = Intent(this, PublisherBooksActivity::class.java)
            intent.putExtra("publisherId", data.publisher)
            startActivityForResult(intent, PUBLISHER_CODE)
        }

        fab.setOnClickListener {
            runBlocking {
                val client = HttpClient {
                    followRedirects = true
                }
                client.getAsTempFile(data.file) { file ->
                    println(file.readBytes().size)

                    val ebookDirectory = File(getExternalFilesDir(null), "Ebook")
                    ebookDirectory.mkdirs()

                    val bookName = data.file.substring(data.file.lastIndexOf("/"))
                    val extension = data.file.substring(data.file.lastIndexOf("."))
                    val newfile = File(ebookDirectory, bookName)

                    val ebookPath = ebookDirectory.toString() + bookName

                    file.copyTo(newfile, overwrite = true)

                    val intent = Intent(applicationContext, BookReaderActivity::class.java)
                    intent.putExtra("file", ebookPath)
                    intent.putExtra("extension", extension)
                    intent.putExtra("bookId", id)

                    startActivityForResult(intent, BOOK_READER_CODE)
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        val theme = sharedPreference.getValueString("current_theme")
        if(currentTheme != theme)
            recreate()
    }

    private fun setAppTheme(currentTheme: String) {
        when (currentTheme) {
            "THEME_DARKISH" -> setTheme(R.style.Theme_App_Darkish)
            "THEME_PURPLISH" -> setTheme(R.style.Theme_App_Purplish)
            "THEME_GREENISH" -> setTheme(R.style.Theme_App_Greenish)
            "THEME_FULLWHITE" -> setTheme(R.style.Theme_App_FullWhite)
            else -> setTheme(R.style.Theme_App_Whitish)
        }
    }

    private fun showCommentDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.dialog_add_book_review)
        dialog.setCancelable(true)

        recyclerView = dialog.findViewById(R.id.comment_list)
        commentsMap = mutableListOf()
        recyclerView.adapter = CommentsRecyclerViewAdapter(commentsMap)

        getData()

        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        val content = dialog.findViewById(R.id.rating_content) as EditText
        val rating = dialog.findViewById(R.id.rating_bar) as AppCompatRatingBar
        (dialog.findViewById(R.id.bt_cancel) as AppCompatButton).setOnClickListener { dialog.dismiss() }

        (dialog.findViewById(R.id.bt_submit) as AppCompatButton).setOnClickListener {
            addRating(
                content.text.toString().trim(),
                rating.rating.toInt(),
                id
            )
            dialog.dismiss()
        }

        dialog.show()
        dialog.window!!.attributes = lp
    }

    private fun getData() {
        val apiInterface = ApiInterface.create().getBookComments("getBookComments", id)
        apiInterface.enqueue(object : Callback<List<CommentItem>> {

            override fun onResponse(
                call: Call<List<CommentItem>>?,
                response: Response<List<CommentItem>>?
            ) {
                if (response?.body() != null) {
                    setCommentListItems(response.body()!! as MutableList)
                }
            }

            override fun onFailure(call: Call<List<CommentItem>>?, t: Throwable?) {
                Toast.makeText(
                    applicationContext,
                    "Wystąpił problem przy pobieraniu danych",
                    Toast.LENGTH_SHORT
                ).show()

            }
        })
    }

    fun setCommentListItems(commentList: MutableList<CommentItem>) {

        this.commentsMap.clear()
        this.commentsMap.addAll(commentList)
        if (commentsMap.size == 0)
        else {
            recyclerView.layoutManager = LinearLayoutManager(this.applicationContext)
            recyclerView.addItemDecoration(
                LineItemDecoration(
                    this.applicationContext,
                    LinearLayout.VERTICAL
                )
            )
        }
        recyclerView.adapter!!.notifyDataSetChanged()
    }

    private fun addRating(content: String, rating: Int, bookId: Int) {
        val apiInterface =
            ApiInterface.create().addBookComment("addBookComment", content, rating, bookId, ApiInterface.USER_ID)

        apiInterface.enqueue(object : Callback<NewCommentItem> {

            override fun onResponse(
                call: Call<NewCommentItem>,
                response: Response<NewCommentItem>
            ) {
                if (response.isSuccessful) {
                    Log.i("addresponse", "post submitted to API." + response.body().toString())
                }
            }

            override fun onFailure(call: Call<NewCommentItem>, t: Throwable?) {
                Log.d("qpablad", t.toString())

            }
        })
    }
}
