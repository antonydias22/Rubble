package model;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    /**
     * Affiche le menu principal du jeu
     */
    public static void afficher_menu(ArrayList<Joueur> joueur, int[][]tableau){
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
                afficher_menu(joueur, tableau);
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
            afficher_menu(joueur, tableau);
        }
        else if (choix_option != 3)
        {
            //afficher les scores
            afficher_score(joueur);
        }
        else {
            System.out.println("choix séléctionné");
            afficher_menu(joueur, tableau);
        }

        //fermer l'entrée utilisateur
        scanner.close();
    }

    public static void afficher_score(ArrayList<Joueur> joueur)
    {
        int taille = joueur.size();
        for(int i = 0; i < taille; i++)
        {
            Joueur Affiche = joueur.get(i);
            System.out.println(Affiche.pseudo + " : " + Affiche.score);
        }
    }
}
