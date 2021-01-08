package com.example.dgsw.rxjava_exam;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
import java.util.Observable;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface TodoDao {
    @Query("SELECT * FROM Todo")
    LiveData<List<Todo>> getAll();

    @Insert
    Completable insert(Todo todo);

    @Update
    Completable update(Todo todo);

    @Delete
    Completable delete(Todo todo);
}
