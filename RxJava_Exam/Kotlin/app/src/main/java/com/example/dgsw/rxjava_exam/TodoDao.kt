package com.example.dgsw.rxjava_exam

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    fun getAll(): Observable<List<Todo>>

    @Insert
    fun insert(todo : Todo) : Completable

    @Update
    fun update(todo : Todo) : Completable

    @Delete
    fun delete(todo : Todo) : Completable
}