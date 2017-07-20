package ru.skbkontur.skoroxod.calcjava;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.kittinunf.result.Result;

import java.util.List;

import ru.skbkontur.skoroxod.calcjava.databinding.MainActivityBinding;

import static ru.skbkontur.skoroxod.calcjava.CalculatorKt.ParseTerms;


public class MainActivity extends AppCompatActivity {

    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }


    public void onClick(View v) {
        Result<List<String>, Exception> res = ParseTerms(binding.term.getText().toString());

//            binding.result.setText();
//            binding.term.setError();
    }
}
