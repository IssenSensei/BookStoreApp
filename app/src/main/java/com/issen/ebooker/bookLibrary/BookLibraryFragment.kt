package com.issen.ebooker.bookLibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.issen.ebooker.EBookerApplication
import com.issen.ebooker.databinding.FragmentBookLibraryBinding

class BookLibraryFragment : Fragment() {

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

        bindRecyclerAdapters()
        return binding.root
    }

    private fun bindRecyclerAdapters() {
        val readingAdapter = BookLibraryRecyclerViewAdapter()
        viewModel.readingList.observe(viewLifecycleOwner, Observer {
            readingAdapter.submitList(it)
        })
        binding.libraryReadingRecycler.adapter = readingAdapter

        val toReadAdapter = BookLibraryRecyclerViewAdapter()
        viewModel.toReadList.observe(viewLifecycleOwner, Observer {
            toReadAdapter.submitList(it)
        })
        binding.libraryToReadRecycler.adapter = toReadAdapter

        val favouritesAdapter = BookLibraryRecyclerViewAdapter()
        viewModel.favouriteList.observe(viewLifecycleOwner, Observer {
            favouritesAdapter.submitList(it)
        })
        binding.libraryFavouritesRecycler.adapter = favouritesAdapter

        val haveReadAdapter = BookLibraryRecyclerViewAdapter()
        viewModel.haveReadList.observe(viewLifecycleOwner, Observer {
            haveReadAdapter.submitList(it)
        })
        binding.libraryHaveReadRecycler.adapter = haveReadAdapter

        val purchasedAdapter = BookLibraryRecyclerViewAdapter()
        viewModel.purchasedList.observe(viewLifecycleOwner, Observer {
            purchasedAdapter.submitList(it)
        })
        binding.libraryPurchasedRecycler.adapter = purchasedAdapter

        val eBooksAdapter = BookLibraryRecyclerViewAdapter()
        viewModel.eBooksList.observe(viewLifecycleOwner, Observer {
            eBooksAdapter.submitList(it)
        })
        binding.libraryEbooksRecycler.adapter = eBooksAdapter

        val reviewedAdapter = BookLibraryRecyclerViewAdapter()
        viewModel.reviewedList.observe(viewLifecycleOwner, Observer {
            reviewedAdapter.submitList(it)
        })
        binding.libraryReviewedRecycler.adapter = reviewedAdapter

        val recommendationsAdapter = BookLibraryRecyclerViewAdapter()
        viewModel.recommendationsList.observe(viewLifecycleOwner, Observer {
            recommendationsAdapter.submitList(it)
        })
        binding.libraryRecommendationsRecycler.adapter = recommendationsAdapter

        val recentlyViewedAdapter = BookLibraryRecyclerViewAdapter()
        viewModel.recentlyViewedList.observe(viewLifecycleOwner, Observer {
            recentlyViewedAdapter.submitList(it)
        })
        binding.libraryRecentlyViewedRecycler.adapter = recentlyViewedAdapter

    }
}