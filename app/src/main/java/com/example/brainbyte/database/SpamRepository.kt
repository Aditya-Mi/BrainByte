package com.example.brainbyte.database

import androidx.lifecycle.LiveData
import com.example.brainbyte.database.SpamNumber
import com.example.brainbyte.database.SpamNumberDao

class SpamRepository(private val spamNumberDao: SpamNumberDao) {
    val getAllData: LiveData<List<SpamNumber>> = spamNumberDao.getAllData()

    suspend fun insertData(spamNumber: SpamNumber){
        spamNumberDao.insertNumber(spamNumber)
    }

    suspend fun updateData(spamNumber: SpamNumber){
        spamNumberDao.updateNumber(spamNumber)
    }

    suspend fun deleteNumber(spamNumber: SpamNumber){
        spamNumberDao.deleteNumber(spamNumber)
    }

    fun searchDatabase(number: String): LiveData<SpamNumber> {
        return spamNumberDao.searchDatabase(number)
    }
}