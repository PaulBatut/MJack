package com.company;


import java.util.ArrayList;
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

    public static String executionAction(String actionJeton, ArrayList<Detective> detective, ArrayList<Alibi> cartes, ArrayList<Tiles> district, int numeroTour, int numeroPris){
        switch (actionJeton){
            case "Alibi":
                return "Vous avez choisi l'option Alibi";
            case "Sherlock":
                int avanceCases = Action.moveDetective();
                int nouvellePosition=detective.get(0).position;
                for (int i=0; i<avanceCases; i++){
                    nouvellePosition=(nouvellePosition+1)%12;
                }
                Detective newSherlock = new Detective(detective.get(0).name,nouvellePosition);
                detective.set(0, newSherlock);
                return "Vous avez choisi l'option Sherlock";

            case "Toby":
                avanceCases = Action.moveDetective();
                nouvellePosition=detective.get(2).position;
                for (int i=0; i<avanceCases; i++){
                    nouvellePosition=(nouvellePosition+1)%12;
                }
                Detective newToby = new Detective(detective.get(2).name,nouvellePosition);
                detective.set(2, newToby);
                return "Vous avez choisi l'option Toby";

            case "Watson":
                avanceCases = Action.moveDetective();
                nouvellePosition=detective.get(1).position;
                for (int i=0; i<avanceCases; i++){
                    nouvellePosition=(nouvellePosition+1)%12;
                }
                Detective newWatson = new Detective(detective.get(1).name,nouvellePosition);
                detective.set(1, newWatson);
                return "Vous avez choisi l'option Watson";

            case "Rotation":
                int rotateDistrict = Action.rotation();
                int oldRot = district.get(rotateDistrict).orientation;
                int newRot = (oldRot+1)%4;
                Tiles newRotTile = new Tiles(district.get(rotateDistrict).color,newRot );
                district.set(rotateDistrict,newRotTile);
                for (Tiles tiles : district) {
                    System.out.println("");
                    System.out.printf("[%s, %d]", tiles.color, tiles.orientation);
                    System.out.println("");
                }
                return "Vous avez choisi l'option rotation";

            case "Echange":
                int[] districtEchange = Action.echange();
                Tiles tileTemporaire= new Tiles(district.get(districtEchange[0]).color, district.get(districtEchange[0]).orientation);
                district.set(districtEchange[0], district.get(districtEchange[1]));
                district.set(districtEchange[1], tileTemporaire);
                for (Tiles tiles : district) {
                    System.out.println("");
                    System.out.printf("[%s, %d]", tiles.color, tiles.orientation);
                    System.out.println("");
                }
                return "Vous avez choisi l'option Echange";

            case "Joker":
                int numeroDetective = Action.joker(numeroTour, numeroPris);
                nouvellePosition = detective.get(numeroDetective).position+1;
                Detective newDetective = new Detective(detective.get(numeroDetective).name,nouvellePosition);
                detective.set(1, newDetective);
                return "Vous avez choisi l'option Joker";
        }
        return actionJeton;
    }
}
