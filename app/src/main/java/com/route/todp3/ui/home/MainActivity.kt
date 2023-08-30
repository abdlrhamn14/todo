package com.route.todp3.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.route.todp3.R
import com.route.todp3.databinding.ActivityMainBinding
import com.route.todp3.ui.home.tabs.SettingFragment

import com.route.todp3.ui.home.tabs.tasks_list.ListFragment

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding
     var tasksListFragmentRef:ListFragment?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.floatingActionBtn.setOnClickListener{
            startAddTaskFragment()
        }

        viewBinding.navBottom.setOnItemSelectedListener {
            when(it.itemId){
                R.id.list->
                {
                    tasksListFragmentRef= ListFragment()
                    startFragment(tasksListFragmentRef!!)
                }
                R.id.settings->startFragment(SettingFragment())
            }
            return@setOnItemSelectedListener true
        }

    }

    private fun  startAddTaskFragment() {
        val addTaskFragment = AddTaskFragment()
        addTaskFragment.onTaskAddedLisnter=AddTaskFragment.OnTaskAddedLisnter{
            Snackbar.make(viewBinding.root,
                "Task Added Successfully",Snackbar.LENGTH_SHORT)
                .show()
            tasksListFragmentRef?.loadTasksFromDataBase()
            //كان في بج هنا وانا في السيتنج لو عملت تاسك جديده ودوست يضرب
            //لان لما يعمل ليسن علي زرار الادد هيجي هنا وييجي عند loadTasksFromDataBase ويخش فيها جوا
        //فراجمنت الليست فهيلاقي انو محتاج ريكويرد كونتسكت هوب!! اصلا الكونتست دي مش ظاهره معمولها  deAttach
        //هي اه جوا المين اكتيفتي بس معمولها deAttach عشان هي مش ظاهره فهتضرب لانها بالنسبالها مش موجوده!!
        //وكمان اصلا كده الكونتكيست ب  null بالتالي ريكور كونتكست مش هتسكت لانك لو شوفت الامبليمنتشن بتاعها هتلاقيها بترمي في الحاله دي الاكسبشن ده
        // throw new IllegalStateException("Fragment " + this + " not attached to a context.");
        }
        addTaskFragment.show(supportFragmentManager,"")

//When the bottom fragment sheet appears, it covers the underlying content of the main class, including the floating action button. However, the Snackbar can still appear above the bottom fragment sheet because it is displayed using the root view of the main class.
//The Snackbar.make() method takes a view as its first parameter, which determines where the Snackbar will be displayed. In your code, you're passing viewBinding.root as the view, which represents the root view of the main class's layout.
//Even though the bottom fragment sheet is displayed on top of the main class's layout, the Snackbar is associated with the root view of the main class. Therefore, it will be displayed above the bottom fragment sheet, allowing the user to see the Snackbar's message.
//It's important to note that the Snackbar will be visible as long as its associated view (viewBinding.root) is visible on the screen. If the bottom fragment sheet completely covers the root view, then the Snackbar may not be visible until the bottom fragment sheet is dismissed or moved to reveal the root view again.
    }

    private fun startFragment(fragment:Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,fragment)
            .setCustomAnimations(R.anim.fade_in,R.anim.fade_out)
            .addToBackStack(null)
            .commit()
    }
}