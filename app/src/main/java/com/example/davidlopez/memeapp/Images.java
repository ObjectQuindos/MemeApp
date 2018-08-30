package com.example.davidlopez.memeapp;

import java.io.Serializable;

public class Images implements Serializable {
    String imageName;

    public Images(String imageName) {
        this.imageName = imageName;
    }
}
