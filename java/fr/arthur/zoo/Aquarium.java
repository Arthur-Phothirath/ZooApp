package fr.arthur.zoo;

public class Aquarium extends Habitat{

    private int eau;
    public Aquarium(int taille) {
        super("Aquarium", taille);
        this.eau = taille * 100;
    }

    public int getEau() {
        return eau;
    }

    public void setEau(int eau) {
        this.eau = eau;
    }
}
