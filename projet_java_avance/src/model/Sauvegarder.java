import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Sauvegarder  {


    public static String genererMD5(String entree) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] tableauDeBytes = md.digest(entree.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : tableauDeBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static void sauvegarderScore(String cheminFichier, String joueur, int score) throws IOException, NoSuchAlgorithmException {
        String donneesAHacher = joueur + ":" + score;
        String donneesHachees = genererMD5(donneesAHacher);
        try (BufferedWriter ecrivain = new BufferedWriter(new FileWriter(cheminFichier, true))) {
            ecrivain.write(joueur + ":" + score + ":" + donneesHachees + "\n");
        }
    }

    public static List<String> lireScores(String cheminFichier) throws IOException, NoSuchAlgorithmException {
        List<String> scoresValides = new ArrayList<>();
        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = lecteur.readLine()) != null) {
                String[] parties = ligne.split(":");
                if (parties.length == 3) {
                    String joueur = parties[0];
                    int score = Integer.parseInt(parties[1]);
                    String hachageStocke = parties[2];
                    String donneesAHacher = joueur + ":" + score;
                    String hachageCalcule = genererMD5(donneesAHacher);
                    if (hachageCalcule.equals(hachageStocke)) {
                        scoresValides.add(ligne);
                    } else {
                        System.out.println("Le score pour " + joueur + " est corrompu et a été ignoré.");
                    }
                }
            }
        }
        return scoresValides;
    }

    public void chargerScore(){

    }

    public void sauvegarderScore(){
        String cheminFichier = "monFichier.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(cheminFichier, true))) {
            writer.write("");
            // La méthode flush() force l'écriture des données dans le fichier
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
