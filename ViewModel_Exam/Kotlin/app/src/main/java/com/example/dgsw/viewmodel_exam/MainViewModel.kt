package com.example.dgsw.viewmodel_exam

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(application : Application) : AndroidViewModel(application) {
    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "todo-db-kt-RxJava"
    ).build()

    fun getAll() : LiveData<List<Todo>> {
        return db.todoDao().getAll()
    }

    fun insert(todo : Todo) {
        db.todoDao().insert(todo)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe() // RxJava 비동기 처리
    }
}