package com.company;


import java.util.Scanner;

public class Jeton {
    String recto;
    String verso;
    Scanner scanner = new Scanner(System.in);

    public String getRecto() {
        return recto;
    }

    public String getVerso() {
        return verso;
    }

    public void setRecto(String recto) {
        this.recto = recto;
    }

    public void setVerso(String verso) {
        this.verso = verso;
    }

    public Jeton(String recto, String verso) {
        this.recto = recto;
        this.verso = verso;
    }

    public static String executionAction(String actionJeton){
        switch (actionJeton){
            case "Alibi":
                return "Vous avez choisi l'option Alibi";
            case "Sherlock":
                int avanceCases = Action.moveSherlock();
                System.out.println(avanceCases);
                return "Vous avez choisi l'option Sherlock";
            case "Toby":
                return "Vous avez choisi l'option Toby";
            case "Watson":
                return "Vous avez choisi l'option Watson";
            case "Rotation":
                return "Vous avez choisi l'option rotation";
            case "Echange":
                return "Vous avez choisi l'option Echange";
            case "Joker":
                return "Vous avez choisi l'option Joker";
        }
        return actionJeton;
    }
}
