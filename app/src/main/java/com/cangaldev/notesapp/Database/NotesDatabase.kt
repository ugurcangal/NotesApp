package com.cangaldev.notesapp.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cangaldev.notesapp.Dao.NotesDao
import com.cangaldev.notesapp.model.Notes
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.runBlocking

@Database(entities = [Notes::class],version = 1 , exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun myNotesDao(): NotesDao

    companion object{

        @Volatile
        var INSTANCE: NotesDatabase?=null



        fun getDatabaseInstance(context: Context):NotesDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {

                return tempInstance

            }

            kotlin.synchronized(this){
                val roomDatabaseInstance = Room.databaseBuilder(context,NotesDatabase::class.java,"Notes").allowMainThreadQueries().build()
                INSTANCE=roomDatabaseInstance
                return roomDatabaseInstance
            }


        }
    }


}