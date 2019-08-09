package com.android.freelance.album.ui.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.DividerItemDecoration
import android.util.Log
import android.widget.Toast
import com.android.freelance.album.R
import com.android.freelance.album.data.AlbumDatabase
import com.android.freelance.album.data.db.entity.Albums
import com.android.freelance.album.data.db.network.models.MusicAlbums
import com.android.freelance.album.data.network.store.DataStore
import com.android.freelance.album.data.db.network.webservice.Result
import com.android.freelance.album.ui.adapters.AlbumsAdapters
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.activityUiThread
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.longToast

class MainActivity : AppCompatActivity() {

    private val LOG_TAG = MainActivity::class.java.name

    internal var db: AlbumDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
        retrieveAlbums()
    }

    private fun initialize() {
        Log.i(LOG_TAG, "TEST : initialize() is called...")
        db = AlbumDatabase.getDatabase(applicationContext)
    }

    // Retrieve albums from the API and save it to Database
    private fun retrieveAlbums() {
        Log.i(LOG_TAG, "TEST : retrieveAlbums() is called...")

        DataStore.getMusicAlbumDataWith { result ->
            if (result != null) {
                when (result.status) {
                    Result.Status.ERROR -> {
                        Toast.makeText(this, "Error ${result.exception?.message}", Toast.LENGTH_LONG).show()
                    }
                    Result.Status.SUCCESS -> {
                        val i = result.data
                        i?.let { saveWith(it) }
                    }
                }
            }
        }
    }

    // Method to save the data to database
    private fun saveWith(musicAlbums: Array<MusicAlbums>) {
        Log.i(LOG_TAG, "TEST : saveWith() is called...")

        doAsync {
            val currentDBPath = getDatabasePath("Albums").absolutePath
            println("DBPath is $currentDBPath")

            var albums = ArrayList<Albums>()

            for (musicAlbum in musicAlbums) {
                val a = Albums()
                a.title = musicAlbum.title
                a.artist = musicAlbum.artist
                a.image = musicAlbum.image
                a.url = musicAlbum.url
                a.thumbnail_image = musicAlbum.thumbnailImage
                albums.add(a)
            }
            db?.albumDao()?.insert(albums)

            val List = db?.albumDao()?.fetchAll()
            activityUiThread {
                longToast("Dat Got Saved.")
                refreshUIWith(List!!)
            }
        }
    }

    // Refresh the UI with fetched data
    private fun refreshUIWith(musicAlbums: List<Albums>) {
        Log.i(LOG_TAG, "TEST : refreshUIWith() is called...")

        val albumList = rvAlbumList
        var layoutManager = LinearLayoutManager(this)
        albumList.layoutManager = layoutManager
        albumList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val adapter = AlbumsAdapters(musicAlbums)
        albumList.adapter = adapter
    }
}
