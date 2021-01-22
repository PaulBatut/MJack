package com.company;
import java.util.Scanner;

public class Action {

    public static int moveSherlock(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez vous avancer de 1 ou 2 cases?");
        int casesAvance = scanner.nextInt();// le joueur choisir le nombre de cases à avancer le jeton Sherlock
        while (casesAvance<1||casesAvance>3){
            casesAvance = scanner.nextInt();
        }
        scanner.close();
        if (casesAvance == 1){
            System.out.println("Vous avancez de 1 case");
            return casesAvance;
        }
        if (casesAvance == 2){
            System.out.println("Vous avancez de 2 cases");
            return casesAvance;
        }
        else {
            System.out.println("ca marche pas tocard");
        }
        return 0;
    }
    public void moveWatson(){

    }
    public void moveToby(){

    }
    public void joker(){

    }
    public void alibi(){

    }
    public void echange(){

    }

}
