package ru.skbkontur.skoroxod.calcjava;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
        kotlin.Pair<Boolean, String> res =  Calculate(binding.term.getText().toString());
        if(res.getFirst()) {
            binding.result.setText(res.getSecond());
        } else {
            binding.term.setError(res.getSecond());
        }
    }
}
