package com.example.dgsw.databinding_exam

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dgsw.databinding_exam.Todo
import io.reactivex.Completable

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    fun getAll(): LiveData<List<Todo>>

    @Insert
    fun insert(todo : Todo) : Completable

    @Update
    fun update(todo : Todo) : Completable

    @Delete
    fun delete(todo : Todo) : Completable
}