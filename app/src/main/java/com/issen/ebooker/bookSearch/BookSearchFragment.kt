package com.issen.ebooker.bookSearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.issen.ebooker.EBookerApplication
import com.issen.ebooker.common.BookListListener
import com.issen.ebooker.common.BookListRecyclerViewAdapter
import com.issen.ebooker.data.domain.Book
import com.issen.ebooker.databinding.FragmentBookSearchBinding

class BookSearchFragment : Fragment(), BookListListener {

    private lateinit var binding: FragmentBookSearchBinding
    private val viewModel: BookSearchViewModel by viewModels {
        BookSearchViewModelFactory(
            (requireActivity().application as EBookerApplication).booksRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookSearchBinding.inflate(layoutInflater, container, false)

        val adapter = BookListRecyclerViewAdapter(this)
        viewModel.bookList.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        binding.bookSearchSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateFilter(newText ?: "")
                return false
            }

        })

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.bookSearchList.adapter = adapter
        return binding.root
    }

    override fun onBookSelected(book: Book) {
        findNavController().navigate(BookSearchFragmentDirections.actionNavBookSearchToNavBookDetail(book))
    }

}