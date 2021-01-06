package com.example.dgsw.room_exam

import androidx.room.*

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    abstract fun getAll(): List<Todo>

    @Insert
    abstract fun insert(todo : Todo)

    @Update
    abstract fun update(todo : Todo)

    @Delete
    abstract fun delete(todo : Todo)
}