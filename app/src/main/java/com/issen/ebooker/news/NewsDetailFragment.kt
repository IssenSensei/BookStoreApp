package com.issen.ebooker.news

import androidx.fragment.app.Fragment

class NewsDetailFragment : Fragment() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_news_details)
//        initToolbar()
//
//        val data: NewsItem = intent.getSerializableExtra("data") as NewsItem
//
//        val content = findViewById<TextView>(R.id.content)!!
//        val title = findViewById<TextView>(R.id.register_title)!!
//        val bookStore = findViewById<TextView>(R.id.bookStore)!!
//        val photo = findViewById<ImageView>(R.id.image)!!
//
//        content.text = data.content
//        title.text = data.title
//        bookStore.text = data.bookStore
//        Glide.with(applicationContext)
//            .load(ApiInterface.photoPath + data.photo)
//            .into(photo)
//    }
//
//    private fun initToolbar() {
//        val toolbar: Toolbar = findViewById(R.id.news_toolbar)
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
