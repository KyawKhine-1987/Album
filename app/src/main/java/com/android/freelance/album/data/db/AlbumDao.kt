package com.android.freelance.album.data.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
/*import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query*/
import com.android.freelance.album.data.db.entity.Albums

@Dao
interface AlbumDao {

    @Insert
    fun insert(albums: ArrayList<Albums>)

    @Query("select * from tbl_albums nolock;")
    fun fetchAll(): List<Albums>

    @Query("select * from tbl_albums nolock where title like :title;")
    fun loadAllStartsWith(title: String): List<Albums>

    @Query("select * from tbl_albums nolock where artist like :name limit 1;")
    fun findByName(name: String): List<Albums>

    @Delete
    fun delete(albums: Albums)
}