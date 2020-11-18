package com.dkass.memorygametwo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.dkass.memorygametwo.databinding.ActivityGameBinding;

import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    Level level;
    Integer thisN;
    Integer decreaseN;
    private ActivityGameBinding binding;

    static public int getIdFromFilenameStatic(Context context, String str) {
        return context.getResources().getIdentifier(str, "raw", context.getPackageName());
    }

    private int getIdFromFilename(String str) {
        return getResources().getIdentifier(str, "raw", getPackageName());
    }

    private void generateLevel() {
        level = new Level();
        level.title = thisN.toString();
        level.maxWrongGuesses = thisN;
        List<Level> levels = LevelsLoader.loadAllLevels(this);
        for (Level l : levels) {
            if (l.title.equals("level 3"))
                level.pairs = l.pairs;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

//        List<Level> levels = LevelsLoader.loadAllLevels(this);
//        for (Level l : levels) {
//            if (l.title.equals(getIntent().getStringExtra("level_title")))
//                level = l;
//        }
        thisN = Integer.parseInt(getIntent().getStringExtra("thisN"));
        decreaseN = Integer.parseInt(getIntent().getStringExtra("decreaseN"));
        generateLevel();

        binding.textViewTitle.setText(level.title);
        binding.textViewWrongGuesses.setText("remaining wrong guesses: " + level.maxWrongGuesses.toString());

        Game game = new Game();
        game.level = level;
        game.wrongGuessesView = binding.textViewWrongGuesses;
        game.context = this;
        game.thisN = thisN;
        game.decreaseN = decreaseN;
        game.buttonNextLevel = binding.buttonNextLevel;

        for (int i = 0; i < 2; i++) {
            Collections.shuffle(level.pairs);
            for (String pair : level.pairs) {
                {
                    ImageButton button = new ImageButton(this);
                    button.setImageResource(getIdFromFilename("hidden"));

                    Card card = new Card();
                    card.imageButton = button;
                    card.imageStr = pair;

                    button.setOnClickListener((View v) -> game.processClick(card));

                    binding.gridLayout.addView(button);
                    game.cards.add(card);
                }
            }
        }

//        binding.gridLayout.setOnClickListener((View v) -> game.processClick(null));
        binding.buttonNextLevel.setOnClickListener((View v) -> {
            Intent intent = new Intent(GameActivity.this, GameActivity.class);
            intent.putExtra("thisN", Integer.toString(thisN - decreaseN));
            intent.putExtra("decreaseN", Integer.toString(decreaseN));
            GameActivity.this.startActivity(intent);
            GameActivity.this.finish();
        });

        binding.buttonNextLevel.setVisibility(View.GONE);
    }
}