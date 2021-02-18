package com.example.mondiatask.view

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mondiatask.R
import com.example.mondiatask.adapter.MusicListAdapter
import com.example.mondiatask.interfaces.MusicViewInterface
import com.example.mondiatask.model.MusicModel
import com.example.mondiatask.presenter.MusicListPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()   , MusicViewInterface {

    lateinit  var musicListPresenter : MusicListPresenter
    var searchQuery = "all"
   lateinit var musicListAdapter : MusicListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
        fetchingMusicList(searchQuery)

    }

    private fun fetchingMusicList(searchQuery: String) {
       musicListPresenter.getMusics(searchQuery)
    }

    private fun initialize() {
        loading.visibility = View.VISIBLE
        musicListPresenter = MusicListPresenter(this)

    }

    override fun showSongsList(songModels: List<MusicModel?>?) {

        loading.visibility = View.GONE
        musicListAdapter = MusicListAdapter(songModels , this)
        music_list.adapter = musicListAdapter
        Log.e("showSongsList" , songModels?.size.toString())

    }

    override fun showError(errorMsg: String?) {
        Toast.makeText(this , errorMsg.toString() , Toast.LENGTH_LONG)
        Log.e("showError" , errorMsg.toString())
    }
}