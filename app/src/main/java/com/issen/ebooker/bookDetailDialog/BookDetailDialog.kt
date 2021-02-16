package com.issen.ebooker.bookDetailDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.issen.ebooker.EBookerApplication
import com.issen.ebooker.databinding.DialogBookDetailBinding

class BookDetailDialog : BottomSheetDialogFragment(), BookDetailDialogListener {

    private val safeArgs: BookDetailDialogArgs by navArgs()
    val viewModel: BookDetailDialogViewModel by viewModels {
        BookDetailDialogViewModelFactory(
            (requireActivity().application as EBookerApplication).booksRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DialogBookDetailBinding.inflate(inflater, container, false)
        viewModel.book = safeArgs.book
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun readAsEPub(link: String) {
        //todo download epub and start reader
    }

    override fun readAsPdf(link: String) {
        //todo download pdf and start reader
    }

    override fun toggleFavourite() {
        viewModel.toggleFavourite()
    }

    override fun showQuotes(id: String) {
        //todo navigate to book quotes list
    }

    override fun showReviews(id: String) {
        //todo navigate to book reviews list(fragment or dialog)
    }

}