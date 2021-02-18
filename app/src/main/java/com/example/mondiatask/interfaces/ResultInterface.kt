package com.example.mondiatask.interfaces

import com.example.mondiatask.model.MusicModel

interface ResultInterface {

    fun onSuccess(songModels: List<MusicModel?>?)

    fun onFailed(errorMsg: String?)

    fun setAccessToken(accessToken: String?)
}