package org.d3if2114.newassessment.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LuasDao {
    @Insert
    fun insert(bmi: LuasEntity)

    @Query("SELECT * FROM luas ORDER BY id DESC")
    fun getLastBmi(): LiveData<List<LuasEntity>>

    @Query("DELETE FROM luas")
    fun clearData()

}