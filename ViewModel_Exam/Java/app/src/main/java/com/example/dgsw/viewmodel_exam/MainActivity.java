package com.example.dgsw.viewmodel_exam;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText mTodoEditText = findViewById(R.id.todo_edit);
        TextView mResultTextView = findViewById(R.id.result_text);

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);


        viewModel.getAll().observe(this, it -> mResultTextView.setText(it.toString()));

        findViewById(R.id.add_button).setOnClickListener(v ->
            viewModel.insert(new Todo(mTodoEditText.getText().toString()))
        );

}
}