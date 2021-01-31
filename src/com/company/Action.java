package com.company;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;

public class Action {

    public static int moveDetective() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Voulez vous avancer de 1 ou 2 cases?");
        int casesAvance = scanner.nextInt();// le joueur choisir le nombre de cases à avancer le jeton du detective choisi
        while (casesAvance < 1 || casesAvance > 2) {//car le détective ne peut avancer que d'une ou de deux cases
            System.out.println("vous ne pouvez avancer que de 1 ou de 2 cases");
            casesAvance = scanner.nextInt();
        }
        if (casesAvance == 1) {//of fait au cas à cas
            System.out.println("Vous avancez de 1 case");
            return casesAvance;//retourne la valeur qu'on a entré, ici 1, même fonctionnement si c'est 2
        }
        if (casesAvance == 2) {
            System.out.println("Vous avancez de 2 cases");
            return casesAvance;
        }
        return 0;
    }

    public static int joker(int numeroTour, int numeroPris) {
        Scanner scanner = new Scanner(System.in);
        if ((numeroTour % 2 == 1 && (numeroPris == 1 || numeroPris == 2)) || (numeroTour % 2 == 0 && (numeroPris == 0 || numeroPris == 3))) {//vérifie si c'est le tour de Mr Jack
            System.out.println("voulez-vous faire avancer un détective? (oui:1, non:0)");
            int rep = scanner.nextInt();
            if (rep == 1) {//vérifie la valeur entrée par l'utilisateur
                System.out.println("Quel détective voulez vous faire avancer?\n Sherlock:0\n Watson:1\n Toby:2\n");
                int chosen = scanner.nextInt();
                while (chosen < 0 || chosen > 3) {//l'utilisateur choisit un détectivequ'il fera avancer
                    System.out.println("Veuillez mettre une valeur entre 0, 1 ou 2");
                    chosen = scanner.nextInt();
                }
                return chosen;
            }
            else {
                return 0;
            }
        }
        else { //on effectue la meme chose que dans la partie demandant si on voulait faire avancer un detective
            System.out.println("Quel détective voulez vous faire avancer?\n Sherlock:0\n Watson:1\n Toby:2\n");
            int chosen = scanner.nextInt();
            while (chosen < 0 || chosen > 3) {
                System.out.println("Veuillez mettre une valeur entre 0, 1 ou 2");
                chosen = scanner.nextInt();
            }
            return chosen;
        }

    }

    public static int alibi( Queue<Alibi> pioche, ArrayList<Tiles> district) {
        String couleurDistrict = pioche.poll().getColor();//on récupère la couleur de la carte alibi piochée
        for (int i = 0; i < district.size(); i++) {//on parcourt ici toute l'arraylist district
            if (district.get(i).color.equals(couleurDistrict)) {//on compare la couleur piochée avec chaque couleur de district
                Tiles tileTemporaire = new Tiles(district.get(i).color, district.get(i).orientation, false, district.get(i).isSus); //on fait une nouvelle tile qui aura les memes caractéristiques que la tile a l'emplacement i, mais qui a son attribut recto mis à false
                district.set(i, tileTemporaire);
                System.out.printf("le district %s est maintenant retourné\n", district.get(i).color);
                return 0;
            }
        }
        return 0;
    }

    public static int[] echange(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("quel est le numéro du premier district?");
        int district1 = scanner.nextInt();//l'utilisateur met le numéro du premier district qu'il souhaite échanger

        while(district1<0||district1>9){//vérifie que le district entré par l'utilisateur est bien dans l'arraylist
            System.out.println("Le district doit faire partie de la liste (entre 0 et 8)");
            district1 = scanner.nextInt();
        }
        System.out.println("avec quel district devra-t-il échanger?");
        int district2 = scanner.nextInt(); //pour le deuxième district échangé
        while(district2<0||district2>9||district2==district1){
            System.out.println("Le deuxième district doit faire partie de notre liste (entre 0 et 8) et est différent de celui choisi précédemment");
            district2 = scanner.nextInt();
        }
        int districtEchange[]= {district1,district2};//créé le tableau qui sera retourné
        return districtEchange;

    }
    public static int rotation(){ //sert a retourner le numéro du district qu'on souhaite
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel est le numéro du district que vous voulez? entre 0 et 8");
        int numeroDistrict = scanner.nextInt();
        while (numeroDistrict<0||numeroDistrict>9){
            numeroDistrict = scanner.nextInt();
        }
        return numeroDistrict;
    }
    public static int quelleRotation(int rotateDistrict){//sert a demander le nombre
        Scanner scanner = new Scanner(System.in);
        System.out.printf("De combien de quarts de tours voulez-vous faire tourner le district %d?\n ", rotateDistrict);
        int nombreRot = scanner.nextInt();
        while (nombreRot<1||nombreRot>2){ //vérifie qu'on fait bien tourner le district d'un ou de deux tours
            System.out.println("on ne peut faire tourner que d'un ou de 2 quarts de tour");
            nombreRot = scanner.nextInt();
        }
        return nombreRot;
    }

}