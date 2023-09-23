package com.example.brainbyte.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.brainbyte.database.SpamNumber

@Dao
interface SpamNumberDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNumber(spamNumber: SpamNumber)

    @Query("SELECT * FROM spam_table WHERE number LIKE :number")
    fun searchDatabase(number: String): LiveData<SpamNumber>

    @Update
    suspend fun updateNumber(spamNumber: SpamNumber)

    @Delete
    suspend fun deleteNumber(spamNumber: SpamNumber)

    @Query("SELECT * FROM spam_table ORDER BY reports ASC")
    fun getAllData():LiveData<List<SpamNumber>>
}