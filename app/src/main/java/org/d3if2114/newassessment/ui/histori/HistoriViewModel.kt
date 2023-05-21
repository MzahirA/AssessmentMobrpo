package org.d3if2114.newassessment.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2114.newassessment.db.LuasDao

class HistoriViewModel(private val db: LuasDao) : ViewModel() {


    val data = db.getLastBmi()

    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }

}