package fr.arthur.zoo;

import java.util.ArrayList;
import java.util.List;

public  class Habitat {

    private List<Animal> animalListe = new ArrayList<>();

    private String nom;
    private int taille;

    public Habitat(String nom, int taille) {
        this.nom = nom;
        this.taille = taille;
    }

    public String getNom() {
        return nom;
    }


    public int getTaille() {
        return taille;
    }
    public List<Animal> getAnimaux() {
        return animalListe;
    }

    @Override
    public String toString() {
        return "Habitat{" +
                "animalListe=" + animalListe +
                ", nom='" + nom + '\'' +
                ", taille=" + taille +
                '}';
    }

    public void ajouterUnAnimal(Animal a){
        if( animalListe.size() < taille  && nom.equals(a.getEnvironnement())){
            animalListe.add(a);
        }if(animalListe.size()>taille){
            System.out.println("Il n'y plus de place dans cette" + a.getEnvironnement());
        }if(nom != a.getEnvironnement()){
            System.out.println("L'animal n'est pas adapté à cette environnement.");
        }
    }

    public void ajouterPlusieursAnimaux(List<Animal> a){
        a.forEach(animal -> {
            ajouterUnAnimal(animal);
        });
    }



   /* public void retierPluseursAnimal(List<Animal> a){
        a.forEach(animal ->{
            retierUnAnimal(animal);
        });
    }*/
}

