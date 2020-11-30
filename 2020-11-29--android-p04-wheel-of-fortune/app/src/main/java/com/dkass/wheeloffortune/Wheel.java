package com.dkass.wheeloffortune;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Wheel {
    public Integer currentItem = 0;
    public List<Integer> items = new ArrayList<>();

    public Wheel() {
        items.add(0);
        items.add(-200);
        items.add(+100);
        items.add(+1000);

        items.add(-10000);
        items.add(+500);
        items.add(-500);
        items.add(+100);

        items.add(+1000);
        items.add(+400);
        items.add(-100);
        items.add(+100);

        items.add(-10000);
        items.add(+300);
        items.add(+200);
        items.add(+800);
    }

    public Integer nextItem() {
        Random rand = new Random();
        Integer n = rand.nextInt(items.size());
        currentItem = n;
        return n;
    }

}
