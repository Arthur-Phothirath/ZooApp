package fr.arthur.fichiers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Fichier {
    private  static  String cheminCree = "F:\\Users\\Arthur\\IdeaProjects\\JavaArthur\\JavaArthur\\JavaArthur\\src\\main\\java\\fr\\arthur\\";

    public static void main(String[] args) {

    }

    public static List<String> recupererLignes(String chemin){
        List<String> liste= new ArrayList<>();
        String line = "sjidjklnqklsd";

        try {
            BufferedReader br = new BufferedReader(new FileReader(cheminCree + chemin));

            while((line = br.readLine()) != null){
                liste.add(line);
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return liste;
    }

}
