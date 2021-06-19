package fr.arthur.zoo;

import java.util.Scanner;

public class Personne {
    private String nom;
    private String metier;

    public Personne(String metier) {
        this.nom = choixNom();
        this.metier = metier;
    }

    public String getNom() {
        return nom;
    }

    public String getMetier() {
        return metier;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMetier(String metier) {
        this.metier = metier;
    }

    public String choixNom(){
        Scanner scan = new Scanner(System.in);
        String nomDonner;
        System.out.println("Quelle est votre prénom");
        nomDonner = scan.nextLine();
        System.out.println("Vous avez choisis le prénom: " + nomDonner);
        return nomDonner;
    }

    @Override
    public String toString() {
        return "{" +
                "nom='" + nom + '\'' +
                ", metier='" + metier + '\'' +
                '}';
    }
}
