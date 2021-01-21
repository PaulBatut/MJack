package com.company;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner scanner = new Scanner(System.in);
        int max = 4;
        int pinkOr= r.nextInt(max);
        int blackOr= r.nextInt(max);
        int orangeOr= r.nextInt(max);
        int purpleOr= r.nextInt(max);
        int greenOr= r.nextInt(max);
        int yellowOr= r.nextInt(max);
        int blueOr= r.nextInt(max);
        int whiteOr= r.nextInt(max);
        int greyOr= r.nextInt(max);

        int numeroTour = 0;

        Tiles pink = new Tiles("Pink", pinkOr);
        Tiles black = new Tiles("Black", blackOr);
        Tiles orange = new Tiles("Orange", orangeOr);
        Tiles purple = new Tiles("Purple", purpleOr);
        Tiles green = new Tiles("Green", greenOr);
        Tiles yellow = new Tiles("Yellow", yellowOr);
        Tiles blue = new Tiles("Blue", blueOr);
        Tiles white = new Tiles("White", whiteOr);
        Tiles grey = new Tiles("Grey", greyOr);

        ArrayList<Tiles> district = new ArrayList<>(); //on utilise une arraylist de la classe Tiles pour avoir les noms de districts au même endroit.
        district.add(pink);
        district.add(black);
        district.add(orange);
        district.add(purple);
        district.add(green);
        district.add(yellow);
        district.add(blue);
        district.add(white);
        district.add(grey);
        Collections.shuffle(district);//permet de mélanger nameDistrict pour avoir une nouvelle expérience à chaque partie.
        Tiles tileSherlock = new Tiles(district.get(0).getColor(), 1);
        Tiles tileWatson = new Tiles(district.get(2).getColor(), 3);
        Tiles tileToby = new Tiles(district.get(7).getColor(), 0);
        district.set(0,tileSherlock);
        district.set(2, tileWatson);
        district.set(7, tileToby);

         //on set les tiles en face des détectives de sorte à ce qu'ils aient un mur en face

        Detective sherlock = new Detective("Sherlock", 0); //on initialise l'objet sherlock, instance de détective. On fait de meme pour watson et toby

        Detective watson = new Detective("Watson", 4);

        Detective toby = new Detective("Toby", 8);

        Jeton jeton1 = new Jeton("Alibi", "Sherlock");
        Jeton jeton2 = new Jeton("Toby", "Watson");
        Jeton jeton3 = new Jeton("Rotation", "Echange");
        Jeton jeton4 = new Jeton("Rotation", "Joker");

        ArrayList<Jeton> jet = new ArrayList<>();
        jet.add(jeton1);
        jet.add(jeton2);
        jet.add(jeton3);
        jet.add(jeton4);

        for (int i=0; i<district.size(); i++){
            System.out.println(district.get(i).getColor());
            System.out.println(district.get(i).getOrientation());
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
