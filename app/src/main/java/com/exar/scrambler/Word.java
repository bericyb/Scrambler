package com.exar.scrambler;

public class Word {

    private String word;
    private int points;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Word(String word, int points) {
        this.word = word;
        this.points = points;
    }

}
