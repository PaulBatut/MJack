package com.company;
import java.util.Scanner;

public class Action {

    public static int moveDetective(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez vous avancer de 1 ou 2 cases?");
        int casesAvance = scanner.nextInt();// le joueur choisir le nombre de cases à avancer le jeton du detective choisi
        while (casesAvance<1||casesAvance>3){
            casesAvance = scanner.nextInt();
        }
        if (casesAvance == 1){
            System.out.println("Vous avancez de 1 case");
            return casesAvance;
        }
        if (casesAvance == 2){
            System.out.println("Vous avancez de 2 cases");
            return casesAvance;
        }
        return 0;
    }

    public static int joker(int numeroTour, int numeroPris){
        Scanner scanner = new Scanner(System.in);
        if((numeroTour%2==0&&(numeroPris==1||numeroPris==2))||(numeroTour%2==1&&(numeroPris==0||numeroPris==3))){
            System.out.println("voulez-vous faire avancer un détective? (oui:y, non:n)");
            String rep = scanner.next();
            if(rep=="y"){
                System.out.println("Quel détective voulez vous faire avancer?\n Sherlock:0\n Watson:1\n Toby:2\n");
                int chosen = scanner.nextInt();
                while (chosen<0 || chosen>3){
                    System.out.println("Veuillez mettre une valeur entre 0, 1 ou 2");
                    chosen = scanner.nextInt();
                }
                return chosen;
            }
            else{
                return 0;
            }
        }
        else{
            System.out.println("Quel détective voulez vous faire avancer?\n Sherlock:0\n Watson:1\n Toby:2\n");
            int chosen = scanner.nextInt();
            while (chosen<0 || chosen>3){
                System.out.println("Veuillez mettre une valeur entre 0, 1 ou 2");
                chosen = scanner.nextInt();
            }
            return chosen;
        }

    }
    public void alibi(){

    }
    public static int[] echange(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("quel est le numéro du premier district?");
        int district1 = scanner.nextInt();
        while(district1<0||district1>9){
            System.out.println("Le district doit faire partie de la liste (entre 0 et 8)");
            district1 = scanner.nextInt();
        }
        System.out.println("avec quel district devra-t-il échanger?");
        int district2 = scanner.nextInt();
        while(district2<0||district2>9||district2==district1){
            System.out.println("Le deuxième district doit faire partie de notre liste (entre 0 et 8) et est différent de celui choisi précédemment");
            district2 = scanner.nextInt();
        }
        int districtEchange[]= {district1,district2};
        return districtEchange;

    }
    public static int rotation(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est le numéro du district que vous voulez? entre 0 et 8");
        int numeroDistrict = scanner.nextInt();
        while (numeroDistrict<0||numeroDistrict>9){
            numeroDistrict = scanner.nextInt();

        }
        return numeroDistrict;
    }

}
