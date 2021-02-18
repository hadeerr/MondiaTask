package com.example.mondiatask.network

import android.text.TextUtils
import com.example.mondiatask.constant.Constants.Companion.accept_header_key
import com.example.mondiatask.constant.Constants.Companion.accept_header_value
import com.example.mondiatask.constant.Constants.Companion.access_token_key
import com.example.mondiatask.constant.Constants.Companion.access_token_value
import com.example.mondiatask.constant.Constants.Companion.content_type_key
import com.example.mondiatask.constant.Constants.Companion.content_type_value
import com.example.mondiatask.constant.Constants.Companion.getWay_key
import com.example.mondiatask.constant.Constants.Companion.getWay_value
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class HttpUrlConnectionClass {

    var httpURLConnection: HttpURLConnection? = null
     var inStream: InputStream? = null
     var bReader: BufferedReader? = null


    @Throws(IOException::class)
    fun getHttpConnection(
        requestUrl: String?,
        requestMethod: String?, accessToken: String ,
        isAccessCodeApi : Boolean
    ): HttpURLConnection? {

        val url = URL(requestUrl)
        httpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection!!.requestMethod = requestMethod
        //set headers in api request access code and get list
        httpURLConnection!!.setRequestProperty(
            getWay_key,
            getWay_value
        )
        if(isAccessCodeApi) {
//            set headers if api access code api
            httpURLConnection!!.setRequestProperty(
                content_type_key,
                content_type_value
            )
            httpURLConnection!!.setRequestProperty(
                accept_header_key,
                accept_header_value
            )
        }
        //set headers in api request get list api

        if (!TextUtils.isEmpty(accessToken)) {
            //then its songs list request ---> add new header
            httpURLConnection!!.setRequestProperty(
                access_token_key,
                "$access_token_value $accessToken"
            )
        }

        httpURLConnection!!.connect()
        return httpURLConnection
    }


    fun getResponse(urlConnection: HttpURLConnection): String {
        //read response from api using input stream
        inStream = urlConnection.inputStream
        bReader =
            BufferedReader(InputStreamReader(inStream))
        var temp: String?
        val response = StringBuilder()
        while (bReader!!.readLine()
                .also { temp = it } != null
        ) response.append(temp)
        return response.toString()

    }

    fun disConnect() {
        httpURLConnection?.disconnect()
        inStream?.close()
    }
}