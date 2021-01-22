package com.company;

import javax.print.attribute.standard.MediaSize;
import java.util.*;

public class Start {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner scanner = new Scanner(System.in);

        int max = 4;
        ArrayList<Tiles> district = new ArrayList<>(); //on utilise une arraylist de la classe Tiles pour avoir les noms de districts au même endroit.
        ArrayList<Alibi> cartes = new ArrayList<>(); //on utilise une arraylist de la classe Alibi pour avoir toutes les cartes alibi au meme endroit
        List<String> couleurs = List.of("Pink", "Black", "Orange", "Purple", "Green", "Yellow", "Blue", "White", "Grey");
        List<Integer> sabliers = List.of(2,0,1,1,1,1,0,1,1);

        for (int i = 0; i<9; i++){
            int orientation= r.nextInt(max);
            Tiles tile = new Tiles(couleurs.get(i),orientation);
            Alibi alibi = new Alibi(false, sabliers.get(i), couleurs.get(i) );
            district.add(tile);
            cartes.add(alibi);
        }
        Collections.shuffle(cartes);

        Alibi carteMrJack = new Alibi(true,cartes.get(0).hourglassNumber, cartes.get(0).color);
        cartes.set(0,carteMrJack);
        for (int i = 0; i< sabliers.size(); i++){
            System.out.printf("[%s, %d, %s]",cartes.get(i).color, cartes.get(i).hourglassNumber, cartes.get(i).isCulprit);
        }

        int numeroTour = 0;

        Collections.shuffle(district);//permet de mélanger nameDistrict pour avoir une nouvelle expérience à chaque partie.
        Tiles tileSherlock = new Tiles(district.get(0).getColor(), 1);
        Tiles tileWatson = new Tiles(district.get(2).getColor(), 3);
        Tiles tileToby = new Tiles(district.get(7).getColor(), 0);
        district.set(0,tileSherlock);
        district.set(2, tileWatson);
        district.set(7, tileToby);

        //on set les tiles en face des détectives de sorte à ce qu'ils aient un mur en face

        Detective sherlock = new Detective( 0); //on initialise la position de sherlock, instance de détective. On fait de meme pour watson et toby
        Detective watson = new Detective( 4);
        Detective toby = new Detective( 8);

        ArrayList<Jeton> jet = new ArrayList<>();
        jet.add(new Jeton("Alibi", "Sherlock"));
        jet.add(new Jeton("Toby", "Watson"));
        jet.add(new Jeton("Rotation", "Echange"));
        jet.add(new Jeton("Rotation", "Joker"));

        for (Tiles tiles : district) {
            System.out.println("");
            System.out.printf("[%s, %d]", tiles.color, tiles.orientation);
        }
        for(int j = 0; j<8; j++){
            ArrayList<String> possibilitesJeton = new ArrayList<>();
            possibilitesJeton.add("empty");
            possibilitesJeton.add("empty");
            possibilitesJeton.add("empty");
            possibilitesJeton.add("empty");
            for (int k = 0; k<10; k++){

                int faceJet1 = r.nextInt(2);
                int faceJet2 = r.nextInt(2);
                int faceJet3 = r.nextInt(2);
                int faceJet4 = r.nextInt(2);

                if(possibilitesJeton.get(0)=="Pris" && possibilitesJeton.get(1)=="Pris" && possibilitesJeton.get(2)=="Pris" && possibilitesJeton.get(3)=="Pris"){
                    System.out.println("Tous les jetons ont été tirés. on passe au tour suivant");
                    possibilitesJeton.set(0,"empty");
                    possibilitesJeton.set(1,"empty");
                    possibilitesJeton.set(2,"empty");
                    possibilitesJeton.set(3,"empty");
                }
                if (possibilitesJeton.get(0)!="Pris"){
                    if (faceJet1 == 0){
                        possibilitesJeton.set(0,jet.get(0).getRecto());
                    }
                    else{
                        possibilitesJeton.set(0,jet.get(0).getVerso());
                    }
                }
                if (possibilitesJeton.get(1)!="Pris"){
                    if (faceJet2 == 0){
                        possibilitesJeton.set(1,jet.get(1).getRecto());
                    }
                    else{
                        possibilitesJeton.set(1,jet.get(1).getVerso());
                    }
                }
                if (possibilitesJeton.get(2)!="Pris"){
                    if (faceJet3 == 0){
                        possibilitesJeton.set(2,jet.get(2).getRecto());
                    }
                    else{
                        possibilitesJeton.set(2,jet.get(2).getVerso());
                    }
                }
                if (possibilitesJeton.get(3)!="Pris"){
                    if (faceJet4 == 0){
                        possibilitesJeton.set(3,jet.get(3).getRecto());
                    }
                    else{
                        possibilitesJeton.set(3,jet.get(3).getVerso());
                    }
                }

                System.out.println(possibilitesJeton);
                System.out.println("Choisissez un jeton (0,1,2, ou 3)");
                int numeroJeton = scanner.nextInt();
                while (possibilitesJeton.get(numeroJeton)=="Pris"){
                    System.out.println("Ce jeton est déjà pris, veuillez prendre autre chose");
                    System.out.println(possibilitesJeton);
                    System.out.println("Choisissez un jeton (0,1,2, ou 3)");
                    numeroJeton = scanner.nextInt();
                }
                System.out.println(Jeton.executionAction(possibilitesJeton.get(numeroJeton)));
                possibilitesJeton.set(numeroJeton, "Pris");
            }
        }
    }
}
