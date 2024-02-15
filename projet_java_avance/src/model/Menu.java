package model;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    /**
     * Affiche le menu principal du jeu
     */
    public static void afficher_menu(ArrayList<Joueur> joueur, int[][]tableau,ArrayList<Joueur>score){
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
                "  ███    ███                                      ▀                      ");

        System.out.println("\n1•Jouer ");
        System.out.println("2•Règle");
        System.out.println("3•Score");
        System.out.println("3•Fermer");

        //Scanner le choix de l'utilisateur
        Scanner scanner = new Scanner(System.in);
        int choix_option = 0;

        //vérifier que l'entrée est correcte et gérer les erreurs
        if (scanner.hasNextInt()){
            choix_option = scanner.nextInt();
        }
        else {
            choix_option = 4;
        }

        //dans le cas ou l'option n'existe pas
        if ((choix_option != 1) && (choix_option != 2) && (choix_option != 3)&& (choix_option != 4)){

            System.out.println("Tu sais pas lire ou quoi ?? sale batard");
            afficher_menu(joueur, tableau,score);
        }
        else if (choix_option == 1)
        {
            Jeu.initialisation_jeu(tableau,joueur,score);
        }
        //affiche les règles si sélectioné
        else if (choix_option == 2) {

            System.out.println("Pendant son tour un joueur peut déplacer son pion d’une case (verticalement ou \n" +
                    "horizontalement), puis il détruit une case du plateau. \n" +
                    "Le dernier joueur pouvant encore se déplacer gagne.\n" +
                    "Contraintes :\n" +
                    "- Un joueur ne peut pas détruire une case occupée.\n" +
                    "- Un joueur ne peut pas occuper une case détruite ou une case déjà occupée.\n" +
                    "- Un joueur bloqué pendant un tour est déclaré perdant");

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

        //fermer l'entrée utilisateur
        scanner.close();
    }
    public static void triInsertion(Joueur[] tableau) {
        //on cherche la taille du tableau que la fonction reçoit
        int n = tableau.length;
        // à chaque itération de la boucle, on cherche la valeur d'un élément du tableau, on commence le plus à gauche moins 1, ce dernier élément va pouvoir être trié plus tard
        for (int i = 1; i < n; ++i) {
            Joueur cle = tableau[i]; // on récupère une valeur du tableau
            int j = i - 1; // On récupère la position de l'élément juste à gauche de notre premier élément
            // tant que l'élément que l'on pointe n'est pas le plus à gauche et que celui ci est plus grand
            while ((j >= 0) && (tableau[j].score > cle.score)) {
                //on modifie l'élement à l'emplacement de notre première variable par la valeur de l'élément à sa droite
                tableau[j + 1] = tableau[j];
                //on modifie la position de notre pointeur un cran plus à gauche
                j = j - 1;
            }
            //on modifie la valeur que nous pointe notre pointeur avec la valeur stocké dans la variable cle et on recommence la boucle de notre trie
            tableau[j + 1] = cle;
        }
    }

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
