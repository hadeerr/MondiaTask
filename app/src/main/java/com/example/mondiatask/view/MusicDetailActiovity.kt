package com.example.mondiatask.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mondiatask.R
import com.example.mondiatask.adapter.MusicListAdapter
import com.example.mondiatask.model.MusicModel
import kotlinx.android.synthetic.main.activity_music_detail_actiovity.*

class MusicDetailActiovity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_detail_actiovity)
        if (intent != null) {
            val music: MusicModel =
                intent.getSerializableExtra("object") as MusicModel
            initView(music)
        }
    }

    private fun initView(music: MusicModel) {
        if (music != null) {
            music_detail_title.text = "Title Is : " + music.musicTitle
            music_detail_type.text = "Type Is : " + music.musicType
            music_detail_artist.text = "Artist Is : " + music.musicArtistName
            music_publish_date.text = "Publish Date  Is : " + music.musicPublishingDate
            music_track_number.text = "TrackNumber Is : " + music.musicTrackNumber
            music_duration.text = "Duration Is : " + music.musicDuration
            var downloadImageTask : MusicListAdapter.DownloadImageTask =
                MusicListAdapter.DownloadImageTask(music_detail_img , detail_loading_img)
            downloadImageTask.execute("https:"+music.musicImg)
        }
    }
}