package com.company;

public class Detective {
    public int position;
    Detective(int position){
        this.position = position;

    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void newPosition(int avanceDeCase) {
        for(int i=0; i<avanceDeCase; i++){
            if (position>=11){
                position = position-11;
            }
            else{
                position++;
            }
        }


    }
}
