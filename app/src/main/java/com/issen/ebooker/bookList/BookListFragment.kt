package com.issen.ebooker.bookList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.issen.ebooker.EBookerApplication
import com.issen.ebooker.databinding.FragmentBookListBinding

class BookListFragment : Fragment() {

    private lateinit var binding: FragmentBookListBinding
    private val safeArgs: BookListFragmentArgs by navArgs()

    private val viewModel: BookListViewModel by viewModels {
        BookListViewModelFactory(
            (requireActivity().application as EBookerApplication).booksRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookListBinding.inflate(inflater, container, false)
        if (safeArgs.shelfId != -1) {
            viewModel.getShelfBooks(safeArgs.shelfId)
        }

        val adapter = BookListRecyclerViewAdapter()
        viewModel.bookList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.bookListRecyclerView.adapter = adapter

        return binding.root
    }
}