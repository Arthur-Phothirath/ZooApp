package fr.arthur.zoo;

import static java.lang.Integer.parseInt;

public class Animal {
    private String nom;
    private int type;
    private String environnement;

    public Animal(String nom, String type, String aquatic) {
        this.nom = nom;
        this.type = parseInt(type);
        if(aquatic.equals("1")){
            this.environnement = "Aquarium";
        } else{
            this.environnement = "Cage";
        }
    }

    public String getNom() {
        return nom;
    }

    public int getType() {
        return type;
    }

    public String getEnvironnement() {
        return environnement;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "nom='" + nom + '\'' +
                ", type=" + type +
                ", environnement='" + environnement + '\'' +
                '}';
    }
}
//    animal_name,hair,feathers,eggs,milk,airborne,aquatic,predator,toothed,backbone,breathes,venomous,fins,legs,tail,domestic,catsize,class_type
