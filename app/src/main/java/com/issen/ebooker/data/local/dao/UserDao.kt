package com.issen.ebooker.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.issen.ebooker.data.local.models.DatabaseUser

@Dao
interface UserDao {

    @Transaction
    @Query("select * from user_table")
    fun getUsers(): LiveData<List<DatabaseUser>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(databaseUser: DatabaseUser)
}