package com.example.todo_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.todo_list.databinding.ActivityUpdateTaskBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UpdateTask : AppCompatActivity() {
    private lateinit var database: myDatabase
    private lateinit var binding: ActivityUpdateTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(applicationContext, myDatabase::class.java, "To_Do").build()

        //get the id of the intent in athe adapter.kt
        val pos = intent.getIntExtra("id", -1)

        if (pos != -1) {
            val title = DataObject.getData(pos).title
            val desp = DataObject.getData(pos).desp

            binding.EditTitleup.setText(title)
            binding.AddDespup.setText(desp)

            binding.delete.setOnClickListener {
                DataObject.del(pos)
                GlobalScope.launch {
                    database.dao().deleteTask(Entity(pos+1,title,desp))
                }
                myIntent()
            }

            binding.update.setOnClickListener {
                val title = binding.EditTitleup.getText().toString()
                val desp = binding.AddDespup.getText().toString()
                DataObject.updateData(pos, title, desp)
                GlobalScope.launch {
                    database.dao().updateTask(Entity(pos+1,title,desp))
                }
                myIntent()
            }
        }


    }

    fun myIntent() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}