package com.company;

public class Detective {
    public String nom;
    public int position;
    Detective(String nom, int position){
        this.nom = nom;
        this.position = position;

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
