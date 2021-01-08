package com.example.dgsw.livedata_exam

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    abstract fun getAll(): LiveData<List<Todo>>

    @Insert
    abstract fun insert(todo : Todo)

    @Update
    abstract fun update(todo : Todo)

    @Delete
    abstract fun delete(todo : Todo)
}