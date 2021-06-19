package fr.arthur.zoo;

import fr.arthur.fichiers.Fichier;

import java.util.*;

public class Zoo {
    //    ATTRIBUT

    private List<Habitat> habitatList = new ArrayList<>();
    private List<Personne> employeesListe = new ArrayList<>();
    private List<Animal> storeAnimal = new ArrayList<>();
    private boolean activate = true;

    public static void main(String[] args) {
        Zoo zoo = new Zoo();
        System.out.println("Bonjour, félicitation pour l'acquisition de votre Zoo.");

        while (zoo.activate){
            zoo.app();
        }

    }

    //LECTURE CSV

    public Zoo(){
        List<String> lignes = Fichier.recupererLignes("zoo\\zoo2.csv");


        Aquarium aquarium = new Aquarium(5);
        Cage cage= new Cage(5);

        habitatList.add(aquarium);
        habitatList.add(cage);

        for (String ligne : lignes){
            String[] tab = ligne.split(",");
            String habitat = tab[6];
            Animal animal = new Animal(tab[0], tab[17], tab[6]);
            storeAnimal.add(animal);
//
        }
    }


    private void app(){
        System.out.println("-------------------------------------------");
        System.out.println("Interface: \n"+
                "Vous avez " + filtreHabitat("Aquarium").size() + " aquarium. \n" +
                "Vous avez " + filtreHabitat("Cage").size() + " cage. \n" +
                "Habitant dans l'aquarium: " + getHabitatList().get(0).getAnimaux().size()+ "\n" +
                "Habitant dans la cage: " + getHabitatList().get(1).getAnimaux().size()+ "\n" +
                "Vétérinaire: " +  filtreMetier("Soigneur").size() + "\n" +
                "Technicien de surface: " + filtreMetier("Janitor").size()
        );
        Scanner scan = new Scanner(System.in);
        System.out.println("Entrez 1 à 6 \n" +
                "1/ Soigner un animal \n" +
                "2/ Nettoyer un Enclos \n" +
                "3/ Engager une personne \n" +
                "4/ Acheter un animal \n" +
                "5/ Acheter un environnement \n" +
                "6/ Statistique \n" +
                "7/ Eteindre l'application \n"
                );
        int choix = scan.nextInt();
        switch (choix){
            case 1:
                Habitat habitat = afficherEtRecupererHabitat();
                Animal animal = afficherEtRecupererAnimal(habitat);
                if (animal != null){
                    chercherSoigneurEtSoigner(animal);
                }
                break;
            case 2: Habitat habitat1 = afficherEtRecupererHabitat();
                    chercherJanitorEtNettoyer(habitat1);
                break;
            case 3: achatEmploye();
                break;
            case 4: achatAnimal();
                break;
            case 5: acheterHabitat();
                break;
            case 6: affichageStats("aquatic", "cage");
                break;
            case 7: eteindreApp(); ;
                break;
            default:
                System.out.println("Vous n'avez pas choisis une option valide: rentrer votre choix en numérique 1-6");
        }
    }

//   ------ EMPLOYER --------


    public void achatSoigneur(){
        employeesListe.add(new Soigneur());
        System.out.println("La liste du personnel: "+getEmploye());
    }

    public void achatJanitor(){
        employeesListe.add(new Janitor());
        System.out.println("La liste du personnel: "+getEmploye());
    }
    public void achatEmploye(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Qui voulez-vous engager? 1/Vétérinaire 2/Technicien de surface");
        int choix = scan.nextInt();
        if(choix == 1){
            System.out.println("Vous avez engagé un nouveau vétérinaire.");
            achatSoigneur();
        } if(choix == 2){
            System.out.println("Vous avez engagé un nouveau technicien de surface.");
            achatJanitor();
        }
    }

    public List<Personne> getEmploye() {
        return employeesListe;
    }

//   -------- ANIMAL ----------

    public void achatAnimal(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Quelle animal voulez-vous acheté? Rentrez l'index correspondant à l'animal.");
        Habitat habitat = afficherEtRecupererHabitat();

        List<Animal> animalCompatible = getAnimalEnvironnement(habitat);
        int i = 1;
        for(Animal animal: animalCompatible){
            System.out.println(i + "/" + animal.getNom());
            i++;
        }
        int choix = scan.nextInt() - 1;
        if(choix > animalCompatible.size()){
            return;
        }
        Animal animalChoix = animalCompatible.get(choix);
        habitat.ajouterUnAnimal(animalChoix);
        System.out.println("Vous avez acheté un " + animalChoix.getNom());
    }

    public List<Animal> getAnimalEnvironnement(Habitat habitat){
        List<Animal> compatibleAnimalListe = new ArrayList<>();
        for(Animal animal : storeAnimal){
            if(animal.getEnvironnement().equals(habitat.getNom())){
                compatibleAnimalListe.add(animal);
            }
        }
        return compatibleAnimalListe;
    }

//               ---------- HABITAT ----------

    public void acheterHabitat(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Quelle habitat voulez-vous acheter? 1/Aquarium 2/Cage");
        int choix = scan.nextInt();
        if (choix == 1){
            Aquarium aquarium = new Aquarium(5);
            habitatList.add(aquarium);
        } if(choix == 2){
            Cage cage= new Cage(5);
            habitatList.add(cage);
        }

    }
//   ------- Affichage et Récupération --------

    public List<Habitat> getHabitatList(){return habitatList; }

    public Habitat afficherEtRecupererHabitat() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Choisir Habitat: ");
        int i = 1;
        for (Habitat habitat : habitatList) {
            System.out.println(i + "/ " + habitat.getNom());
            i++;
        }
        return habitatList.get(scan.nextInt() - 1);
    }

    public Animal afficherEtRecupererAnimal(Habitat habitat) {
            if (habitat.getAnimaux().size() == 0){
                System.out.println("Vous n'avez pas d'animal dans cette enclos.");
                return null;
            }
            Scanner scan = new Scanner(System.in);
            System.out.println("Choisir animal: ");
            int i = 1;
            for (Animal animal : habitat.getAnimaux()) {
                System.out.println(i + "/ " + animal.getNom());
                i++;
            }
            return habitat.getAnimaux().get(scan.nextInt() -1 );
    }


    public void  chercherSoigneurEtSoigner(Animal animal){
        if(getSoigneurs().size() > 0){
            for (Soigneur soigneur: getSoigneurs()) {
                if (soigneur.getSoinType() == animal.getType()) {
                    soigneur.soigner(animal);
                    break;
                }
            }

        } else {
            System.out.println("Vous n'avez de vétérinaire.");
        }
    }

    public List<Soigneur> getSoigneurs() {
        List<Soigneur> soigneurs = new ArrayList<>();
        employeesListe.forEach(employe -> {
            if (employe.getMetier().equals("Soigneur")) {
                System.out.println("Un soigneur a été trouvé!");
                soigneurs.add((Soigneur) employe);
            }
        });
        return soigneurs;
    }

    public void chercherJanitorEtNettoyer(Habitat habitat){
        if(getJanitors().size() > 0){
            for(Janitor janitor: getJanitors()){
                if (janitor.getNettoyageType() == habitat.getNom()){
                    janitor.nettoyer(habitat);
                }if(janitor.getNettoyageType() != habitat.getNom()) {
                    System.out.println("Vous n'avez aucun technicien de surface adapté à cette environnement...");
                }
            }
        }else{
            System.out.println("Vous n'avez pas technicien de surface");
        }
    }

    public List<Janitor> getJanitors(){
        List<Janitor> janitors = new ArrayList<>();
        employeesListe.forEach(employe -> {
            if(employe.getMetier().equals("Janitor")){
                System.out.println("Un technicien de surface a été trouvé");
                janitors.add((Janitor) employe);
            }
        });
        return janitors;
    }

    public List<Personne> filtreMetier(String filtre){
        List<Personne> listeMetier = new ArrayList<>();
        getEmploye().forEach(personne -> {
            if(personne.getMetier().equals(filtre)){
                listeMetier.add(personne);
            }
        });
        return listeMetier;
    }

    public List<Habitat> filtreHabitat(String filtre){
        List<Habitat> listeHabitat = new ArrayList<>();
        getHabitatList().forEach(habitat -> {
            if(habitat.getNom().equals(filtre)){
                listeHabitat.add(habitat);
            }
        });
        return listeHabitat;
    }

    public void affichageStats(String aquatic, String cage){
        Scanner scan = new Scanner(System.in);
        System.out.println("Quelle statitstique voulez-vous visualisé? 1/Animaux 2/Ratio vétérinaire/animal 3/Ratio Janitor/Enclos");
        int choix = scan.nextInt();
        if (choix == 1) {
            if(ratioAnimalAquaticVolant(aquatic) < 101){
                System.out.println("Il y a "+ ratioAnimalAquaticVolant(aquatic) +"% d'animaux type Aquatic \n" +
                        "et il y a " + ratioAnimalAquaticVolant(cage)+"% de type Volant");
            }else {
                System.out.println("Il vous faut au moins un animal volant et un animal aquatic.");
            }
        }if(choix == 2){
            System.out.println("Cette fonctionnalité n'est pas encore disponible...");
        }if(choix == 3){
            System.out.println("Cette fonctionnalité n'est pas encore disponible...");
        }
    }

//    public float ratioAnimalSoigneur{
//        int totalAnimaux = filtreHabitat("Aquarium").size() + filtreHabitat("Cage").size();
//        int totalSoigneur = filtreMetier("Soigneur").size();
//        float ratio = (totalSoigneur/totalAnimaux)*100;
//        return ratio;
//    }
//    public float ratioEnclosJanitor{
//        int totalEnclos = filtreHabitat("Aquarium").size() + filtreHabitat("Cage").size();
//        int totalJanitor = filtreMetier("Janitor").size();
//        float ratio = (totalJanitor/totalEnclos)*100;
//        return ratio;
//    }



    public float ratioAnimalAquaticVolant(String typeAnimal){
        float ratioListe = 0;
        int totalAquatic = getHabitatList().get(0).getAnimaux().size();
        int totalVolant= getHabitatList().get(1).getAnimaux().size();
        int total= totalAquatic + totalVolant;
        float pourcentage = total/100;
        if(typeAnimal == "aquatic"){
            float ratioAquatic =  totalAquatic/pourcentage;
            ratioListe = ratioAquatic;
        }if(typeAnimal == "cage"){
            float ratioVolant =  totalVolant/pourcentage;
            ratioListe = ratioVolant;
        }
        return ratioListe;
    }


    public void eteindreApp(){
        System.out.println("Fermeture de l'application...");
        activate = false;
    }

}
