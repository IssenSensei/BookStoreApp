package com.issen.ebooker.bookEPubReader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.issen.ebooker.EBookerApplication
import com.issen.ebooker.R


class BookEPubReaderFragment : Fragment() {

    private val viewModel: BookEPubReaderViewModel by viewModels {
        BookEPubReaderViewModelFactory(
            (requireActivity().application as EBookerApplication).booksRepository
        )
    }
    private val safeArgs: BookEPubReaderFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_book_e_pub_reader, container, false)
        viewModel.bookLink = safeArgs.url
//        val config: Config = Config()
//            .setAllowedDirection(Config.AllowedDirection.ONLY_VERTICAL)
//            .setDirection(Config.Direction.VERTICAL)
//            .setFont(Constants.FONT_LORA)
//            .setFontSize(2)
//            .setNightMode(true)
//            .setThemeColor(R.color.app_green)
//            .setShowTts(true)
//
//        val folioReader = FolioReader.get()
//            .setConfig(config, true)
//            .openBook("file:///android_asset/TheSilverChair.epub")
//

        return view
    }

}