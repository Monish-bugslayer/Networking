package com.example.networking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.networking.databinding.ItemtodoBinding


class ToDoAdapter : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(){

    inner class ToDoViewHolder(val binding:ItemtodoBinding):RecyclerView.ViewHolder(binding.root)
    private val diffCallBack=object:DiffUtil.ItemCallback<ToDoItems>(){
        override fun areItemsTheSame(oldItem: ToDoItems, newItem: ToDoItems): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: ToDoItems, newItem: ToDoItems): Boolean {
            return oldItem==newItem
        }

    }
    private val differ=AsyncListDiffer(this,diffCallBack)
    var todos:List<ToDoItems>
    get() = differ.currentList
    set(value){differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(ItemtodoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.binding.apply {
            val todo=todos[position]
            viewDetails.text=todo.title
            checkBox.isChecked=todo.completed
        }
    }
}