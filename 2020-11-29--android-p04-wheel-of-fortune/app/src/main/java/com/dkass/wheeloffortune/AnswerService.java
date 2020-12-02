package com.dkass.wheeloffortune;

import android.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnswerService {
    static List<Pair<String, String>> answers = new ArrayList<>();

    static public void init() {
        answers.add(new Pair<>("greetings", "hello"));
        answers.add(new Pair<>("greetings", "hello here"));
        answers.add(new Pair<>("greetings", "hey"));
        answers.add(new Pair<>("goodbyes", "bye"));
        answers.add(new Pair<>("greetings", "goodbye"));
        answers.add(new Pair<>("greetings", "bye bye"));
    }

    static public Pair<String, String> getAnswer() {
        Random rr = new Random();
        return answers.get(rr.nextInt(answers.size()));
    }
}
