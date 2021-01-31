package com.company;


import java.util.ArrayList;
import java.util.Queue;


public class Jeton {
    String recto;
    String verso;

    public String getRecto() {
        return recto;
    }
    public void setRecto(String recto) {
        this.recto = recto;
    }

    public String getVerso() {
        return verso;
    }
    public void setVerso(String verso) {
        this.verso = verso;
    }

    public Jeton(String recto, String verso) {
        this.recto = recto;
        this.verso = verso;
    }



    public static String executionAction(String actionJeton, ArrayList<Detective> detective, Queue<Alibi> pioche, ArrayList<Tiles> district, int numeroTour, int numeroPris, int sabliersJack){
        switch (actionJeton){
            case "Alibi":
                if ((numeroTour%2==1&&(numeroPris==1||numeroPris==2))||(numeroTour%2==0&&(numeroPris==0||numeroPris==3))){//s'éxécute seulement si c'est au tour de Jack
                    Alibi cartePioche = pioche.poll(); //on tire une carte de la pioche et on donne tous ses attributs à cartePioche, puisqu'on a besoin de différents attributs.
                    sabliersJack = sabliersJack + cartePioche.hourglassNumber; //permet de mettre à jour le nombre de sabliers
                    System.out.printf("La carte piochee est %s\n", cartePioche.getColor());
                }
                else {
                    Action.alibi(pioche, district);//exécute la méthode alibi, qui est dans la classe Action.
                }
                System.out.printf("Mr Jack a %d sabliers\n",sabliersJack);//affiche le nombre de sabliers possédés par Jack
                return "Vous avez choisi l'option Alibi";
            case "Sherlock": //commentaires valables pour Watson et Toby
                int avanceCases = Action.moveDetective(); //execute la methode movedetective de la classe Action, et retourne un nombre
                int nouvellePosition=detective.get(0).position;
                nouvellePosition=(nouvellePosition+avanceCases)%12; //nouvellePosition ne dépasse jamais 11, et revient automatiquement à 0 s'il le dépasse (% correspond au reste)
                Detective newSherlock = new Detective(detective.get(0).name,nouvellePosition); //on utilise un objet temporaire newSherlock qui va récupérer la nouvelle position qui sera mise dans l'array detectives.
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
                int rotateDistrict = Action.rotation(); //rotation va retourner le numéro du district qu'on souhait
                int nombreRotations = Action.quelleRotation(rotateDistrict); // réupère le nombre de quart de tour qu'effectue le district
                int oldRot = district.get(rotateDistrict).orientation; //récupère l'orientation originale du district
                int newRot = (oldRot+nombreRotations)%4; // on ajoute le nombre de quart de tour à l'orientation originale
                Tiles newRotTile = new Tiles(district.get(rotateDistrict).color,newRot, district.get(rotateDistrict).recto, district.get(rotateDistrict).isSus );//créé une nouvelle tile
                district.set(rotateDistrict,newRotTile);//Remplace le district choisi par la nouvelle tile

                return "Vous avez choisi l'option rotation";

            case "Echange":
                int[] districtEchange = Action.echange(); //retourne un tableau avec le numéro du premier élément qui correspond au premier district choisi et le deuxième élément correspond au deuxieme district
                Tiles tileTemporaire= new Tiles(district.get(districtEchange[0]).color, district.get(districtEchange[0]).orientation, district.get(districtEchange[0]).recto, district.get(districtEchange[0]).isSus);//tile qui correspond au premier district
                district.set(districtEchange[0], district.get(districtEchange[1]));//on met le deuxième district à la place du premier
                district.set(districtEchange[1], tileTemporaire); //

                return "Vous avez choisi l'option Echange";

            case "Joker":
                int numeroDetective = Action.joker(numeroTour, numeroPris);//reto
                nouvellePosition = detective.get(numeroDetective).position+1;
                Detective newDetective = new Detective(detective.get(numeroDetective).name,nouvellePosition);
                detective.set(numeroDetective, newDetective);
                return "Vous avez choisi l'option Joker";
        }
        return actionJeton;
    }
}