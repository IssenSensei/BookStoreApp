package com.issen.ebooker.bookLibrary

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
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
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var readingAdapter: BookLibraryRecyclerViewAdapter
    private lateinit var toReadAdapter: BookLibraryRecyclerViewAdapter
    private lateinit var favouritesAdapter: BookLibraryRecyclerViewAdapter
    private lateinit var haveReadAdapter: BookLibraryRecyclerViewAdapter
    private lateinit var purchasedAdapter: BookLibraryRecyclerViewAdapter
    private lateinit var eBooksAdapter: BookLibraryRecyclerViewAdapter
    private lateinit var reviewedAdapter: BookLibraryRecyclerViewAdapter
    private lateinit var recommendationsAdapter: BookLibraryRecyclerViewAdapter
    private lateinit var recentlyViewedAdapter: BookLibraryRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookLibraryBinding.inflate(inflater, container, false)
        sharedPreferences = getDefaultSharedPreferences(requireContext())
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.listener = this

        viewModel.checkVisibilitySettings(sharedPreferences)
        bindRecyclerAdapters()
        observeListData()
        return binding.root
    }

    private fun bindRecyclerAdapters() {
        readingAdapter = BookLibraryRecyclerViewAdapter(this)
        binding.libraryReadingRecycler.adapter = readingAdapter

        toReadAdapter = BookLibraryRecyclerViewAdapter(this)
        binding.libraryToReadRecycler.adapter = toReadAdapter

        favouritesAdapter = BookLibraryRecyclerViewAdapter(this)
        binding.libraryFavouritesRecycler.adapter = favouritesAdapter

        haveReadAdapter = BookLibraryRecyclerViewAdapter(this)
        binding.libraryHaveReadRecycler.adapter = haveReadAdapter

        purchasedAdapter = BookLibraryRecyclerViewAdapter(this)
        binding.libraryPurchasedRecycler.adapter = purchasedAdapter

        eBooksAdapter = BookLibraryRecyclerViewAdapter(this)
        binding.libraryEbooksRecycler.adapter = eBooksAdapter

        reviewedAdapter = BookLibraryRecyclerViewAdapter(this)
        binding.libraryReviewedRecycler.adapter = reviewedAdapter

        recommendationsAdapter = BookLibraryRecyclerViewAdapter(this)
        binding.libraryRecommendationsRecycler.adapter = recommendationsAdapter

        recentlyViewedAdapter = BookLibraryRecyclerViewAdapter(this)
        binding.libraryRecentlyViewedRecycler.adapter = recentlyViewedAdapter
    }


    private fun observeListData() {
        viewModel.areReadingNowObserved.observe(viewLifecycleOwner, {
            if (it) {
                viewModel.readingList.observe(viewLifecycleOwner, { list ->
                    readingAdapter.submitList(list)
                })
            } else {
                viewModel.readingList.removeObservers(this)
            }
        })
        viewModel.areToReadObserved.observe(viewLifecycleOwner, {
            if (it) {
                viewModel.toReadList.observe(viewLifecycleOwner, { list ->
                    toReadAdapter.submitList(list)
                })
            } else {
                viewModel.toReadList.removeObservers(this)
            }
        })
        viewModel.areFavouritesObserved.observe(viewLifecycleOwner, {
            if (it) {
                viewModel.favouriteList.observe(viewLifecycleOwner, { list ->
                    favouritesAdapter.submitList(list)
                })
            } else {
                viewModel.favouriteList.removeObservers(this)
            }
        })
        viewModel.areHaveReadObserved.observe(viewLifecycleOwner, {
            if (it) {
                viewModel.haveReadList.observe(viewLifecycleOwner, { list ->
                    haveReadAdapter.submitList(list)
                })
            } else {
                viewModel.haveReadList.removeObservers(this)
            }
        })
        viewModel.arePurchasedObserved.observe(viewLifecycleOwner, {
            if (it) {
                viewModel.purchasedList.observe(viewLifecycleOwner, { list ->
                    purchasedAdapter.submitList(list)
                })
            } else {
                viewModel.purchasedList.removeObservers(this)
            }
        })
        viewModel.areEBooksObserved.observe(viewLifecycleOwner, {
            if (it) {
                viewModel.eBooksList.observe(viewLifecycleOwner, { list ->
                    eBooksAdapter.submitList(list)
                })
            } else {
                viewModel.eBooksList.removeObservers(this)
            }
        })
        viewModel.areReviewedObserved.observe(viewLifecycleOwner, {
            if (it) {
                viewModel.reviewedList.observe(viewLifecycleOwner, { list ->
                    reviewedAdapter.submitList(list)
                })
            } else {
                viewModel.reviewedList.removeObservers(this)
            }
        })
        viewModel.areRecommendationsObserved.observe(viewLifecycleOwner, {
            if (it) {
                viewModel.recommendationsList.observe(viewLifecycleOwner, { list ->
                    recommendationsAdapter.submitList(list)
                })
            } else {
                viewModel.recommendationsList.removeObservers(this)
            }
        })
        viewModel.areRecentlyViewedObserved.observe(viewLifecycleOwner, {
            if (it) {
                viewModel.recentlyViewedList.observe(viewLifecycleOwner, { list ->
                    recentlyViewedAdapter.submitList(list)
                })
            } else {
                viewModel.recentlyViewedList.removeObservers(this)
            }
        })
    }

    override fun onBookSelected(book: Book) {
        findNavController().navigate(BookLibraryFragmentDirections.actionNavBookLibraryToNavBookDetail(book))
    }

    override fun onShelfSelected(shelfId: Int) {
        findNavController().navigate(BookLibraryFragmentDirections.actionNavBookLibraryToNavBookList(shelfId))
    }
}