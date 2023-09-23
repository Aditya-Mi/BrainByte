package com.example.brainbyte.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SpamViewModel(application: Application):AndroidViewModel(application) {

    private val spamNumberDao = SpamNumberDatabase.getDatabase(application).spamNumberDao()
    private val repository: SpamRepository
    val getAllData: LiveData<List<SpamNumber>>

    init {
        repository= SpamRepository(spamNumberDao)
        getAllData = repository.getAllData

    }

    fun insertNumber(spamNumber: SpamNumber){
        viewModelScope.launch (Dispatchers.IO){
            repository.insertData(spamNumber)
        }
    }

    fun updateNumber(spamNumber: SpamNumber){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateData(spamNumber)
        }
    }

    fun deleteNumber(spamNumber: SpamNumber){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteNumber(spamNumber)
        }
    }

    fun searchDatabase(number: String): LiveData<SpamNumber> {
        return repository.searchDatabase(number)
    }
}