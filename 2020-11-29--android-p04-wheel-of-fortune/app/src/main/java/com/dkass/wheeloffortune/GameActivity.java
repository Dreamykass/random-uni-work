package com.dkass.wheeloffortune;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.dkass.wheeloffortune.databinding.ActivityGameBinding;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    State state;
    Integer money = 0;
    List<Letter> lettersConsonants = new ArrayList<>();
    List<Letter> lettersVowels = new ArrayList<>();
    List<Word> words = new ArrayList<>();
    String answer;
    ActivityGameBinding binding;
    Wheel wheel = new Wheel();
    Boolean finished = false;
    Boolean finishedToastShown = false;
    String inputFromAlert = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        answer = AnswerService.getAnswer();
        state = State.Idle;


        // ----------------------------------------------------- letters
        for (char c = 'a'; c < 'z'; c++) {
            String cc = String.valueOf(c);
            boolean isVowel = ("aeiou".indexOf(c) != -1);

            Button button = new Button(GameActivity.this);
            button.setText(cc);
            button.setMinimumWidth(0);
            button.setWidth(100);
            button.setEnabled(false);

            Letter letter = new Letter();
            letter.button = button;
            letter.revealed = false;
            letter.value = cc;
            letter.isVowel = isVowel;

            button.setOnClickListener((View v) -> letterClick(letter));

            binding.gridLayoutLetters.addView(button);
            if (isVowel)
                lettersVowels.add(letter);
            else
                lettersConsonants.add(letter);
        }
        binding.gridLayoutLetters.setColumnCount(10);


        // ----------------------------------------------------- words
        for (char c : answer.toCharArray()) {
            String cc = String.valueOf(c);

            Button button = new Button(GameActivity.this);
            button.setText(cc);
            button.setMinimumWidth(0);
            button.setWidth(100);

            Word word = new Word();
            word.button = button;
            word.revealed = false;
            word.value = cc;
            button.setText("_");

            button.setOnClickListener((View v) -> wordClick(word));

            binding.gridLayoutWord.addView(button);
            words.add(word);

            if (cc.equals(" ")) {
                button.setText(" ");
                word.revealed = true;
            }
        }
        binding.gridLayoutWord.setColumnCount(10);


        // ----------------------------------------------------- other clicks
        binding.buttonRoll.setOnClickListener((View v) -> rollClick());
        binding.buttonVowel.setOnClickListener((View v) -> vowelClick());
        binding.buttonSolve.setOnClickListener((View v) -> solveClick());

        // ----------------------------------------------------- state refresh
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                refresh();
                handler.postDelayed(this, 1000);
            }
        }, 90);
    }

    void toast(String str) {
        Toast.makeText(GameActivity.this, str, Toast.LENGTH_LONG).show();
    }

    void refresh() {
        binding.textViewState.setText(state.toString());
        binding.buttonMoney.setText(String.format("%s$", money.toString()));

        if (finished && !finishedToastShown) {
            if (inputFromAlert.equalsIgnoreCase(answer)) {
                toast("You have won!!! Your final money was: " + money + "$.");
                finishedToastShown = true;
            } else {
                toast("You took a wrong guess and lost... Your final money was: " + money + "$.");
                finishedToastShown = true;
            }
        }

        if (state == State.Idle) {
            binding.buttonVowel.setEnabled(true);
            binding.buttonSolve.setEnabled(true);
            binding.buttonRoll.setEnabled(true);
        } else {
            binding.buttonVowel.setEnabled(false);
            binding.buttonSolve.setEnabled(false);
            binding.buttonRoll.setEnabled(false);
        }
    }

    void letterClick(Letter letter) {
        if (state == State.ChoosingAVowel && !letter.revealed) {
            letter.revealed = true;
            for (Letter v : lettersVowels)
                v.button.setEnabled(false);
            lettersVowels.remove(letter);

            for (Word word : words) {
                if (word.value.equals(letter.value)) {
                    word.revealed = true;
                    word.button.setText(word.value);
                }
            }

            state = State.Idle;
        } else if (state == State.ChoosingAConsonant && !letter.revealed) {
            letter.revealed = true;
            for (Letter v : lettersConsonants)
                v.button.setEnabled(false);
            lettersConsonants.remove(letter);

            for (Word word : words) {
                if (word.value.equals(letter.value)) {
                    word.revealed = true;
                    word.button.setText(word.value);
                }
            }

            state = State.Idle;
        }
        refresh();
    }

    void wordClick(Word word) {

    }

    void vowelClick() {
        if (!lettersVowels.isEmpty() && state == State.Idle) {
            if (money < 800) {
                toast("You need 800$ to buy a vowel.");
            } else {
                money -= 800;
                for (Letter letter : lettersVowels) {
                    letter.button.setEnabled(true);
                }
                state = State.ChoosingAVowel;
                refresh();
            }
        }
    }

    void rollClick() {
        if (state == State.Idle) {
            state = State.Rolling;
            refresh();


            Intent myIntent = new Intent(GameActivity.this, RollingActivity.class);
            myIntent.putExtra("currentItem", wheel.currentItem);
            myIntent.putExtra("nextItem", wheel.nextItem());
            myIntent.putExtra("size", wheel.items.size());
            GameActivity.this.startActivity(myIntent);


            final Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    int change = wheel.items.get(wheel.currentItem);
                    money += change;

                    for (Letter letter : lettersConsonants) {
                        letter.button.setEnabled(true);
                    }

                    if (change == 0)
                        toast("You've neither gained nor lost.");
                    else if (change < 0)
                        toast("You've lost " + change + "$...");
                    else
                        toast("You've won " + change + "$!");

                    state = State.ChoosingAConsonant;
                    refresh();
                }
            }, 1500);
        }

        refresh();
    }

    void solveClick() {
        if (state == State.Idle) {
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("What's the answer?");

                // Set up the input
                final EditText input = new EditText(this);
                // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                builder.setView(input);

                // Set up the buttons
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        inputFromAlert = input.getText().toString();
                        finished = true;
                        state = State.GameOver;
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }


        }
    }
}