package com.example.dgsw.livedata_exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.Room

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "todo-db-kt"
        ).allowMainThreadQueries().build()

        val editText = findViewById<EditText>(R.id.todo_edit)
        val textView = findViewById<TextView>(R.id.result_text)
        val button = findViewById<Button>(R.id.add_button)


        db.todoDao().getAll().observe(this, { todos ->
            textView.text = todos.toString()
        })

        button.setOnClickListener {
            db.todoDao().insert(Todo(editText.text.toString()))
        }

    }
}