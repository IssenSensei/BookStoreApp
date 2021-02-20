package com.issen.ebooker.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.issen.ebooker.data.local.models.DatabaseReviewItem

@Dao
interface ReviewDao {

    @Transaction
    @Query("select * from review_table")
    fun getReviews(): LiveData<List<DatabaseReviewItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveReview(databaseReviewItem: DatabaseReviewItem)
}