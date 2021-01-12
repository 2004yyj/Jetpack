package com.example.dgsw.databinding_exam;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.example.dgsw.databinding_exam.databinding.ActivityMainBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setLifecycleOwner(this); // LiveData를 관찰하면서 UI에 바로 반영할 수 있도록 한다.

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.setViewModel(viewModel);



        // viewModel.getAll().observe(this, it -> binding.resultText.setText(it.toString()));

        // findViewById(R.id.add_button).setOnClickListener(v ->
            // viewModel.insert(new Todo(binding.todoEdit.getText().toString()))
        // );
    }
}