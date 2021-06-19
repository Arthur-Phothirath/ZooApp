package fr.arthur.zoo;

import java.util.Scanner;

public class Janitor extends Personne{
    private String nettoyageType;
    public Janitor() {
        super("Janitor");
        this.nettoyageType = choixNettoyageType();
    }

    public String getNettoyageType() {
        return nettoyageType;
    }

    public void setNettoyageType(String nettoyageType) {
        this.nettoyageType = nettoyageType;
    }

//    @Override
//    public String toString() {
//        return "Janitor{" +
//                "nettoyageType='" + nettoyageType + '\'' +
//                '}';
//    }

    public String choixNettoyageType(){
        String nettoyageType;
        Scanner scan = new Scanner(System.in);
        System.out.println("Environnement peut-il nettoyer? 1/Aquarium 2/Cage");
        int choix = scan.nextInt();
        if(choix == 1){
            nettoyageType = "Aquarium";
        }else{
            nettoyageType = "Cage";
        }
        return nettoyageType;
    }

    public void nettoyer(Habitat h){
        if(h.getNom() == nettoyageType){
            System.out.println("Je vais aller nettoyer -> " + h.getNom() + ".");
        }

    }


}
