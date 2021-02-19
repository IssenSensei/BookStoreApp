package com.issen.ebooker.bookQuoteList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.issen.ebooker.EBookerApplication
import com.issen.ebooker.R
import com.issen.ebooker.bookReviewsList.BookReviewsFirebaseRecyclerObserver
import com.issen.ebooker.data.local.models.DatabaseQuotationItem
import com.issen.ebooker.data.local.models.DatabaseReviewItem
import com.issen.ebooker.databinding.FragmentBookQuoteListBinding

class BookQuotesListFragment : Fragment() {

    private val safeArgs: BookQuotesListFragmentArgs by navArgs()
    private val viewModel: BookQuotesListViewModel by viewModels {
        BookQuotesListViewModelFactory(
            (requireActivity().application as EBookerApplication).booksRepository,
            safeArgs.bookId
        )
    }
    private val auth = FirebaseAuth.getInstance()
    private lateinit var adapter: BookQuotesFirebaseRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentBookQuoteListBinding.inflate(inflater, container, false)

        val options: FirebaseRecyclerOptions<DatabaseQuotationItem> = FirebaseRecyclerOptions.Builder<DatabaseQuotationItem>()
            .setQuery(viewModel.databaseRef, DatabaseQuotationItem::class.java)
            .build()

        viewModel.databaseRef.push().setValue(
            DatabaseQuotationItem(
                0,
                "quote content",
                viewModel.bookId,
                auth.currentUser!!.uid
            )
        )

        adapter = BookQuotesFirebaseRecyclerAdapter(options)
        adapter.registerAdapterDataObserver(
            BookReviewsFirebaseRecyclerObserver(
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