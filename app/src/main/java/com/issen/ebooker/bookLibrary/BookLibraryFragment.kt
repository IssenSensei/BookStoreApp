package com.issen.ebooker.bookLibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.issen.ebooker.EBookerApplication
import com.issen.ebooker.data.domain.Book
import com.issen.ebooker.databinding.FragmentBookLibraryBinding

class BookLibraryFragment : Fragment(), LibraryListener {

    private val viewModel: BookLibraryViewModel by viewModels {
        BookLibraryViewModelFactory(
            (requireActivity().application as EBookerApplication).booksRepository
        )
    }
    private lateinit var binding: FragmentBookLibraryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookLibraryBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.listener = this

        bindRecyclerAdapters()
        return binding.root
    }

    private fun bindRecyclerAdapters() {
        val readingAdapter = BookLibraryRecyclerViewAdapter(this)
        viewModel.readingList.observe(viewLifecycleOwner, Observer {
            readingAdapter.submitList(it)
        })
        binding.libraryReadingRecycler.adapter = readingAdapter

        val toReadAdapter = BookLibraryRecyclerViewAdapter(this)
        viewModel.toReadList.observe(viewLifecycleOwner, Observer {
            toReadAdapter.submitList(it)
        })
        binding.libraryToReadRecycler.adapter = toReadAdapter

        val favouritesAdapter = BookLibraryRecyclerViewAdapter(this)
        viewModel.favouriteList.observe(viewLifecycleOwner, Observer {
            favouritesAdapter.submitList(it)
        })
        binding.libraryFavouritesRecycler.adapter = favouritesAdapter

        val haveReadAdapter = BookLibraryRecyclerViewAdapter(this)
        viewModel.haveReadList.observe(viewLifecycleOwner, Observer {
            haveReadAdapter.submitList(it)
        })
        binding.libraryHaveReadRecycler.adapter = haveReadAdapter

        val purchasedAdapter = BookLibraryRecyclerViewAdapter(this)
        viewModel.purchasedList.observe(viewLifecycleOwner, Observer {
            purchasedAdapter.submitList(it)
        })
        binding.libraryPurchasedRecycler.adapter = purchasedAdapter

        val eBooksAdapter = BookLibraryRecyclerViewAdapter(this)
        viewModel.eBooksList.observe(viewLifecycleOwner, Observer {
            eBooksAdapter.submitList(it)
        })
        binding.libraryEbooksRecycler.adapter = eBooksAdapter

        val reviewedAdapter = BookLibraryRecyclerViewAdapter(this)
        viewModel.reviewedList.observe(viewLifecycleOwner, Observer {
            reviewedAdapter.submitList(it)
        })
        binding.libraryReviewedRecycler.adapter = reviewedAdapter

        val recommendationsAdapter = BookLibraryRecyclerViewAdapter(this)
        viewModel.recommendationsList.observe(viewLifecycleOwner, Observer {
            recommendationsAdapter.submitList(it)
        })
        binding.libraryRecommendationsRecycler.adapter = recommendationsAdapter

        val recentlyViewedAdapter = BookLibraryRecyclerViewAdapter(this)
        viewModel.recentlyViewedList.observe(viewLifecycleOwner, Observer {
            recentlyViewedAdapter.submitList(it)
        })
        binding.libraryRecentlyViewedRecycler.adapter = recentlyViewedAdapter

    }

    override fun onBookSelected(book: Book) {
        findNavController().navigate(BookLibraryFragmentDirections.actionNavBookLibraryToNavBookDetail(book))
    }

    override fun onShelfSelected(shelfId: Int) {
        findNavController().navigate(BookLibraryFragmentDirections.actionNavBookLibraryToNavBookList(shelfId))
    }
}