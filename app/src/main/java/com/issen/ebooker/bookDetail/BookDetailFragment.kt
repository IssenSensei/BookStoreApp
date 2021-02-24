package com.issen.ebooker.bookDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.issen.ebooker.EBookerApplication
import com.issen.ebooker.R
import com.issen.ebooker.data.domain.Book
import com.issen.ebooker.databinding.FragmentBookDetailBinding
import kotlinx.android.synthetic.main.activity_main.*

class BookDetailFragment : Fragment(), AuthorListener, BookDetailListener {

    private val navArgs: BookDetailFragmentArgs by navArgs()
    private val sharedViewModel: BookDetailViewModel by activityViewModels {
        BookDetailViewModelFactory(
            (requireActivity().application as EBookerApplication).booksRepository
        )
    }
    private lateinit var binding: FragmentBookDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        sharedViewModel.setSelectedBook(navArgs.book)
        requireActivity().toolbar.title = sharedViewModel.book.title

        val adapter = BookAuthorsRecyclerViewAdapter(sharedViewModel.book.authors ?: listOf(), this)
        binding.bookDetailAuthorRecycler.adapter = adapter

        sharedViewModel.addReview.observe(viewLifecycleOwner, Observer {
            if (it) {
                sharedViewModel.setAddReview(false)
                addReview()
            }
        })

        binding.viewModel = sharedViewModel
        binding.listener = this
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun addReview() {
        findNavController().navigate(R.id.nav_book_detail_review_dialog)
    }

    override fun onAuthorClicked(name: String) {
        findNavController().navigate(BookDetailFragmentDirections.actionNavBookDetailToNavBookList(author = name))
    }

    override fun onThumbnailClicked(url: String) {
        findNavController().navigate(BookDetailFragmentDirections.actionNavBookDetailToNavBookCover(url))
    }

    override fun onPublisherClicked(publisher: String) {
        findNavController().navigate(BookDetailFragmentDirections.actionNavBookDetailToNavBookList(publisher = publisher))
    }

    override fun onShowMoreClicked(book: Book) {
        findNavController().navigate(BookDetailFragmentDirections.actionNavBookDetailToNavBookDetailDialog(book))
    }
}
