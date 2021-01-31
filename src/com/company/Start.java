package com.company;

import javax.print.attribute.standard.MediaSize;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;


public class Start {
    public static void main(String[] args) {
        boolean jackIsHidden = true; //Initialise le fait que les enqueteurs n'ont pas trouvé Jack
        Random r = new Random(); //initialise la partie aléatoire du jeu
        Scanner scanner = new Scanner(System.in); // nous permet d'entrer du texte ou des int. Ne sera pas utile dans la partie graphique
        int t=0;
        int numeroTour = 1; //on garde un compteur de tours puisque l'ordre de pioche des jetons change si c'est un tour pair ou impair
        int sabliersJack = 0; //Mr Jack a initialement 0 sabliers, et il lui en faut  6 pour gagner la partie


        int max = 4; //le nombre de possibilités pour l'aléatoire. on a mis max = 4 car il y a 4 orientations possibles
        ArrayList<Tiles> district = new ArrayList<>(); //on utilise une arraylist de la classe Tiles pour avoir les noms de districts au même endroit.
        ArrayList<Alibi> cartes = new ArrayList<>(); //on utilise une arraylist de la classe Alibi pour avoir toutes les cartes alibi au meme endroit
        List<String> couleurs = List.of("Pink", "Black", "Orange", "Purple", "Green", "Yellow", "Blue", "White", "Grey");// servira pour la queue et l'arraylist district
        List<Integer> sabliers = List.of(2,0,1,1,1,1,0,1,1); //le nombre de sabliers pour chaque carte

        for (int i = 0; i<9; i++){
            int orientation= r.nextInt(max);
            Tiles tile = new Tiles(couleurs.get(i),orientation, true, false); //crée une instance de la classe Tiles, récupère l'élément i de couleurs, une orientation aléatoire et fixe la face a recto
            Alibi alibi = new Alibi(false, sabliers.get(i), couleurs.get(i) );//crée une instance de la classe Alibi, indique que le suspect est d'office innocent, et procédé identique quee ligne d'avant
            district.add(tile); //ces deux lignes ajoutent les objets créés à des ArrayList définis précédemment. Vu qu'on fait ce procédé 9 fois, on aura 9 éléments dans ces listes.
            cartes.add(alibi);
        }
        Collections.shuffle(cartes); //Utilisation de shuffle permet de mélanger les cartes
        Collections.shuffle(district);//permet de mélanger nameDistrict pour avoir une nouvelle expérience à chaque partie.

        Alibi carteMrJack = new Alibi(true,cartes.get(0).hourglassNumber, cartes.get(0).color); //on veut que la premiere carte piochée par Mr Jack soit le coupable. On prend donc la valeur de couleur et l'orientation de la première carte, et on lui attitre la valeur true pour que ce soit le coupable
        cartes.set(0,carteMrJack);// on remplace la première carte par la nouvelle instance
        Queue<Alibi> pioche = new ArrayBlockingQueue<Alibi>(9); //on créé une queue, qui sera plus facile d'utilisation qu'un arraylist

        for (int i = 0; i< sabliers.size(); i++){
            System.out.printf("[%s, %d, %s]",cartes.get(i).color, cartes.get(i).hourglassNumber, cartes.get(i).isCulprit); //affiche la couleur, le nombre de sabliers et si c'est le coupable dans la console
            pioche.add(cartes.get(i)); //on ajoute chaque élément à la pioche

        }

        Tiles tileSherlock = new Tiles(district.get(0).getColor(), 1, true, false); //comme chaque détective doit initialement faire face à un mur, on initialise les murs à certaines positions précises avec des objets qui reprennent les positions et couleurs des objets initialement à leurs position.
        Tiles tileWatson = new Tiles(district.get(2).getColor(), 3, true, false);
        Tiles tileToby = new Tiles(district.get(7).getColor(), 0, true, false);
        district.set(0, tileSherlock); //on set les tiles en face des détectives de sorte à ce qu'ils aient un mur en face
        district.set(2, tileWatson);
        district.set(7, tileToby);

        String identiteJack = pioche.poll().getColor(); //on pioche dans les cartes et on donne une identité à Mr Jack, qu'il ne donnera pas au détective

        for (int i = 0; i< district.size(); i++){
            if (district.get(i).color==identiteJack){
                Tiles tileJack = new Tiles(district.get(i).color, district.get(i).orientation, true, true);
                district.set(i,tileJack);
            }
        }

        ArrayList<Detective> detectives = new ArrayList<>();
        detectives.add(new Detective("Sherlock", 0));  //on initialise la position de sherlock, instance de détective. On fait de meme pour watson et toby
        detectives.add(new Detective( "Watson", 4));
        detectives.add(new Detective( "Toby", 8));


        System.out.printf("\n\nl'identité de Mr Jack est %s \n\n", identiteJack);
        ArrayList<Jeton> jet = new ArrayList<>(); //cet arraylist nous permet d'initialiser des jetons. On aurait pu faire comme précédemment mais cela aurait été aussi voire plus long
        jet.add(new Jeton("Alibi", "Sherlock"));
        jet.add(new Jeton("Toby", "Watson"));
        jet.add(new Jeton("Rotation", "Echange"));
        jet.add(new Jeton("Rotation", "Joker"));

        ArrayList<Tiles> suspects = new ArrayList<>();

        ArrayList<String> casesDetectives = new ArrayList<>();
        for (int i =0; i<12; i++){
            casesDetectives.add("empty");
        }

        ArrayList<String> possibilitesJeton = new ArrayList<>(); //cet arraylist définit les jetons qui seront tirés
        possibilitesJeton.add("empty"); // on initialise toutes les valeurs pour créer l'arraylist. On modifiera ces valeurs par la suite
        possibilitesJeton.add("empty");
        possibilitesJeton.add("empty");
        possibilitesJeton.add("empty");
        int faceJet1 = 0;
        int faceJet2 = 0;
        int faceJet3 = 0;
        int faceJet4 = 0;
        int numeroPris=0; //combientième jeton pioché
        while (sabliersJack<6||jackIsHidden){// ici on énonce les conditions de victoire
            System.out.printf("  X         1               2               3           X\n 0 [%s, %d, %s] [%s, %d, %s] [%s, %d, %s] 4\n11 [%s, %d, %s] [%s, %d, %s] [%s, %d, %s] 5\n10 [%s, %d, %s] [%s, %d, %s] [%s, %d, %s] 6\nX         9               8               7           X", district.get(0).color, district.get(0).orientation,district.get(0).recto,district.get(1).color, district.get(1).orientation,district.get(1).recto,district.get(2).color, district.get(2).orientation,district.get(2).recto,district.get(3).color, district.get(3).orientation,district.get(3).recto,district.get(4).color, district.get(4).orientation,district.get(4).recto,district.get(5).color, district.get(5).orientation,district.get(5).recto,district.get(6).color, district.get(6).orientation,district.get(6).recto,district.get(7).color, district.get(7).orientation,district.get(7).recto,district.get(8).color, district.get(8).orientation,district.get(8).recto);
            System.out.println("");
            System.out.println("");
            if(possibilitesJeton.get(0)=="Pris" && possibilitesJeton.get(1)=="Pris" && possibilitesJeton.get(2)=="Pris" && possibilitesJeton.get(3)=="Pris"){ //s'éxécute seulemnt si tous les jetons sont pris
                int detectionJack = 0;
                System.out.println("Tous les jetons ont été tirés. on passe au tour suivant");
                sabliersJack+=1;
                possibilitesJeton.set(0,"empty");
                possibilitesJeton.set(1,"empty");
                possibilitesJeton.set(2,"empty");
                possibilitesJeton.set(3,"empty");
                faceJet1= (faceJet1+1)%2; //on retourne les jetons
                faceJet2= (faceJet2+1)%2;
                faceJet3= (faceJet3+1)%2;
                faceJet4= (faceJet4+1)%2;
                ArrayList<Tiles> innocents = district;
                /*for (int i=0; i<detectives.size();i++){
                    switch (detectives.get(i).position){
                        case 0:
                            for(int j=0;j<3;j++) {
                                if (district.get(j).recto&&(district.get(j).orientation == 0 || district.get(j).orientation == 2 || district.get(j).orientation == 3)) {
                                    if (!district.get(j).isSus) {
                                        Tiles newSuspect = new Tiles(district.get(j).color, district.get(j).orientation, false, district.get(j).isSus);
                                        suspects.add(newSuspect);
                                        detectionJack+=1;

                                    }
                                    if (district.get(j).orientation == 3) {
                                        break;
                                    }
                                }

                            }

                    case 1:
                        for(int j=0;j<3;j++) {
                                if (district.get(j).recto&&(district.get(j * 3).orientation == 0 || district.get(j * 3).orientation == 1 || district.get(j * 3).orientation == 3)) {
                                    if (district.get(j * 3).color != identiteJack) {
                                        Tiles newSuspect = new Tiles(district.get(j * 3).color, district.get(j * 3).orientation, false, district.get(j * 3).isSus);
                                        suspects.add(newSuspect);
                                        detectionJack+=1;
                                    }
                                    if (district.get(j).orientation == 0) {
                                        break;
                                    }
                                }
                            }
                        case 2:
                            System.out.println("on entre dans le case 2");
                            for(int j=0;j<3;j++){
                                if (district.get(j).recto&&(district.get(1+j*3).orientation==0||district.get(1+j*3).orientation==1||district.get(1+j*3).orientation==3)){
                                    if (!district.get(1 + j * 3).color.equals(identiteJack)){
                                        System.out.println("on entre dans le 1e if");
                                        Tiles newSuspect = new Tiles(district.get(1+j*3).color, district.get(1+j*3).orientation, false, district.get(1+j*3).isSus);
                                        suspects.add(newSuspect);
                                        detectionJack+=1;
                                    }
                                    if(district.get(1+j*3).orientation==0){
                                        System.out.println("on entre dans le 2e if");
                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                        case 3:
                            for(int j=0;j<3;j++){
                                if (district.get(j).recto&&(district.get(2+j*3).orientation==0||district.get(2+j*3).orientation==1||district.get(2+j*3).orientation==3)){
                                    if (district.get(2+j*3).color!=identiteJack){
                                        Tiles newSuspect = new Tiles(district.get(2+j*3).color, district.get(2+j*3).orientation, false, district.get(2+j*3).isSus);
                                        suspects.add(newSuspect);
                                        detectionJack+=1;
                                    }
                                    if(district.get(2+j*3).orientation==0){
                                        break;
                                    }
                                }
                            }
                        case 4:
                            for(int j=0;j<3;j++){
                                if (district.get(j).recto&&(district.get(2-j).orientation == 1 || district.get(2-j).orientation == 2 || district.get(2-j).orientation == 4)) {
                                    if (!district.get(2-j).isSus) {
                                        Tiles newSuspect = new Tiles(district.get(2-j).color, district.get(2-j).orientation, false, district.get(2-j).isSus);
                                        suspects.add(newSuspect);
                                        detectionJack+=1;
                                    }
                                    if (district.get(2-j).orientation == 4) {
                                        break;
                                    }
                                }
                            }
                        case 5:
                            for(int j=0;j<3;j++){
                                if (district.get(j).recto&&(district.get(5-j).orientation == 1 || district.get(5-j).orientation == 2 || district.get(5-j).orientation == 4)) {
                                    if (!district.get(5-j).isSus) {
                                        Tiles newSuspect = new Tiles(district.get(5-j).color, district.get(5-j).orientation, false, district.get(6-j).isSus);
                                        suspects.add(newSuspect);
                                        detectionJack+=1;
                                    }
                                    if (district.get(5-j).orientation == 4) {
                                        break;
                                    }
                                }
                            }
                        case 6:
                            for(int j=0;j<3;j++){
                                if (district.get(j).recto&&(district.get(8-j).orientation == 1 || district.get(8-j).orientation == 2 || district.get(8-j).orientation == 4)) {
                                    if (!district.get(8-j).isSus) {
                                        Tiles newSuspect = new Tiles(district.get(8-j).color, district.get(8-j).orientation, false, district.get(8-j).isSus);
                                        suspects.add(newSuspect);
                                        detectionJack+=1;
                                    }
                                    if (district.get(8-j).orientation == 4) {
                                        break;
                                    }
                                }
                            }
                    case 7:
                        for(int j=0;j<3;j++){
                            if (district.get(j).recto&&(district.get(8-3*j).orientation==1||district.get(8-3*j).orientation==2||district.get(8-3*j).orientation==3)){
                                if(!district.get(8-3*j).isSus){
                                    Tiles newSuspect = new Tiles(district.get(8-3*j).color, district.get(8-3*j).orientation, false, district.get(8-3*j).isSus);
                                    suspects.add(newSuspect);
                                    detectionJack+=1;
                                }
                                if(district.get(8-3*j).orientation == 2){
                                    break;
                                }
                            }
                        }
                    case 8:
                        for(int j=0;j<3;j++){
                            if (district.get(j).recto&&(district.get(7-3*j).orientation==1||district.get(7-3*j).orientation==2||district.get(7-3*j).orientation==3)){
                                if(!district.get(7-3*j).isSus){
                                    Tiles newSuspect = new Tiles(district.get(7-3*j).color, district.get(7-3*j).orientation, false, district.get(7-3*j).isSus);
                                    suspects.add(newSuspect);
                                    detectionJack+=1;
                                }
                                if(district.get(8-3*j).orientation == 2){
                                    break;
                                }
                            }
                        }
                    case 9:

                        for(int j=0;j<3;j++){
                            if (district.get(j).recto&&(district.get(6-3*j).orientation==1||district.get(6-3*j).orientation==2||district.get(6-3*j).orientation==3)){
                                if(!district.get(6-3*j).isSus){
                                    Tiles newSuspect = new Tiles(district.get(6-3*j).color, district.get(6-3*j).orientation, false, district.get(6-3*j).isSus);
                                    suspects.add(newSuspect);
                                    detectionJack+=1;
                                }
                                if(district.get(6-3*j).orientation == 2){
                                    break;
                                }
                            }
                        }
                    case 10:
                        for(int j=0;j<3;j++) {
                                if (district.get(j).recto&&(district.get(6+j).orientation == 0 || district.get(6+j).orientation == 2 || district.get(6+j).orientation == 3)) {
                                    if (!district.get(6+j).isSus) {
                                        Tiles newSuspect = new Tiles(district.get(6+j).color, district.get(6+j).orientation, false, district.get(6+j).isSus);
                                        suspects.add(newSuspect);
                                        detectionJack+=1;
                                    }
                                    if (district.get(6+j).orientation == 3) {
                                        break;
                                    }
                                }
                            }
                    case 11:
                        for(int j=0;j<3;j++){
                            if (district.get(j).recto&&(district.get(3+j).orientation == 0 || district.get(3+j).orientation == 2 || district.get(3+j).orientation == 3)) {
                                if (!district.get(3+j).isSus) {
                                    Tiles newSuspect = new Tiles(district.get(3+j).color, district.get(3+j).orientation, false, district.get(3+j).isSus);
                                    suspects.add(newSuspect);
                                    detectionJack+=1;
                                }
                                if (district.get(3+j).orientation == 3) {
                                    break;
                                }
                            }
                        }
                        break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + detectives.get(i).position);
                    }
                }
                if (detectionJack>0) {
                    for (int j = 0; j < suspects.size(); j++) {
                        for (int k = 0; k < district.size(); k++) {
                            if (suspects.get(j).color.equals(district.get(k).color)) {
                                innocents.set(k, new Tiles("0", 0, false, false));
                            }
                        }
                    }
                    for (int j = 0; j < innocents.size(); j++) {

                        System.out.printf("[%s]", innocents.get(j).color);
                    }
                }
                System.out.println("");
                for (int j=0; j<innocents.size(); j++){

                    while (innocents.get(innocents.size()-j).color=="0"){
                        innocents.remove(innocents.size()-j);
                    }
                    System.out.printf("[%s]",innocents.get(j).color);
                }
*/
                sabliersJack+=1;
                System.out.printf("Mr Jack a maintenant %d jetons\n", sabliersJack);
                numeroTour+=1;

            }

            if(numeroTour%2==1&&numeroPris==0) { //on relance les jetons seulement au début des tours impairs
                faceJet1 = r.nextInt(2);//on lance les jetons pour avoir leur face
                faceJet2 = r.nextInt(2);
                faceJet3 = r.nextInt(2);
                faceJet4 = r.nextInt(2);
            }

            if (possibilitesJeton.get(0)!="Pris"){ //on vérifie si le jeton n'a pas encore été pris.
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
                for (int i=0; i<=2; i++){ //sert ici pour avoir les positions des détectives
                    System.out.printf("Position %s : %d ", detectives.get(i).name,detectives.get(i).position);
                }
                if((numeroTour%2==0&&(numeroPris==1||numeroPris==2))||(numeroTour%2==1&&(numeroPris==0||numeroPris==3))){//permet d'afficher si c'est à l'enqueteur ou a mr jack de piocher
                    System.out.println("\nAu tour de Sherlock de piocher");
                }
                else{
                    System.out.println("\nAu tour de Mr Jack de piocher");
                }
                System.out.println("Choisissez un jeton (0,1,2, ou 3)");

                int numeroJeton = Integer.parseInt(scanner.nextLine()); //le joueur peut choisir un jeton

                while(numeroJeton<0||numeroJeton>3){ //sert à  vérifier que le numéro choisi est bien dans l'arraylist. Ne sera pas utilisé dans la partie graphique
                    System.out.println("Ce jeton n'existe pas, veuillez prendre autre chose");
                    System.out.println(possibilitesJeton);
                    System.out.println("Choisissez un jeton (0,1,2, ou 3)");
                    numeroJeton = Integer.parseInt(scanner.nextLine());
                }
                while (possibilitesJeton.get(numeroJeton)=="Pris"){ //sert à verifier que le numéro de jeton choisi ne correspond pas à un jeton qui a déjà été pris
                    System.out.println("Ce jeton est déjà pris, veuillez prendre autre chose");
                    System.out.println(possibilitesJeton);
                    System.out.println("Choisissez un jeton (0,1,2, ou 3)");
                    numeroJeton = Integer.parseInt(scanner.nextLine());
                }

                System.out.println(Jeton.executionAction(possibilitesJeton.get(numeroJeton), detectives, pioche, district, numeroTour, numeroPris, sabliersJack));//appelle la classe jeton et lance la méthode executeAction
                numeroPris=(numeroPris+1)%4;

                possibilitesJeton.set(numeroJeton, "Pris");//le jeton qu'on a pris est set à "Pris" pour ne pas être re pioché durant le tour
            }
        }
    }
