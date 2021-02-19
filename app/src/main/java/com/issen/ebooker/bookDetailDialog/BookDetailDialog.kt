package com.issen.ebooker.bookDetailDialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.issen.ebooker.EBookerApplication
import com.issen.ebooker.databinding.DialogBookDetailBinding

class BookDetailDialog : BottomSheetDialogFragment(), BookDetailDialogListener {

    private val safeArgs: BookDetailDialogArgs by navArgs()
    val viewModel: BookDetailDialogViewModel by viewModels {
        BookDetailDialogViewModelFactory(
            (requireActivity().application as EBookerApplication).booksRepository,
            safeArgs.book
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DialogBookDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.listener = this
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun readAsEPub(link: String) {
        findNavController().navigate(BookDetailDialogDirections.actionNavBookDetailDialogToNavBookEPubReader(link))
    }

    override fun readAsPdf(link: String) {
        findNavController().navigate(BookDetailDialogDirections.actionNavBookDetailDialogToNavBookPdfReader(link))
    }

    override fun toggleFavourite() {
        viewModel.toggleFavourite()
    }

    override fun showQuotes(id: String) {
        findNavController().navigate(BookDetailDialogDirections.actionNavBookDetailDialogToNavBookQuoteList(id))
    }

    override fun showReviews(id: String) {
        findNavController().navigate(BookDetailDialogDirections.actionNavBookDetailDialogToNavBookReviewsList(id))
    }

}