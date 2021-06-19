package fr.arthur.zoo;

import java.util.Scanner;

public class Soigneur extends Personne {
    private int soinType;

    public Soigneur() {
        super("Soigneur");
        this.soinType = choixSoinType();
    }

    public int getSoinType() {
        return soinType;
    }

    public void setSoinType(int soinType) {
        this.soinType = soinType;
    }

    @Override
    public String toString() {
        return "Soigneur{" +
                "soinType=" + soinType +
                '}';
    }

    public int choixSoinType(){
        int choixSoinType;
        Scanner scan = new Scanner(System.in);
        System.out.println("Quelle type d'animal peut-il soigner? 1-7");
        int choix = scan.nextInt();
        choixSoinType = choix;
        return choixSoinType;
    }

    public void soigner(Animal a){

        if(a.getType() == soinType){
            System.out.println("Je vais aller le soigner.");
        }
    }

}
