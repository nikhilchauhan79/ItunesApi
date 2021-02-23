package com.example.itunes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "music_data")
data class ResultsOff (

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "wrapperType",defaultValue = "temp")
    var wrapperType: String?,

    @ColumnInfo(name = "kind",defaultValue = "temp")
    var kind: String?,

    @ColumnInfo(name = "collectionId",defaultValue = "temp")
    var collectionId: Int?,

    @ColumnInfo(name = "trackId",defaultValue = "temp")
    var trackId: Int?,

    @ColumnInfo(name = "artistName",defaultValue = "temp")
    var artistName: String?,

    @ColumnInfo(name = "collectionName",defaultValue = "temp")
    var collectionName: String?,

    @ColumnInfo(name = "trackName",defaultValue = "temp")
    var trackName: String?,

    @ColumnInfo(name = "collectionCensoredName",defaultValue = "temp")
    var collectionCensoredName: String?,

    @ColumnInfo(name = "trackCensoredName",defaultValue = "temp")
    var trackCensoredName: String?,

//    var collectionArtistViewUrl:String?,
    @ColumnInfo(name = "collectionViewUrl",defaultValue = "temp")
    var collectionViewUrl: String?,

    @ColumnInfo(name = "previewUrl",defaultValue = "temp")
    var previewUrl: String?,

    @ColumnInfo(name = "trackPrice",defaultValue = "temp")
    var trackPrice: String?,

    @ColumnInfo(name = "releaseDate",defaultValue = "temp")
    var releaseDate: String?,

    @ColumnInfo(name = "country",defaultValue = "temp")
    var country: String?,

    @ColumnInfo(name = "primaryGenreName",defaultValue = "temp")
    var primaryGenreName: String?,

    @ColumnInfo(name = "artworkUrl100",defaultValue = "temp")
    var artworkUrl100: String?


)