package com.example.mondiatask.network

import android.os.AsyncTask
import android.text.TextUtils
import android.util.Log
import com.example.mondiatask.constant.Constants.Companion.access_token_key1
import com.example.mondiatask.constant.Constants.Companion.api_music_list
import com.example.mondiatask.constant.Constants.Companion.api_token
import com.example.mondiatask.constant.Constants.Companion.base_url
import com.example.mondiatask.constant.Constants.Companion.get_api_method
import com.example.mondiatask.constant.Constants.Companion.post_api_method
import com.example.mondiatask.interfaces.ResultInterface
import com.example.mondiatask.model.MusicModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONTokener
import java.io.IOException
import java.net.HttpURLConnection

class MusicAsyncTask(var result: ResultInterface) : AsyncTask<String, Void, List<MusicModel>>() {


    var myException: Exception? = null


    override fun doInBackground(vararg params: String?): List<MusicModel>? {
        try {
            var accessToken: String? = params[0]
            //check if access token exist
            if (TextUtils.isEmpty(accessToken)) {
                accessToken = getAccessTokenFromApi()
                result.setAccessToken(accessToken)
            }
            Log.e("AccessTokenInAsync" , accessToken!!)
            //then get songs list
            return getMusicListFromApi(accessToken!!, params[1]!!)
        } catch (e: Exception) {
            this.myException = e
        }
        return emptyList()
    }

    @Throws(IOException::class, JSONException::class)
    private fun getAccessTokenFromApi(): String? {
        val accessTokenApiUrl: String? = getAccessTokenApiUrl()
        val urlConnection: HttpURLConnection = HttpUrlConnectionClass().getHttpConnection(
            accessTokenApiUrl,
            post_api_method,
            "" ,
            true
        )!!
        val accessTokenResponse: String = HttpUrlConnectionClass().getResponse(urlConnection)
        val jsonObject = JSONTokener(accessTokenResponse).nextValue() as JSONObject
        Log.e("AccessToken" , jsonObject.getString(access_token_key1))
        return jsonObject.getString(access_token_key1)
    }


    @Throws(IOException::class, JSONException::class)
    private fun getMusicListFromApi(accessToken: String, searchKey: String): List<MusicModel>? {
        val musicListApiUrl: String? = getMusicListApiUrl(searchKey)
        val urlConnection: HttpURLConnection? =
            HttpUrlConnectionClass().getHttpConnection(musicListApiUrl, get_api_method, accessToken , false)
        val response: String = HttpUrlConnectionClass().getResponse(urlConnection!!)
        val jsonSongsArray = JSONTokener(response).nextValue() as JSONArray
        closeAllRequests()
        return MusicModel.getMusicListFromJsonArray(jsonSongsArray)
    }

    private fun closeAllRequests() {
        HttpUrlConnectionClass().disConnect()
    }

    override fun onPostExecute(musicList: List<MusicModel>?) {
        super.onPostExecute(musicList)
        if (this.myException != null) {
            // when fetching api failed
            this.result.onFailed(this.myException!!.message)
        } else {
            //when fetching api success
            this.result.onSuccess(musicList)
        }
    }

    private fun getAccessTokenApiUrl(): String? {
        return base_url + api_token
    }
    private fun getMusicListApiUrl(searchKey: String): String? {
        val url: String = String.format(
            api_music_list,
            searchKey
        )
        return base_url + url
    }
}