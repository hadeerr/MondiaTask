package com.example.mondiatask.presenter

import com.example.mondiatask.interfaces.ResultInterface
import com.example.mondiatask.interfaces.MusicViewInterface
import com.example.mondiatask.model.MusicModel
import com.example.mondiatask.network.MusicAsyncTask

class MusicListPresenter(var musicMusicView: MusicViewInterface) : ResultInterface{
    var musicAsyncTask : MusicAsyncTask? = null
    var myAccessToken : String? = null

    fun getMusics(searchKey: String?) {
            musicAsyncTask = MusicAsyncTask(this)
            musicAsyncTask!!.execute(myAccessToken, searchKey)

    }

    override fun onSuccess(musics: List<MusicModel?>?) {
        if (musics!!.isEmpty()) {
            musicMusicView.showError("Error when fetching music list please try later")
        } else {
            musicMusicView.showSongsList(musics)
        }
    }

    override fun onFailed(errorMsg: String?) {
        musicMusicView.showError(errorMsg)
    }

    override fun setAccessToken(accessToken: String?) {
        this.myAccessToken = accessToken
    }
}