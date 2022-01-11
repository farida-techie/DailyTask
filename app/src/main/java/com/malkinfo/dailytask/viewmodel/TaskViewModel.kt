package com.malkinfo.dailytask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.malkinfo.dailytask.model.TaskRepository
import com.malkinfo.dailytask.model.UserTask


class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TaskRepository
    val allTAsk: LiveData<List<UserTask?>?>?
    var taskLiveData = MutableLiveData<UserTask>()

    fun insert(recs: UserTask?) {
        repository.insert(recs)
    }

    fun update(recs: UserTask?) {
        repository.update(recs)
    }

    fun delete(recs: UserTask?) {
        repository.delete(recs)
    }

    fun deleteAllTask() {
        repository.deleteAllRecords()
    }
    fun getAllTask():LiveData<List<UserTask?>?>?{
        return allTAsk
    }

    init {
        repository = TaskRepository(application)
        allTAsk = repository.getAllRecords()

    }
}