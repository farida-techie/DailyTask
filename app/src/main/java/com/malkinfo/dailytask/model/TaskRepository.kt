package com.malkinfo.dailytask.model

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TaskRepository(var application: Application?) {
    private val taskDao: TaskDao
    private val allRecs: LiveData<List<UserTask?>?>?
    val recLiveData = MutableLiveData<UserTask>()

    fun insert(recs: UserTask?) {
        InsertNoteAsyncTask(taskDao).execute(recs)
    }

    fun update(recs: UserTask?) {
        UpdateNoteAsyncTask(taskDao).execute(recs)
    }

    fun delete(recs: UserTask?) {
        DeleteNoteAsyncTask(taskDao).execute(recs)
    }

    fun deleteAllRecords() {
        DeleteAllNotesAsyncTask(taskDao).execute()
    }
    fun getAllRecords(): LiveData<List<UserTask?>?>?{
        return allRecs
    }

    private class InsertNoteAsyncTask (private val taskDao: TaskDao) :
        AsyncTask<UserTask?, UserTask?, UserTask?>() {
        override  fun doInBackground(vararg recs: UserTask?): UserTask? {
            taskDao.insert(recs[0])
            return null
        }
    }


    private class UpdateNoteAsyncTask  (private val taskDao: TaskDao) :
        AsyncTask<UserTask?, UserTask?, UserTask?>() {
        override fun doInBackground(vararg recs: UserTask?): UserTask? {
            taskDao.update(recs[0])
            return null
        }
    }

    private class DeleteNoteAsyncTask(private val taskDao: TaskDao) :
        AsyncTask<UserTask?, UserTask?, UserTask?>() {
        override fun doInBackground(vararg recs: UserTask?): UserTask? {
            taskDao.delete(recs[0])
            return null
        }
    }

    private class DeleteAllNotesAsyncTask (private val taskDao: TaskDao) :
        AsyncTask<UserTask?, UserTask?, UserTask?>() {
        override fun doInBackground(vararg notes: UserTask?): UserTask? {
            taskDao.deleteAllRecords()
            return null
        }
    }

    init {
        val database = TaskRoomDatabase.getInstance(application!!)
        taskDao = database!!.recordingDao()
        allRecs = taskDao.getAllRecords()
    }
}