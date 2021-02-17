package com.issen.ebooker.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.issen.ebooker.data.local.models.DatabaseQuotationItem

@Dao
interface QuotationDao {

    @Transaction
    @Query("select * from quotation_table")
    fun getQuotes(): LiveData<List<DatabaseQuotationItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(databaseQuotationItem: DatabaseQuotationItem)
}