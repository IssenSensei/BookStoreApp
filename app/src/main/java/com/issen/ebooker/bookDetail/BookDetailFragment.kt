package com.issen.ebooker.bookDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.issen.ebooker.EBookerApplication
import com.issen.ebooker.data.domain.Book
import com.issen.ebooker.databinding.FragmentBookDetailBinding
import kotlinx.android.synthetic.main.activity_main.*

class BookDetailFragment : Fragment(), AuthorListener, BookDetailListener {

    private val viewModel: BookDetailViewModel by viewModels {
        BookDetailViewModelFactory(
            (requireActivity().application as EBookerApplication).booksRepository
        )
    }
    private val navArgs: BookDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBookDetailBinding.inflate(inflater, container, false)
        viewModel.book = navArgs.book
        requireActivity().toolbar.title = viewModel.book.title

        val adapter = BookAuthorsRecyclerViewAdapter(viewModel.book.authors ?: listOf(), this)
        binding.bookDetailAuthorRecycler.adapter = adapter

        binding.viewModel = viewModel
        binding.listener = this
        binding.lifecycleOwner = this
        return binding.root
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
