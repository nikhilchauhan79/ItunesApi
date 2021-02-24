package com.example.itunes

import androidx.lifecycle.LiveData
import androidx.room.*
import retrofit2.http.DELETE

@Dao
interface MusicDao {

    @Query("SELECT * FROM music_data")
    fun getAllResults(): LiveData<List<ResultsOff>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg resultsData: ResultsOff)

    @Delete
    fun delete(resultData2: ResultsOff)

    @Query("DELETE FROM music_data")
    fun deleteAll()


}