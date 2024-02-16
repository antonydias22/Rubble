package model;// Déclaration du package

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    /**
     * Affiche le menu principal du jeu
     */
  
    public static void afficher_menu(ArrayList<Joueur> joueur, int[][]tableau,ArrayList<Joueur>score) throws IOException, NoSuchAlgorithmException {
  
        //afficher les différentes options du menu
        System.out.println("" +
                "  ▄████████ ███    █▄  ▀█████████▄  ▀█████████▄   ▄█          ▄████████ \n" +
                "  ███    ███ ███    ███   ███    ███   ███    ███ ███         ███    ███ \n" +
                "  ███    ███ ███    ███   ███    ███   ███    ███ ███         ███    █▀  \n" +
                " ▄███▄▄▄▄██▀ ███    ███  ▄███▄▄▄██▀   ▄███▄▄▄██▀  ███        ▄███▄▄▄     \n" +
                "▀▀███▀▀▀▀▀   ███    ███ ▀▀███▀▀▀██▄  ▀▀███▀▀▀██▄  ███       ▀▀███▀▀▀     \n" +
                "▀███████████ ███    ███   ███    ██▄   ███    ██▄ ███         ███    █▄  \n" +
                "  ███    ███ ███    ███   ███    ███   ███    ███ ███▌    ▄   ███    ███ \n" +
                "  ███    ███ ████████▀  ▄█████████▀  ▄█████████▀  █████▄▄██   ██████████ \n" +
                "  ███    ███                                      ▀▀▀▀▀▀▀▀▀                          ");


        System.out.println("\nMenu du jeu :");
        System.out.println("┌──────────────┐");
        System.out.println("│ 1• Jouer     │");
        System.out.println("│ 2• Règles    │");
        System.out.println("│ 3• Score     │");
        System.out.println("└──────────────┘");

        // Scanner le choix de l'utilisateur
        Scanner scanner = new Scanner(System.in);
        int choix_option = 0;

        // Vérifier que l'entrée est correcte et gérer les erreurs
        if (scanner.hasNextInt()){
            choix_option = scanner.nextInt();
        }
        else {
            choix_option = 4;
        }

        // Dans le cas où l'option n'existe pas
        if ((choix_option != 1) && (choix_option != 2) && (choix_option != 3)&& (choix_option != 4)){

            System.out.println("Tu sais pas lire ou quoi ?? sale batard");
            afficher_menu(joueur, tableau,score);
        }
        else if (choix_option == 1)
        {
            Jeu.initialisation_jeu(tableau,joueur,score);
        }
        // Affiche les règles si elles ont été sélectionées
        else if (choix_option == 2) {

            System.out.println("Pendant son tour, un joueur peut déplacer son pion d’une case (verticalement ou \n" +
                    "horizontalement), puis il détruit une case du plateau. \n" +
                    "Le dernier joueur pouvant encore se déplacer gagne.\n" +
                    "Contraintes :\n" +
                    "- Un joueur ne peut pas détruire une case occupée.\n" +
                    "- Un joueur ne peut pas occuper une case détruite ou une case déjà occupée.\n" +
                    "- Un joueur bloqué pendant un tour est déclaré perdant.");

            Scanner scanner2 = new Scanner(System.in);
            String touche = scanner2.nextLine();
            afficher_menu(joueur, tableau,score);
        }
        else if (choix_option == 3)
        {
            //afficher les scores
            afficher_score(score);
            afficher_menu(joueur, tableau,score);
        }
        else {
            System.out.println("choix séléctionné");
            afficher_menu(joueur, tableau,score);
        }

        // Fermer l'entrée utilisateur
        scanner.close();
    }

    /**
     * Implémentation d'un algorithme de tri des scores
     * @param tableau : le tableau qui contient les infos que l'on veut afficher
     */
    public static void triInsertion(Joueur[] tableau) {
        // On cherche la taille du tableau que la fonction reçoit
        int n = tableau.length;
        // À chaque itération de la boucle, on cherche la valeur d'un élément du tableau,
        // on commence le plus à gauche moins 1, ce dernier élément va pouvoir être trié plus tard
        for (int i = 1; i < n; ++i) {
            Joueur cle = tableau[i]; // On récupère une valeur du tableau
            int j = i - 1; // On récupère la position de l'élément juste à gauche de notre premier élément
            // Tant que l'élément que l'on pointe n'est pas le plus à gauche et que celui ci est plus grand
            while ((j >= 0) && (tableau[j].score > cle.score)) {
                // On modifie l'élément à l'emplacement de notre première variable par la valeur de l'élément à sa droite
                tableau[j + 1] = tableau[j];
                // On modifie la position de notre pointeur un cran plus à gauche
                j = j - 1;
            }
            // On modifie la valeur que nous pointe notre pointeur avec la valeur stocké dans
            // la variable cle et on recommence la boucle de notre trie
            tableau[j + 1] = cle;
        }
    }

    /**
     * Fonction permettant d'afficher les scores des joueurs dans un ordre trié
     * @param joueur : la liste des joueurs que l'on va remplir à l'initialisation de la partie
     */
    public static void afficher_score(ArrayList<Joueur> joueur)
    {
        int taille = joueur.size();
        Joueur [] trie = new Joueur[taille];
        for(int i = 0; i < taille; i++)
        {
            Joueur Affiche = joueur.get(i);
            trie[i] = Affiche;
          
            if (i == 9)
                break;
            //System.out.println(Affiche.pseudo + " : " + Affiche.score);
        }
        triInsertion(trie);
        //affichage ordre croissant
        System.out.println("score ordre croissant: ");
      
        for(int i = 0; i < taille; i++)
        {
            System.out.println(trie[i].pseudo + " : " + trie[i].score);
        }
      
        System.out.println("score ordre decroissant: ");
      
        for(int i = taille - 1; i >= 0; i--)
        {
            System.out.println(trie[i].pseudo + " : " + trie[i].score);
        }
    }
}
