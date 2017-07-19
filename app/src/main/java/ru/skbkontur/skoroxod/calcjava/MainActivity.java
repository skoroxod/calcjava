package ru.skbkontur.skoroxod.calcjava;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.kittinunf.result.Result;

import ru.skbkontur.skoroxod.calcjava.databinding.MainActivityBinding;

import static ru.skbkontur.skoroxod.calcjava.CalculatorKt.Calculate;


public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }


    public void onClick(View v) {
        Result<String, Exception> res = Calculate(binding.term.getText().toString());

//            binding.result.setText();
//            binding.term.setError();
    }
}
