package com.issen.ebooker.bookQuoteList

import androidx.lifecycle.ViewModel
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.issen.ebooker.data.domain.Quotation
import io.bloco.faker.Faker
import kotlin.random.Random

class BookQuotesListViewModel(val bookId: String) : ViewModel() {
    var databaseRef = Firebase.database.getReference("quotes")
    private val faker = Faker()

    init {
        databaseRef = databaseRef.child(bookId)

        for(i in 0..Random.nextInt(0, 15)){
            val key = databaseRef.push().key
            databaseRef.child(key!!).setValue(
                Quotation(
                    key,
                    faker.lorem.sentence(Random.nextInt(1, 50))
                )
            )
        }
    }
}