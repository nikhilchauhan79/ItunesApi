package com.example.itunes

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlin.math.log


class MusicRepository{
    companion object{
        lateinit var musicDatabase: MusicDatabase
        var labels:LiveData<List<ResultsOff>>?=null

        private fun initializeDB(context: Context): MusicDatabase?{
            return MusicDatabase.getInstance(context)

        }

        fun insertData(context: Context,resultsOff: ResultsOff) {

            musicDatabase = initializeDB(context)!!

            CoroutineScope(IO).launch {
                musicDatabase!!.musicDao().insertAll(resultsOff)
            }


        }

        fun getAllResults(context: Context) : LiveData<List<ResultsOff>>? {

            musicDatabase = initializeDB(context)!!
            labels= musicDatabase.musicDao().getAllResults()


            return labels
        }




    }

}