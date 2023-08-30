package com.route.todp3.ui.home.tabs.tasks_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

import com.route.todp3.dataBase.MyDataBase

import com.route.todp3.databinding.FragmentListBinding
import com.route.todp3.ui.home.model.Task
import java.util.Calendar

class ListFragment:Fragment() {
  private  lateinit var viewBinding: FragmentListBinding
    var selectedDate= Calendar.getInstance()
    init {
        selectedDate.set(Calendar.HOUR,0)
        selectedDate.set(Calendar.MINUTE,0)
        selectedDate.set(Calendar.SECOND,0)
        selectedDate.set(Calendar.MILLISECOND,0)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding=FragmentListBinding.inflate(inflater,container,false)
        return viewBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    override fun onStart() {
        super.onStart()
        loadTasksFromDataBase()
    }

     fun loadTasksFromDataBase() {
         context?.let {
             var tasks =  MyDataBase.getInstance(requireContext())
             .tasksDoa()
             .selectTasksByDate(selectedDate.timeInMillis)
             adapter.upadateTasks(tasks as ArrayList<Task>)
         }//كده لو الكونسكتست بnullمش هنعمل حاجه لكن لو مش ب null

    }

    private val adapter= ListAdapter(null)
    private fun initViews() {
        viewBinding.recyclerViewList.adapter=adapter
        viewBinding.calendarView.setSelectedDate(
            CalendarDay.today()//return day of today
        //this method take object from local date or calender day
        // this class can not be instantiated because it has private constructors
        // but it had static method return obj from calender day
        //so when he start the application the start date will be day of today
        )
        viewBinding.calendarView.setOnDateChangedListener { widget, date, selected ->
            if (selected) {

                selectedDate.set(Calendar.YEAR, date.year)
                selectedDate.set(Calendar.MONTH, date.month-1)//month starts from 0 in Calender obj
                selectedDate.set(Calendar.DAY_OF_MONTH, date.day)
                loadTasksFromDataBase()
            }

        }

    }
}