package model;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Menu {

    public static void afficher_menu(){

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
        System.out.println("3•Fermer");

        Scanner scanner = new Scanner(System.in);
        int choix_option = 0;

        if (scanner.hasNextInt()){
            choix_option = scanner.nextInt();
        }
        else {
            choix_option = 4;
        }

        if ((choix_option != 1) && (choix_option != 2) && (choix_option != 3)){

            System.out.println("Tu sais pas lire ou quoi ?? sale batard");


                afficher_menu();
        }
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
            afficher_menu();



        }
        else {
            System.out.println("choix séléctionné");
            afficher_menu();
        }

        scanner.close();

    }
}
