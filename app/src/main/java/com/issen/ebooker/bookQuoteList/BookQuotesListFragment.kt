package com.issen.ebooker.bookQuoteList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.issen.ebooker.data.domain.Quotation
import com.issen.ebooker.databinding.FragmentBookQuoteListBinding

class BookQuotesListFragment : Fragment() {

    private val safeArgs: BookQuotesListFragmentArgs by navArgs()
    private val viewModel: BookQuotesListViewModel by viewModels {
        BookQuotesListViewModelFactory(
            safeArgs.bookId
        )
    }
    private lateinit var adapter: BookQuotesFirebaseRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBookQuoteListBinding.inflate(inflater, container, false)

        val options: FirebaseRecyclerOptions<Quotation> = FirebaseRecyclerOptions.Builder<Quotation>()
            .setQuery(viewModel.databaseRef, Quotation::class.java)
            .build()

        adapter = BookQuotesFirebaseRecyclerAdapter(options)
        adapter.registerAdapterDataObserver(
            BookQuotesFirebaseRecyclerObserver(
                binding.bookQuotesListRecyclerView,
                adapter,
                LinearLayoutManager(requireContext())
            )
        )
        binding.bookQuotesListRecyclerView.adapter = adapter
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onPause() {
        adapter.stopListening()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        adapter.startListening()
    }
}