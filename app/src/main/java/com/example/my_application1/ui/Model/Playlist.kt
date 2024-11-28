package com.example.my_application1.ui.Model

data class Playlist(
    val checksum: String,
    val collaborative: Boolean,
    val cover: String,
    val creation_date: String,
    val creator: Creator,
    val description: String,
    val duration: Int,
    val fans: Int,
    val id: Int,
    val is_loved_track: Boolean,
    val link: String,
    val md5_image: String,
    val nb_tracks: Int,
    val picture_type: String,
    val `public`: Boolean,
    val share: String,
    val title: String,
    val tracklist: String,
    val tracks: Tracks,
    val type: String
)