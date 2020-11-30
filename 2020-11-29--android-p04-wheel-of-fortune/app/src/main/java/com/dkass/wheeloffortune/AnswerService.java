package com.dkass.wheeloffortune;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AnswerService {
    static List<String> answers = new ArrayList<>();

    static public void init() {
        answers.add("hello");
        answers.add("hello here");
        answers.add("aaa");
        answers.add("wheel");
        answers.add("fortune");
        answers.add("foo bar");
    }

    static public String getAnswer() {
        Random rr = new Random();
        return answers.get(rr.nextInt(answers.size()));
    }
}
