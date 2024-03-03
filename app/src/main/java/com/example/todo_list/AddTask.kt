package com.example.todo_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.room.Room
import com.example.todo_list.databinding.ActivityAddTaskBinding
import com.example.todo_list.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddTask : AppCompatActivity() {
    private lateinit var database: myDatabase
    private lateinit var binding: ActivityAddTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(
            applicationContext, myDatabase::class.java, "To_Do"
        ).build()

        binding.update.setOnClickListener {
            val title = binding.EditTitleup.getText().toString()
            val desp = binding.AddDespup.getText().toString()

            if (title.isBlank() || desp.isBlank()) {
                Toast.makeText(this, "Fill all the details", Toast.LENGTH_SHORT).show()
            } else {
                DataObject.setData(title, desp)
                GlobalScope.launch {//generating coroutine scope sothan suspended function can called
                    database.dao().insertTask(Entity(0, title, desp))
                }
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }


        }

        binding.delete.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


    }
}