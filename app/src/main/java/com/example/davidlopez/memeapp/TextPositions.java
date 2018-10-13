package com.example.davidlopez.memeapp;

public class TextPositions {
    private static volatile TextPositions soleInstance = new TextPositions();

    private TextPositions(){}

    public static TextPositions getInstance() {
        return  soleInstance;
    }

    Boolean hasFirstText = false;
    Boolean hasSecondText = false;

    String firstTextPosition = "";
    String secondTextPosition = "";

    String firstText = "";
    String secondText = "";

    float sizeFirstText = 75;
    float sizeSecondText = 75;
}
