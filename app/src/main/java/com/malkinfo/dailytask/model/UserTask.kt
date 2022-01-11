package com.malkinfo.dailytask.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "recording_table")
@Parcelize
data class UserTask(
    @PrimaryKey(autoGenerate = true)
    var id :Long = 0,
    var userTitle:String,
    var date:String
): Parcelable