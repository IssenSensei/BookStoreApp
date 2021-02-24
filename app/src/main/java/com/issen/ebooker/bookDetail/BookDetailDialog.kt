package com.issen.ebooker.bookDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.issen.ebooker.EBookerApplication
import com.issen.ebooker.R
import com.issen.ebooker.databinding.DialogBookDetailBinding

class BookDetailDialog : BottomSheetDialogFragment(), BookDetailDialogListener {

    private val sharedViewModel: BookDetailViewModel by activityViewModels {
        BookDetailViewModelFactory(
            (requireActivity().application as EBookerApplication).booksRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DialogBookDetailBinding.inflate(inflater, container, false)
        dialog?.window?.attributes?.windowAnimations = R.style.BottomSheetDialogAnimation
        binding.viewModel = sharedViewModel
        binding.listener = this
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun toggleFavourite() {
        sharedViewModel.toggleFavourite()
    }

    override fun toggleToRead() {
        sharedViewModel.toggleToRead()
    }

    override fun toggleHaveRead() {
        sharedViewModel.toggleHaveRead()
    }

    override fun showQuotes(id: String) {
        findNavController().navigate(BookDetailDialogDirections.actionNavBookDetailDialogToNavBookQuoteList(id))
    }

    override fun showReviews(id: String) {
        findNavController().navigate(BookDetailDialogDirections.actionNavBookDetailDialogToNavBookReviewsList(id))
    }

    override fun addReview(id: String) {
        sharedViewModel.setAddReview(true)
        findNavController().popBackStack()
    }

}