package com.route.todp3.ui.home.tabs.tasks_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.route.todp3.databinding.TaskItemBinding
import com.route.todp3.dateformat.Converter
import com.route.todp3.ui.home.model.Task

class ListAdapter(var tasks:ArrayList<Task>?):RecyclerView.Adapter<ListAdapter.ViewHolder>() {


    class ViewHolder(var itemBinding:TaskItemBinding):RecyclerView.ViewHolder(itemBinding.root){

    fun bind(task: Task){
         itemBinding.title.text=task.title
        //never will enter here if the list is null
     itemBinding.desc.text=task.description
        val date=Converter(task.date!!)
        itemBinding.date.text=date.format()
    }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding=TaskItemBinding.inflate(LayoutInflater.from(parent.context),
        parent,false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = tasks?.size?:0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

      holder.bind(tasks!!.get(position))//will never send any thing null
        //never will enter here if the list is null

    }

    fun upadateTasks(tasks: ArrayList<Task>) {
        this.tasks=tasks
        notifyDataSetChanged()
    }
}