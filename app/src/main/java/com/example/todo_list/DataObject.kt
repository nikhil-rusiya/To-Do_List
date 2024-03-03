package com.example.todo_list

import android.content.Context
import android.widget.Toast

object DataObject {
    var listData = mutableListOf<CardInfo>()

    fun setData(title: String, desp: String) {
            listData.add(CardInfo(title, desp))
    }

    fun getAllData(): List<CardInfo> {
        return listData
    }

    fun getData(pos:Int): CardInfo {
        return listData[pos]
    }
    fun del(pos: Int)  {
        listData.removeAt(pos)
    }

    fun updateData(pos:Int , title:String,description:String){
        listData[pos].title =title
        listData[pos].desp =description
    }
}