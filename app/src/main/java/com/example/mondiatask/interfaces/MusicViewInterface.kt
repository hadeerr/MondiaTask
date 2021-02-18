package com.example.mondiatask.interfaces

import com.example.mondiatask.model.MusicModel

interface MusicViewInterface {
    fun showSongsList(songModels: List<MusicModel?>?)

    fun showError(errorMsg: String?)
}