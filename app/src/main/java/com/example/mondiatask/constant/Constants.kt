package com.example.mondiatask.constant

class Constants {
    companion object{
        const val  base_url = "https://staging-gateway.mondiamedia.com/"
        const val api_token = "v0/api/gateway/token/client"
        const val api_music_list = "v2/api/sayt/flat?query=%s&limit=20"
        const val getWay_key = "X-MM-GATEWAY-KEY"
        const val getWay_value = "Ge6c853cf-5593-a196-efdb-e3fd7b881eca"
        const val access_token_key = "Authorization"
        const val access_token_value = "Bearer"
        const val access_token_key1 = "accessToken"
        const val get_api_method = "GET"
        const val post_api_method = "POST"
        const val content_type_key = "Content-Type"
        const val content_type_value = "application/x-www-form-urlencoded"
        const val accept_header_key = "Accept"
        const val accept_header_value = "application/json"
    }

}