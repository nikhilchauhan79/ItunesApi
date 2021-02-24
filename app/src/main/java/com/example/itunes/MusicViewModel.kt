package com.example.itunes

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

public class MusicViewModel:ViewModel() {
    var musicLiveData:LiveData<List<ResultsOff>>?=null

    fun insertData(context: Context,resultsOff: ResultsOff){
        MusicRepository.insertData(context,resultsOff)
    }

    fun getLabelsDetails(context: Context):LiveData<List<ResultsOff>>{
        musicLiveData=MusicRepository.getAllResults(context)
        return musicLiveData!!
    }

}