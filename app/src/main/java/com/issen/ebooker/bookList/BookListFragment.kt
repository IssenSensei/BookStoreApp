package com.issen.ebooker.bookList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.issen.ebooker.EBookerApplication
import com.issen.ebooker.data.domain.Book
import com.issen.ebooker.databinding.FragmentBookListBinding
import com.issen.ebooker.common.BookListListener
import com.issen.ebooker.common.BookListRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class BookListFragment : Fragment(), BookListListener {

    private lateinit var binding: FragmentBookListBinding
    private val safeArgs: BookListFragmentArgs by navArgs()

    private val viewModel: BookListViewModel by viewModels {
        BookListViewModelFactory(
            (requireActivity().application as EBookerApplication).booksRepository,
            safeArgs.shelfId,
            safeArgs.author,
            safeArgs.publisher,
            (requireActivity().application as EBookerApplication)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookListBinding.inflate(inflater, container, false)

        val adapter = BookListRecyclerViewAdapter(this)
        viewModel.bookList.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })

        viewModel.listTitle.observe(viewLifecycleOwner, {
            requireActivity().toolbar.title = it
        })

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.bookListRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onBookSelected(book: Book) {
        findNavController().navigate(BookListFragmentDirections.actionNavBookListToNavBookDetail(book))
    }
}