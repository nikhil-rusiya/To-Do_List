package com.example.todo_list

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DAO {
    //suspend is used for run query in background thread so that if anyquery is runnning like searching which took time a lot
    //so that our UI is not pause and query run in background thread not on main thread (Coroutines ka use hota h)
    @Insert
    suspend fun insertTask(entity: Entity)

    @Update
    suspend fun updateTask(entity: Entity)

    @Delete
    suspend fun deleteTask(entity: Entity)

    @Query( "SELECT * FROM To_Do")
    suspend fun getTask(): List<CardInfo>
}