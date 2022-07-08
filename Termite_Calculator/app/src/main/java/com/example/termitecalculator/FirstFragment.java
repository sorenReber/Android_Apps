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
            // Get the user input and parse it into an double.
            double userInput = Double.parseDouble(inputNumber.getText().toString());
            double gallons = userInput * .2;
            double ounces = gallons * 1.6;
            // Set the output textviews to show the amounts of mixed and concentrated product.
            outGallons.setText(Double.toString(gallons));
            outOunce.setText(Double.toString(ounces));
        } catch(NumberFormatException e) {
            System.out.println("parse value is not valid : " + e);
        }
        inputNumber.requestFocus();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        // Connects each design element to their respective variables on activity creation.
        inputNumber = binding.inputNumber;
        inputNumber.setText("");
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
           /*
           This is an attempt at getting the number input to accept pressing the enter key
           or the green check mark on the screen. It sort of works.
            */
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