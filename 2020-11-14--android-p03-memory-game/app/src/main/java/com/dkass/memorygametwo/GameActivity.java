package com.dkass.memorygametwo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.dkass.memorygametwo.databinding.ActivityGameBinding;

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
        // --------------------------------------------------------------------------loading
        if (getIntent().getBooleanExtra("loaded", false)) {
            game.level = level;
            game.wrongGuessesView = binding.textViewWrongGuesses;
            game.context = this;
            game.thisN = thisN;
            game.decreaseN = decreaseN;
            game.buttonNextLevel = binding.buttonNextLevel;
            game.pointsView = binding.textViewPoints;


            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

            level.title = sharedPref.getString("level.title", "foo");
            level.maxWrongGuesses = sharedPref.getInt("level.maxWrongGuesses", -1);
            game.nicelyRevealedCards = sharedPref.getInt("game.nicelyRevealedCards", -19);

            for (int i = 0; i < level.pairs.size(); i++) {
                level.pairs.set(i, sharedPref.getString("level.pairs.get(" + i + ")", "xxx"));
            }
        } else {
            game.level = level;
            game.wrongGuessesView = binding.textViewWrongGuesses;
            game.context = this;
            game.thisN = thisN;
            game.decreaseN = decreaseN;
            game.buttonNextLevel = binding.buttonNextLevel;
            game.pointsView = binding.textViewPoints;
        }
        // --------------------------------------------------------------------------loading

        for (int i = 0; i < 2; i++) {
//            Collections.shuffle(level.pairs);
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

        if (getIntent().getBooleanExtra("loaded", false)) {
            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

            for (int i = 0; i < game.cards.size(); i++) {
                game.cards.get(i).revealed = sharedPref.getBoolean("game.cards.get(" + i + ").revealed",
                        true);
                if (game.cards.get(i).revealed)
                    game.cards.get(i).imageButton.setImageResource(
                            getIdFromFilename(game.cards.get(i).imageStr)
                    );
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

        binding.buttonSaveAndQuit.setOnClickListener((View v) -> {
            SharedPreferences sharedPref = GameActivity.this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();

            editor.putInt("thisN", thisN);
            editor.putInt("decreaseN", decreaseN);
            editor.putString("level.title", level.title);
            editor.putInt("level.maxWrongGuesses", level.maxWrongGuesses);
            editor.putInt("game.nicelyRevealedCards", game.nicelyRevealedCards);

            for (int i = 0; i < level.pairs.size(); i++) {
                editor.putString("level.pairs.get(" + i + ")", level.pairs.get(i));
            }

            for (int i = 0; i < game.cards.size(); i++) {
                editor.putBoolean("game.cards.get(" + i + ").revealed",
                        game.cards.get(i).revealed);
            }

            editor.apply();

            finish();
        });

        game.updateWrongGuessesView();

    }
}