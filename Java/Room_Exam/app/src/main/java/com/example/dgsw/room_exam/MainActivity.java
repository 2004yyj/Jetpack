package com.example.dgsw.room_exam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText mTodoEditText = findViewById(R.id.todo_edit);
        TextView mResultTextView = findViewById(R.id.result_text);

        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "todo-db")
                .allowMainThreadQueries()
                .build();

        mResultTextView.setText(db.todoDao().getAll().toString());

        findViewById(R.id.add_button).setOnClickListener(v -> {
            db.todoDao().insert(new Todo(mTodoEditText.getText().toString()));
            mResultTextView.setText(db.todoDao().getAll().toString());
        });
    }
}