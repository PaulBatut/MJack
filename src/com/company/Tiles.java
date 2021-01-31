package com.company;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Tiles {
    int orientation;
    boolean recto;
    String color;
    boolean isSus;
    public Tiles(String color, int orientation, boolean recto, boolean isSus){
        this.color = color;
        this.orientation = orientation;
        this.recto= recto;
        this.isSus= isSus;
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

    public boolean isSus() {
        return isSus;
    }
    public void setSus(boolean sus) {
        isSus = sus;
    }
}