package com.malkinfo.dailytask.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert
    fun insert(records:UserTask?)

    @Update
    fun update(records:UserTask?)

    @Delete
    fun delete(records:UserTask?)

    @Query("DELETE FROM recording_table")
    fun deleteAllRecords()

    @Query("SELECT * FROM recording_table ORDER BY id DESC")
    fun getAllRecords(): LiveData<List<UserTask?>?>?

}