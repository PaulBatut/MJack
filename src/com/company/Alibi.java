package com.company;

import java.util.ArrayList;
import java.util.List;

public class Alibi {
    boolean isCulprit;
    String color;
    int hourglassNumber;
    List<Alibi> pile;

    public boolean isCulprit() {
        return isCulprit;
    }
    public void setCulprit(boolean culprit) {
        isCulprit = culprit;
    }

    public void setHourglassNumber(int hourglassNumber) {
        this.hourglassNumber = hourglassNumber;
    }
    public int getHourglassNumber() {
        return hourglassNumber;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }

    public Alibi(boolean isCulprit, int hourglassNumber, String color) {
        this.isCulprit = isCulprit;
        this.hourglassNumber = hourglassNumber;
        this.color = color;
    }
}