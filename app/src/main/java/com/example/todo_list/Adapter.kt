package com.example.todo_list

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class Adapter (var data :List<CardInfo>) : RecyclerView.Adapter<Adapter.myViewHolder>() {


    class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title = itemView.findViewById<TextView>(R.id.taskTitle)
        var desp = itemView.findViewById<TextView>(R.id.desp)
//        var delbtn = itemView.findViewById<Button>(R.id.delbtn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)

        return myViewHolder(itemView)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.title.text= data[position].title
        holder.desp.text= data[position].desp


//        holder.delbtn.setOnClickListener {
//            DataObject.del(position)
//            holder.delbtn.context.startActivity(Intent(holder.delbtn.context,MainActivity::class.java))
//
//        }


        holder.itemView.setOnClickListener {
           val intent = Intent(holder.itemView.context,UpdateTask::class.java)
            intent.putExtra("id",position)
            holder.itemView.context.startActivity(intent)
        }
    }


}