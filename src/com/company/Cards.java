package com.company;
import java.util.Random;
public enum Cards {
    PINK,
    BLACK,
    ORANGE,
    PURPLE,
    GREEN,
    YELLOW,
    BLUE,
    WHITE,
    GREY;

    public int getSablier(){
        int[] Sablier = {2,0,1,1,1,1,0,1,1};
        return Sablier[this.ordinal()];
    }


}