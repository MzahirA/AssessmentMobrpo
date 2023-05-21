package org.d3if2114.newassessment.model

import org.d3if2114.newassessment.db.LuasEntity

fun LuasEntity.hitungLuas(): HasilLuas {
    val luas = panjang * lebar
    val kategori =
        when {
            luas > 100 -> KategoriLuas.BESAR
            else -> KategoriLuas.KECIL
        }
    return HasilLuas(luas, kategori)
}