package com.malkinfo.dailytask.model

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [UserTask::class], version = 1)
abstract class TaskRoomDatabase : RoomDatabase() {

    abstract fun recordingDao(): TaskDao

    companion object{
        private var instance:TaskRoomDatabase?= null

        @Synchronized
        fun getInstance(context: Context):TaskRoomDatabase?{

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskRoomDatabase::class.java, "recording_table"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
        private val roomCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance).execute()
            }
        }
    }
    private class PopulateDbAsyncTask(db: TaskRoomDatabase?) : AsyncTask<Unit?, Unit?, Unit?>() {
        private val taskDao: TaskDao
        override fun doInBackground(vararg params: Unit?): Unit? {
            return null
        }

        init {
            taskDao = db!!.recordingDao()
        }
    }

}

