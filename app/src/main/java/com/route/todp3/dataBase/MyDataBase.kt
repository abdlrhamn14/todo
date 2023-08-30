package com.route.todp3.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.todp3.ui.home.model.Task
import com.route.todp3.taskDao.Dao
@Database(entities = [Task::class], version = 1, exportSchema = true)
abstract class MyDataBase:RoomDatabase() {//لازم يكون سنجلتون وابستراكت في نقس الوقت
        abstract fun tasksDoa(): Dao
    companion object{
    private var instance: MyDataBase?=null

//kotlin coroutin
fun getInstance(context: Context): MyDataBase {
            if (instance == null){
                //initialize the instance
                // we will build the data base here using builder design pattern
                instance =Room.databaseBuilder(context.applicationContext, MyDataBase::class.java,"tasksDb")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    //يبقي انت محتاج حد يقولك ازاي تغير من الفيرجن للفيرجن فده اللي هيقولو من غيرو مش هيعرف ازاي يغير
                    //الفانكشن دي بقي هتسمح الداتا بيز القديمه اللي كانت موجوده وتعمل واحده جديده كليا
                    // بس كده عندك مشكله وهي ان كل الداتا القديمه هتطير
                    //الحل التاني   auto migrationبدالها يعني
                    .build()
            }
            return instance!!
        }

    }

}