package com.issen.ebooker.bookReviewsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.issen.ebooker.data.BooksRepository
import com.issen.ebooker.data.domain.Review
import io.bloco.faker.Faker
import kotlinx.coroutines.launch
import kotlin.random.Random

class BookReviewsListViewModel(private val booksRepository: BooksRepository, val bookId: String) : ViewModel() {

    private val faker = Faker()

    private val _bookTitle = MutableLiveData<String>()
    val bookTitle: LiveData<String>
        get() = _bookTitle

    val database = Firebase.database
    var databaseRef = database.getReference("reviews")

    init {
        databaseRef = databaseRef.child(bookId)
        viewModelScope.launch {
            _bookTitle.value = booksRepository.getBookTitle(bookId)
            for (i in 0..Random.nextInt(0, 15)) {
                val key = databaseRef.push().key
                databaseRef.child(key!!).setValue(
                    Review(
                        key,
                        faker.name.name(),
                        bookTitle.value ?: "",
                        faker.lorem.sentence(Random.nextInt(1, 50)),
                        0 + Random.nextFloat() * (5)
                    )
                )
            }
        }
    }
}