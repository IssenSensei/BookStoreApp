package com.issen.ebooker.bookDetail

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.issen.ebooker.EBookerApplication
import com.issen.ebooker.R
import com.issen.ebooker.databinding.DialogBookDetailReviewBinding

class BookDetailReviewDialog : DialogFragment() {

    private val sharedViewModel: BookDetailViewModel by activityViewModels {
        BookDetailViewModelFactory(
            (requireActivity().application as EBookerApplication).booksRepository
        )
    }
    lateinit var binding: DialogBookDetailReviewBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogBookDetailReviewBinding.inflate(layoutInflater, null, false)

        val dialog = AlertDialog.Builder(requireContext())
        dialog.apply {
            setView(binding.root)
            setTitle(getString(R.string.set_rating))
            setPositiveButton(getString(R.string.save)) { dialogInterface: DialogInterface, i: Int ->
                sharedViewModel.saveReview(binding.dialogBookDetailReviewContent.text.toString())
            }
            setNegativeButton(getString(R.string.cancel)) { dialogInterface: DialogInterface, i: Int ->
            }
        }

        binding.dialogBookDetailReviewRating.setOnRatingBarChangeListener { ratingBar, rating, b ->
            sharedViewModel.setRating(rating)
        }

        binding.viewModel = sharedViewModel
        binding.lifecycleOwner = this

        return dialog.create()
    }
}