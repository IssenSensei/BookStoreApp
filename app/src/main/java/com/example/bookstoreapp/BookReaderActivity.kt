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
import com.github.barteksc.pdfviewer.listener.OnLongPressListener
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
                    .pages(0, 2, 1, 3, 3, 3)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(0)
//                    .onLoad(onLoadCompleteListener)
//                    .onPageChange(onPageChangeListener)
//                    .onPageScroll(onPageScrollListener)
//                    .onError(onErrorListener)
//                    .onPageError(onPageErrorListener)
//                    .onRender(onRenderListener)
//                    .onTap(onTapListener)
//                    .onLongPress(onLongPressListener)
                    .enableAnnotationRendering(true)
                    .scrollHandle(null)
                    .enableAntialiasing(true)
                    .spacing(0)
                    .autoSpacing(false)
                    .pageFitPolicy(FitPolicy.WIDTH)
                    .fitEachPage(false)
                    .pageSnap(false)
                    .pageFling(false)
                    .nightMode(false)
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

                folioReader.setOnHighlightListener {
                        highLight: HighLight,
                        highLightAction: HighLight.HighLightAction ->
                    if(highLightAction == HighLight.HighLightAction.NEW){
                            val apiInterface = ApiInterface.create()
                                .addBookQuote("addBookQuote", highLight.content,
                                    bookId, ApiInterface.USER_ID)

                            apiInterface.enqueue(object : Callback<Int> {
                                override fun onResponse(call: Call<Int>, response: Response<Int>) {
                                    if(response.isSuccessful) {
                                        Log.i("addresponse",
                                            "post submitted to API." + response.body().toString())
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
