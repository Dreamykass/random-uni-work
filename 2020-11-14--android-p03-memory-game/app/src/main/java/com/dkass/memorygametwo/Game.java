package com.dkass.memorygametwo;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Game {
    public Context context;
    public Level level;
    public List<Card> cards = new ArrayList<>();
    public Integer wrongGuesses = 0;
    public TextView wrongGuessesView;
    public TextView pointsView;
    public Button buttonNextLevel;
    public Integer nicelyRevealedCards = 0;
    GameState state = GameState.NoneRevealed;
    Card firstCardTried;
    Card secondCardTried;
    Integer thisN;
    Integer decreaseN;

    private int getIdFromFilename(String str) {
        return context.getResources().getIdentifier(str, "raw", context.getPackageName());
    }

    public void updateWrongGuessesView() {
        int i = level.maxWrongGuesses - wrongGuesses;
        wrongGuessesView.setText("remaining wrong guesses: " + Integer.toString(i));
        pointsView.setText("points: " + (nicelyRevealedCards / 2));
    }

    public void processClick(Card card) {
        GameState nextState = GameState.NoneRevealed;
        switch (state) {
            case NoneRevealed: {
                if (!card.revealed) {
                    firstCardTried = card;

                    card.imageButton.setImageResource(getIdFromFilename(card.imageStr));

                    card.revealed = true;

                    nextState = GameState.OneCardRevealed;
                }
                break;
            }
            case OneCardRevealed: {
                if (!card.revealed) {
                    secondCardTried = card;

                    card.imageButton.setImageResource(getIdFromFilename(card.imageStr));

                    card.revealed = true;

                    nextState = GameState.TwoCardsRevealed;
                }
                break;
            }
            case TwoCardsRevealed: {
                if (firstCardTried.imageStr.equals(secondCardTried.imageStr)) {
                    nicelyRevealedCards += 2;
                    updateWrongGuessesView();
                } else {
                    firstCardTried.imageButton.setImageResource(getIdFromFilename("hidden"));
                    secondCardTried.imageButton.setImageResource(getIdFromFilename("hidden"));

                    firstCardTried.revealed = false;
                    secondCardTried.revealed = false;

                    if (firstCardTried.revealedAtLeastOnce) {
                        wrongGuesses += 1;
                        updateWrongGuessesView();
                    }
                    if (secondCardTried.revealedAtLeastOnce) {
                        wrongGuesses += 1;
                        updateWrongGuessesView();
                    }

                    firstCardTried.revealedAtLeastOnce = true;
                    secondCardTried.revealedAtLeastOnce = true;
                }

                if (wrongGuesses >= level.maxWrongGuesses) {
                    nextState = GameState.GameOver;
                    wrongGuessesView.setText("Game Over!");
                }

                if (nicelyRevealedCards >= level.pairs.size() * 2) {
                    nextState = GameState.GameWon;
                    wrongGuessesView.setText("Game Won! Congratulations!");

//                    Intent intent = new Intent(context, GameActivity.class);
//                    intent.putExtra("thisN", thisN - decreaseN);
//                    intent.putExtra("decreaseN", decreaseN);
//                    context.startActivity(intent);
                    buttonNextLevel.setVisibility(View.VISIBLE);

                }

                break;
            }
        }
        state = nextState;
        Log.e("kekw", thisN.toString());
        Log.e("kekw", decreaseN.toString());
    }
}
