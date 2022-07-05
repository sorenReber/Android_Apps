package com.example.termitecalculator;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.termitecalculator.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private EditText inputNumber;
    private Button btnCalc;
    private TextView outGallons;
    private TextView outOunce;

    private void calculate(){
        try {
            int userInput = Integer.parseInt(inputNumber.getText().toString());
            double gallons = (double) userInput * .2;
            double ounces = gallons * 1.6;
            outGallons.setText(Double.toString(gallons));
            outOunce.setText(Double.toString(ounces));
        } catch(NumberFormatException e) {
            System.out.println("parse value is not valid : " + e);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        inputNumber = binding.inputNumber;
        btnCalc = binding.btnCalc;
        outGallons = binding.outGallons;
        outOunce = binding.outOunce;
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate();
            }
        });
       btnCalc.setOnEditorActionListener(new TextView.OnEditorActionListener() {
           @Override
           public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
               calculate();
               return true;
           }
       });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}