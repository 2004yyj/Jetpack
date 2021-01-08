package com.example.dgsw.livedata_exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText mTodoEditText = findViewById(R.id.todo_edit);
        TextView mResultTextView = findViewById(R.id.result_text);

        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "todo-db-liveData1")
                .allowMainThreadQueries()
                .build();

        //관찰(Observe)도중 DB에 Insert시 UI 갱신
        db.todoDao().getAll().observe(this, todos -> mResultTextView.setText(todos.size() + "개"));

        //버튼 클릭시 DB에 Insert
        findViewById(R.id.add_button).setOnClickListener(v -> db.todoDao().insert(new Todo(mTodoEditText.getText().toString())));
    }
}