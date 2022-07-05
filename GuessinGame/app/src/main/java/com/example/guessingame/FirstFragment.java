package com.example.guessingame;

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

import com.example.guessingame.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private EditText txtGuess;
    private Button btnGuess;
    private TextView lblOutput;
    private int theNumber;
    private int tries = 0;

    public void newGame(){
        theNumber = (int)(Math.random() * 100 + 1);
    }

    public void checkGuess(){
        String guessText = txtGuess.getText().toString();
        String message = "";
        txtGuess.setText("");
        txtGuess.requestFocus();
        try {
            int guess = Integer.parseInt(guessText);
            if (guess < theNumber) {
                message = guess + " is too low. Try again.";
                tries++;
            } else if (guess > theNumber) {
                message = guess + " is too high. Try again.";
                tries++;
            } else {
                message = guess + " is correct. I GUESS you won in " + tries + " tries! \nLet's play again!";
                tries = 0;
                newGame();
            }
        } catch (Exception e){
            message = "Please enter a whole number between 1 and 100";
        } finally {
            lblOutput.setText(message);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        txtGuess = binding.txtGuess;
        btnGuess = binding.btnGuess;
        lblOutput = binding.lblOutput;
        newGame();
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkGuess();
            }
        });
        txtGuess.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                checkGuess();
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