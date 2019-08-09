package com.android.freelance.album.data.network.store

import retrofit2.Call
import com.android.freelance.album.data.db.network.models.MusicAlbums
import com.android.freelance.album.data.db.network.webservice.APIInterface
import com.android.freelance.album.data.db.network.webservice.Result
import retrofit2.Response

// DataStore is "singleton Instance" like using an object
class DataStore private constructor() {

    companion object {

        fun getMusicAlbumDataWith(responseCallback: (Result<Array<MusicAlbums>>) -> Unit) {

            val service = APIInterface.APIServiceFactory.create()

            service.getMusicData().enqueue(object : retrofit2.Callback<Array<MusicAlbums>> {

                override fun onFailure(call: Call<Array<MusicAlbums>>, t: Throwable) {
                    //Handle failure
                    responseCallback(Result.error(Exception("Unable to fetch data.")))
                }

                override fun onResponse(call: Call<Array<MusicAlbums>>, response: Response<Array<MusicAlbums>>) {
                    val albumInfo = response.body()
                    println("Request Data Success")
                    print("CountryData is $albumInfo")
                    responseCallback(Result.success(albumInfo))
                }
            })
        }
    }
}