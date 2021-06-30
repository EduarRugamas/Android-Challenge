package com.thedevexperto.test_android_challenge.Services

import com.thedevexperto.test_android_challenge.Data.DataArticulosAndroid
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun ObtenerArticulos(@Url url:String): Response<DataArticulosAndroid>

}