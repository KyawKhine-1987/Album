package com.android.freelance.album.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
/*
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
*/
import com.android.freelance.album.data.db.AlbumDao
import com.android.freelance.album.data.db.entity.Albums


@Database(
    /*entities = arrayOf(Data::class),*/
    entities = [Albums::class],
    version = 1,
    exportSchema = false
)
abstract class AlbumDatabase : RoomDatabase() {
    abstract fun albumDao(): AlbumDao

    companion object {
        private var INSTANCE: AlbumDatabase? = null

        internal fun getDatabase(context: Context): AlbumDatabase {

            if (INSTANCE == null) {
                synchronized(AlbumDatabase::class.java) {

                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AlbumDatabase::class.java,
                            "Albums"
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
 }