package com.android.freelance.album.data.db.network.webservice

import com.android.freelance.album.data.db.network.models.MusicAlbums
import com.android.freelance.album.data.network.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.Call

interface APIInterface {

    @GET("/api/music_albums")
    fun getMusicData(): Call<Array<MusicAlbums>>

    object APIServiceFactory {

        fun create(): APIInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.baseURL)
                .build()

            return retrofit.create(APIInterface::class.java)
        }
    }
}