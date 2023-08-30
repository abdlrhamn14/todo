package com.route.todp3.ui.home

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.route.todp3.dataBase.MyDataBase

import com.route.todp3.databinding.FragmentAddTaskBinding
import com.route.todp3.ui.home.model.Task
import java.util.Calendar


class AddTaskFragment : BottomSheetDialogFragment() {

private lateinit var viewBinding:FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding= FragmentAddTaskBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.addTask.setOnClickListener{
            createNewTask()
        }
        viewBinding.dateContainer.setOnClickListener {
            showDatePicker()
        }
    }

    val calendar= Calendar.getInstance()
    private fun showDatePicker() {
        context?.let {
            val dialog= if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                DatePickerDialog(it)
            } else {
                TODO("VERSION.SDK_INT < N")
            }
            dialog.setOnDateSetListener { datePicker, year, month, day ->
                //month starts from 0
            viewBinding.date.text="$day,${month+1},$year"
            calendar.set(year,month,day)
                //set the time equal to 0 ignore it
                calendar.set(Calendar.HOUR,0)
                calendar.set(Calendar.MINUTE,0)
                calendar.set(Calendar.SECOND,0)
                calendar.set(Calendar.MILLISECOND,0)
            }

        dialog.show()

        }

    }

    private fun createNewTask() {
        if (validation()){
            val task= Task(title = viewBinding.title.text.toString(),
                    description=viewBinding.desc.text.toString(),
                    date = calendar.timeInMillis)
            MyDataBase.getInstance(requireContext())
                .tasksDoa()
                .insertTask(task)
         onTaskAddedLisnter?.onTaskAdded()
            dismiss()
        }

    }
    var onTaskAddedLisnter:OnTaskAddedLisnter?=null
    fun interface OnTaskAddedLisnter{
       abstract fun onTaskAdded()
    }
    private fun validation(): Boolean {

    if (viewBinding.title.text.toString().isNullOrBlank()){
        viewBinding.titleContainer.error="fill the title"
        return false
    }
    else {
        viewBinding.titleContainer.error = null
    }
    if (viewBinding.desc.text.toString().isNullOrBlank()){
        viewBinding.descContainer.error="fill the description"
        return false
    }
    else {
        viewBinding.descContainer.error = null
    }
    if (viewBinding.date.text.toString().isNullOrBlank()){
        viewBinding.dateContainer.error="fill the date"
        return false
    }
    else {
        viewBinding.dateContainer.error = null
    }
        return true
    }




}