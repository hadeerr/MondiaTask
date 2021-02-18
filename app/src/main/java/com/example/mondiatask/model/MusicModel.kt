package com.example.mondiatask.model

import android.util.Log
import org.json.JSONArray
import org.json.JSONException
import java.io.Serializable
import java.util.*

class MusicModel(
    var musicId: Int?,
    var musicTitle: String? = null,

    var musicType: String? = null,

    var musicPublishingDate: String? = null,

    var musicDuration: String? = null,
    var musicTrackNumber: String? = null,

    var musicArtistName: String? = null,

    var musicImg: String? = null,
) : Serializable {


    companion object {

        fun getMusicListFromJsonArray(jsonArray: JSONArray?): List<MusicModel>? {
            var musicModelList: MutableList<MusicModel>? = null
            if (jsonArray != null && jsonArray.length() > 0) {
                musicModelList = ArrayList<MusicModel>()
                for (iSong in 0 until jsonArray.length()) {
                    var musicModel: MusicModel?
                    try {
                        musicModel = MusicModel(
                            jsonArray.getJSONObject(iSong).getInt("id"),
                            jsonArray.getJSONObject(iSong).getString("title"),
                            jsonArray.getJSONObject(iSong).getString("type"),
                            jsonArray.getJSONObject(iSong).getString("publishingDate"),
                            jsonArray.getJSONObject(iSong).getString("duration"),
                            jsonArray.getJSONObject(iSong).getString("trackNumber"),
                            jsonArray.getJSONObject(iSong).getJSONObject("mainArtist")
                                .getString("name"),
                            jsonArray.getJSONObject(iSong).getJSONObject("cover")
                                .getString("large")
                        )
                        musicModelList!!.add(musicModel)
                    } catch (e: JSONException) {
                        Log.e("error", e.message!!)
                    }
                }
            }
            return musicModelList
        }
    }
}