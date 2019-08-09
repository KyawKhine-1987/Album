package com.android.freelance.album.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/*import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey*/

@Entity(tableName = "tbl_albums")
data class Albums (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    var uid: Int = 0,

    @ColumnInfo(name = "Title")
    var title: String? = null,

    @ColumnInfo(name = "Artist")
    var artist: String? = null,

    @ColumnInfo(name = "URL")
    var url: String? = null,

    @ColumnInfo(name = "Image")
    var image: String? = null,

    @ColumnInfo(name = "Thumbnail_Image")
    var thumbnail_image: String? = null
)