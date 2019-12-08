package com.example.bookstoreapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bookstoreapp.database.ApiInterface
import com.folioreader.Config
import com.folioreader.Constants
import com.folioreader.FolioReader
import com.folioreader.model.HighLight
import com.folioreader.model.locators.ReadLocator.Companion.LOG_TAG
import com.github.barteksc.pdfviewer.util.FitPolicy
import kotlinx.android.synthetic.main.activity_book_reader.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class BookReaderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_reader)
        val bookId = intent.getIntExtra("bookId", 4)
        when (intent.getStringExtra("extension")!!) {
            ".pdf" -> {
                pdfView.fromFile(File(intent.getStringExtra("file")!!))
                    .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                    .enableSwipe(true) // allows to block changing pages using swipe
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(0)
                    // allows to draw something on the current page, usually visible in the middle of the screen
//                    .onDraw(onDrawListener)
//                    // allows to draw something on all pages, separately for every page. Called only for visible pages
//                    .onLoad(onLoadCompleteListener) // called after document is loaded and starts to be rendered
//                    .onPageChange(onPageChangeListener)
//                    .onPageScroll(onPageScrollListener)
//                    .onError(onErrorListener)
//                    .onPageError(onPageErrorListener)
//                    .onRender(onRenderListener) // called after document is rendered for the first time
//                    // called on single tap, return true if handled, false to toggle scroll handle visibility
//                    .onTap(onTapListener)
//                    .onLongPress(onLongPressListener)
                    .enableAnnotationRendering(true) // render annotations (such as comments, colors or forms)
                    .scrollHandle(null)
                    .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                    // spacing between pages in dp. To define spacing color, set view background
                    .spacing(0)
                    .autoSpacing(false) // add dynamic spacing to fit each page on its own on the screen
                    .pageFitPolicy(FitPolicy.WIDTH) // mode to fit pages in the view
                    .fitEachPage(false) // fit each page to the view, else smaller pages are scaled relative to largest page.
                    .pageSnap(false) // snap pages to screen boundaries
                    .pageFling(false) // make a fling change only a single page like ViewPager
                    .nightMode(false) // toggle night mode
                    .load()
            }
            ".epub" -> {

                val config = Config()
                    .setAllowedDirection(Config.AllowedDirection.ONLY_VERTICAL)
                    .setDirection(Config.Direction.VERTICAL)
                    .setFont(Constants.FONT_LORA)
                    .setFontSize(2)
                    .setNightMode(true)
                    .setShowTts(true)

                val folioReader = FolioReader.get()
                    .setConfig(config, false)
                folioReader.openBook(intent.getStringExtra("file"))

                folioReader.setOnHighlightListener { highLight: HighLight, highLightAction: HighLight.HighLightAction ->
                    if(highLightAction == HighLight.HighLightAction.NEW){
                            val apiInterface = ApiInterface.create().addBookQuote("addBookQuote", highLight.content, bookId, 1)

                            apiInterface.enqueue(object : Callback<Int> {

                                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                                    if(response.isSuccessful) {
                                        Log.i("addresponse", "post submitted to API." + response.body().toString())
                                    }
                                }

                                override fun onFailure(call: Call<Int>, t: Throwable?) {
                                    Log.d("qpablad", t.toString())

                                }
                            })

                    }
                }

                folioReader.setReadLocatorListener { readLocator ->
                    Log.i(LOG_TAG, "-> saveReadLocator -> " + readLocator.toJson()!!)
                }

            }
        }
    }
}
