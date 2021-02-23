package com.example.itunes

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import retrofit2.http.DELETE

@Dao
interface MusicDao {

    @Query("SELECT * FROM music_data")
    fun getAllResults(): List<ResultsOff>


    @Insert
    fun insertAll(vararg resultsData: ResultsOff)

    @Delete
    fun delete(resultData2: ResultsOff)

    @Query("DELETE FROM music_data")
    fun deleteAll()


}