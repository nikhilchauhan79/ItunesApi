package com.example.itunes

import androidx.annotation.Nullable

data class RepoResult(
    val resultCount: String?,
    val items: ArrayList<Results>
)

data class Results(

    @Nullable
    var id: Int? = 0,

    @Nullable
    val wrapperType: String?,

    @Nullable
    val kind: String? = "no value",

    @Nullable
    val collectionId: Int? = 0,

    @Nullable
    val trackId: Int? = 0,
    @Nullable
    val artistName: String? = "no value",
    @Nullable
    val collectionName: String? = "no value",
    @Nullable
    var trackName: String? = "no value",
    @Nullable
    val collectionCensoredName: String? = "no value",
    @Nullable
    val trackCensoredName: String? = "no value",
//    val collectionArtistViewUrl:String?,
    @Nullable
    val collectionViewUrl: String? = "no value",
    @Nullable
    val previewUrl: String? = "no value",
    @Nullable
    val trackPrice: String? = "no value",
    @Nullable
    val releaseDate: String? = "no value",
    @Nullable
    val country: String? = "no value",
    @Nullable
    val primaryGenreName: String? = "no value",
    @Nullable
    val artworkUrl100: String? = "no value"


)

fun Results.toRoomRecord() = ResultsOff(
    0,
    wrapperType,
    kind,
    collectionId,
    trackId,
    artistName,
    collectionName,
    trackName,
    collectionCensoredName,
    trackCensoredName,
    collectionViewUrl,
    previewUrl,
    trackPrice,
    releaseDate,
    country,
    primaryGenreName,
    artworkUrl100
)
