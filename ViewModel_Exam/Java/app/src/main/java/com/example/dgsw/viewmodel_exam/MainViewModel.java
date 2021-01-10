package com.example.dgsw.viewmodel_exam;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends AndroidViewModel {
    private AppDatabase db;

    public MainViewModel(@NonNull Application application) {
        super(application);
        db = Room.databaseBuilder(application, AppDatabase.class, "todo-db")
                .build();
    }

    public LiveData<List<Todo>> getAll() {
        return db.todoDao().getAll();
    }

    public void insert(Todo todo) {
            db.todoDao().insert(todo)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe();
    }
}
