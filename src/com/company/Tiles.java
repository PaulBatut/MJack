package com.company;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Tiles {
    int orientation;
    boolean recto = true;
    String color;
    public Tiles(String color, int orientation){
        this.color = color;
        this.orientation = orientation;
    }
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }
    public int getOrientation() {
        return orientation;
    }
    public boolean isRecto() {
        return recto;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }

}
