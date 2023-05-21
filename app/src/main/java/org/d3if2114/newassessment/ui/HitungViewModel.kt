package org.d3if2114.newassessment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2114.newassessment.db.LuasDao
import org.d3if2114.newassessment.db.LuasEntity
import org.d3if2114.newassessment.model.HasilLuas
import org.d3if2114.newassessment.model.KategoriLuas

class HitungViewModel(private val db: LuasDao) : ViewModel() {


    private val hasilLuas = MutableLiveData<HasilLuas?>()
    private val navigasi = MutableLiveData<KategoriLuas?>()

    val data = db.getLastBmi()


    fun hitungLuas(panjang: Float, lebar : Float){
        val luas = panjang * lebar
        val kategori  = getKategori(luas)
        hasilLuas.value = HasilLuas(luas,kategori)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataLuas = LuasEntity(
                    panjang = panjang,
                    lebar = lebar
                )
                db.insert(dataLuas)
            }
        }

    }

    private fun getKategori(luas: Float): KategoriLuas {
        return when {
            luas > 100 -> KategoriLuas.BESAR
            else -> KategoriLuas.KECIL
        }
    }

    fun getHasilLuas(): LiveData<HasilLuas?> = hasilLuas

    fun mulaiNavigasi() {
        navigasi.value = hasilLuas.value?.kategori
    }
    fun selesaiNavigasi() {
        navigasi.value = null
    }
    fun getNavigasi() : LiveData<KategoriLuas?> = navigasi
}