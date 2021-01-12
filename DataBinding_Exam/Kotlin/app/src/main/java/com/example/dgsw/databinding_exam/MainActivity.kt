package com.example.dgsw.databinding_exam

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.dgsw.databinding_exam.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)

        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this // 라이브데이터를 옵저빙 할 수 있도록 함

        val mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.viewModel = mainViewModel // 뷰모델을 MainViewModel로 지정

//        val editText = findViewById<EditText>(R.id.todo_edit)
//        val textView = findViewById<TextView>(R.id.result_text)
//        val button = findViewById<Button>(R.id.add_button)

//        mainViewModel.getAll().observe (this, { todo ->
//                    textView.text = todo.toString()
//                }
//        ) // LiveData 사용

//        button.setOnClickListener {
//            mainViewModel.insert(Todo(editText.text.toString()))
//        }
    }
}