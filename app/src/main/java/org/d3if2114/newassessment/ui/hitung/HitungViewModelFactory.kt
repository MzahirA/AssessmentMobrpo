package org.d3if2114.newassessment.ui.hitung

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if2114.newassessment.db.LuasDao
import org.d3if2114.newassessment.ui.HitungViewModel

class HitungViewModelFactory(
    private val db: LuasDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HitungViewModel::class.java)) {
            return HitungViewModel(db) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
