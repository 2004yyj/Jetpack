package com.example.dgsw.viewmodel_exam

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.todo_edit)
        val textView = findViewById<TextView>(R.id.result_text)
        val button = findViewById<Button>(R.id.add_button)

        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java);


        mainViewModel.getAll().observe (this, { todo ->
                    textView.text = todo.toString()
                }
        ) // LiveData 사용

        button.setOnClickListener {
            mainViewModel.insert(Todo(editText.text.toString()))
        }
    }
}