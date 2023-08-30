package com.route.todp3.ui.home.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
//    @ColumnInfo(name="")
    @PrimaryKey(autoGenerate = true)
    // this annotation to make the  the key does not repeated make it unique
    // autoGenerate to assign the filed id automatic from 1 and increa
    // se 1 to each object or row or record
    var id:Int?=null,

    var title:String?=null,

    var description:String?=null,

    var date:Long?=null,

    var isDone:Boolean=false)
