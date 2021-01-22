package com.company;

public class Detective {
    public int position;
    String name;
    Detective( String name,int position){
        this.position = position;
        this.name = name;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    public int getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public int newPosition(int avanceDeCase) {
        for(int i=0; i<avanceDeCase; i++){
            position = position%11;
            position = i+1;
        }
        return position;
    }
}
