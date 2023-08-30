package com.route.todp3.taskDao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.route.todp3.ui.home.model.Task

@Dao
// here we will put all the methods to deal with our data base
interface Dao {
    @Insert
    //هو بقا هيعمل كل حاجه ويكتب كود بتاع الانيسرت والليله دي
    fun insertTask(task: Task)
    @Update
    fun updateTask(task: Task)
    @Delete
    fun deleteTask(task: Task)
    @Query("select * from Task")
    //هنا بقا هنكتب كويري ولو هتاخد بالك بقا في compile time verification
    fun selectAllTasks():List<Task>
    @Query("select * from Task where date=:date")
    fun selectTasksByDate(date:Long):List<Task>
}