package model;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Sauvegarder  {


    public static String genererMD5(String texte) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");

            // Convertit le texte en un tableau de bytes et met à jour le digest
            byte[] hash = digest.digest(texte.getBytes());

            // Convertie le tableau de bytes en tableau hexadecimale
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {

                String hex = Integer.toHexString(0xff & hash[i]);
                // Ajoute un zéro devant si nécessaire pour avoir toujours deux chiffres
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            // Retourne le hachage MD5 sous forme de chaîne hexadécimale
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Gère l'exception dans le cas où l'algorithme MD5 n'est pas disponible
            throw new RuntimeException("Erreur lors du hachage MD5", e);
        }
    }

    public static void sauvegarderScores(String cheminFichier, List<Joueur> joueurs) throws IOException, NoSuchAlgorithmException {
        try (BufferedWriter ecrivain = new BufferedWriter(new FileWriter(cheminFichier, false))) {
            for (Joueur joueur : joueurs) {
                String donneesAHacher = joueur.pseudo + ":" + joueur.score;
                String donneesHachees = genererMD5(donneesAHacher);
                ecrivain.write(joueur.pseudo + ":" + donneesHachees + "\n");
            }
        }
    }

    public static List<Joueur> lireScores(String cheminFichier) throws IOException, NoSuchAlgorithmException {
        List<Joueur> joueurs = new ArrayList<>();
        try (BufferedReader lecteur = new BufferedReader(new FileReader(cheminFichier))) {
            String ligne;
            while ((ligne = lecteur.readLine()) != null) {
                String[] parties = ligne.split(":");
                if (parties.length == 3) {
                    String pseudo = parties[0];
                    int score = Integer.parseInt(parties[1]);
                    String hachageStocke = parties[1];
                    String hachageCalcule = genererMD5(pseudo + ":" + score);
                    if (hachageCalcule.equals(hachageStocke)) {
                        System.out.println("Le score du joueur " + pseudo + " est " + score);

                    } else {
                        System.out.println("Les données du joueur " + pseudo + " sont corrompues et ont été ignorées.");
                    }
                }
            }
        }
        return joueurs;
    }


}
