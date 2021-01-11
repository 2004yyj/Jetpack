package com.example.dgsw.coroutine_exam

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.todo_edit)
        val textView = findViewById<TextView>(R.id.result_text)
        val button = findViewById<Button>(R.id.add_button)

        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "todo-db-kt-RxJava"
        ).build()

        db.todoDao().getAll()
                .observe (this, { todo ->
                    textView.text = todo.toString()
                }
        ) // LiveData 사용

        button.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                db.todoDao().insert(Todo(editText.text.toString()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }
    }
}