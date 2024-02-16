package model; // Déclaration du package

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList; // Importation de la classe ArrayList
import java.util.Random; // Importation de la classe Random
import java.util.Scanner; // Importation de la classe Scanner

public class Jeu {
    /**
     * Fonction permettant d'afficher le plateau de jeu lorsque l'on lui demande, elle va alors afficher toutes les
     * valeurs d'un tableau à l'execption des extrémitées
     * @param tableau : le tableau qui contient les infos que l'on veut afficher
     * @param axe_x : taille du plateau en hauteur
     * @param axe_y : taille du plateau en largeur
     * @param compte_x : pointeur selon l'axe horizontale qui va nous permettre de nous repérer sur le plateau
     * @param compte_y : pointeur selon l'axe verticale qui va nous permettre de nous repérer sur le plateau
     */
    public static void afficherTableau(int[][] tableau, int axe_x, int axe_y, int compte_x, int compte_y) {

        // Affichage de la barre verticale à la première cellule
        if ((compte_x == 1) && (compte_y == 1))
        {
            System.out.print("  |1|2|3|4|5|6|7|8|9|0|1|\n");
        }
        if (compte_x == 1)
            System.out.print((compte_y %10) + " |");
        if ( compte_x != axe_x -1)
          
        // Vérifie si ce n'est pas la dernière colonne
        if (compte_x != axe_x -1)
        {
            // Affiche le contenu de la cellule en fonction de sa valeur
            if (tableau[compte_y][compte_x] == 0)
                System.out.print(" |");
            else if (tableau[compte_y][compte_x] == -1)
                System.out.print("■|");
            else if (tableau[compte_y][compte_x] == 1)
                System.out.print("\u001B[31m" + "♥" + "\u001B[0m" + "|");
            else if (tableau[compte_y][compte_x] == 2)
                System.out.print("\u001B[33m" + "♠" + "\u001B[0m"+ "|");
            else if (tableau[compte_y][compte_x] == 3)
                System.out.print("\u001B[34m" + "♣" + "\u001B[0m"+ "|");
            else if (tableau[compte_y][compte_x] == 4)
                System.out.print("\u001B[32m" + "♦" + "\u001B[0m"+ "|");

            // Appel la fonction récursivement pour afficher la cellule suivante dans la même ligne
            afficherTableau(tableau, axe_x,axe_y,compte_x +1 ,compte_y);
        }

        // Vérifie si c'est la dernière colonne mais pas la dernière ligne
        if ((compte_x == axe_x - 1) && (compte_y != axe_y - 2))
        {
            // Passe à la ligne et affiche le début d'une nouvelle ligne
            System.out.print("\n");
            // Appelle la fonction récursivement pour afficher la première cellule de la nouvelle ligne
            afficherTableau(tableau, axe_x, axe_y,1 , compte_y +1);
        }
    }

    /**
     * Fonction permettant d'initialiser le tableau au lancement de la partie
     * @param tableau : le tableau qui contient les infos que l'on veut afficher
     * @param compte_x : pointeur selon l'axe horizontale qui va nous permettre de nous repérer sur le plateau
     * @param compte_y : pointeur selon l'axe verticale qui va nous permettre de nous repérer sur le plateau
     */
    public static void init_tableau(int[][] tableau, int compte_x, int compte_y)
    {
        // Vérifie si la position actuelle se trouve aux bords du tableau
        if (((compte_y == 0)||(compte_y == 11)||(compte_x == 0)||(compte_x == 12)))
        {
            // Si elle se trouve sur le bord, on défini la valeur à cette position est défini à -1
            tableau[compte_y][compte_x] = -1;

            // Fait un déplacement à la colonne suivante (si existante.s) selon la position actuelle
            if (compte_x < 12)
            {
                init_tableau(tableau,compte_x+1,compte_y);
            }

            // Si la position actuelle est sur le bord, le déplacement est effectué
            // à la première colonne de la ligne suivante
            else if (compte_y < 11)
            {
                init_tableau(tableau,0,compte_y+1);
            }
        }

        // Si la position actuelle n'est pas sur le bord,
        // on lui assigne la valeur 0 et on la déplace à la colonne suivante
        else
        {
            tableau[compte_y][compte_x] = 0;
            init_tableau(tableau,compte_x+1,compte_y);
        }
    }

    /**
     * Cette fonction permet d'initialiser la partie en donnant un nom au joueur, préparant le plateau et donnant le
     * joueur qui va commencer la partie
     * @param tableau : le tableau qui contient les infos que l'on veut afficher
     * @param joueur : la liste des joueurs que l'on va remplir à l'initialisation de la partie
     * @return : on retourne le joueur qui va commencer pour lancer notre boucle de jeu
     */
    public static void initialisation_jeu(int[][]tableau, ArrayList<Joueur> joueur, ArrayList<Joueur> score) throws IOException, NoSuchAlgorithmException {
        Jeu.init_tableau(tableau,1,1);
        // Demande le nombre de joueur
        System.out.println("Nombre de joueur(s) : ");
        Scanner scanner = new Scanner(System.in);
        int nb_joueur = 0;

        // Vérifie que l'entrée est correcte et gère les erreurs
        if (scanner.hasNextInt() ){
            nb_joueur = scanner.nextInt();
            if(nb_joueur <= 4 && nb_joueur > 0)
            {

            }
            else{
                System.out.println("Nombre de joueur Incorrect");
                initialisation_jeu(tableau,joueur,score);
            }
        }
        else {
            System.out.println("Nombre de joueur Incorrect");
            initialisation_jeu(tableau,joueur,score);
        }

        // Création des joueurs dans la liste joueur
        for (int i = 0; i < nb_joueur; i++) {
            Joueur objet = new Joueur();
            objet.mort = false;
            // En fonction du joueur, on va leur donner une position de départ différente
            if (i == 0)
            {
                objet.position = new int[2];
                objet.position[0] = 5;
                objet.position[1] = 6;
                tableau[objet.position[0]][objet.position[1]] = 1;
            }
            else if (i == 1)
            {
                objet.position = new int[2];
                objet.position[0] = 6;
                objet.position[1] = 5;
                tableau[objet.position[0]][objet.position[1]] = 2;
            } else if (i == 2)
            {
                objet.position = new int[2];
                objet.position[0] = 5;
                objet.position[1] = 5;
                tableau[objet.position[0]][objet.position[1]] = 3;
            } else if (i == 3)
            {
                objet.position = new int[2];
                objet.position[0] = 6;
                objet.position[1] = 6;
                tableau[objet.position[0]][objet.position[1]] = 4;
            }

            // On donne un pseudo différent à chaque joueur
            boolean pseudoValide = false;
            Scanner scanner1 = new Scanner(System.in);
            while (!pseudoValide) {
                System.out.println("Veuillez saisir le pseudonyme pour le joueur " + (i + 1) + " (2 à 10 caractères) :");
                String pseudonyme = scanner1.nextLine(); // Utiliser le même scanner pour éviter des conflits

                if (pseudonyme.length() >= 2 && pseudonyme.length() <= 10) {
                    objet.pseudo = pseudonyme;
                    pseudoValide = true;
                } else {
                    System.out.println("Pseudo Incorrect : votre pseudo doit être compris entre 2 et 10 caractères. Merci de resaisir un pseudo !");
                }
            }
            joueur.add(objet);
        }

        // Remplissage variable qui joue avec un nombre aléatoire
        int aqui;
        Random r = new Random();
        aqui = r.nextInt(nb_joueur);
      
        tour_de_jeu(tableau,joueur,aqui,score);
    }
  
    public static void tour_de_jeu(int[][]tableau, ArrayList<Joueur> joueur, int aqui, ArrayList<Joueur> listescore) throws IOException, NoSuchAlgorithmException {
        // Compte le nombre de joueur
        int nb_joueur = joueur.size();
        boolean fin = false;

        // La boucle s'exécute tant que la partie n'est pas finie
        while (fin == false)
        {
            int nb_bloque = 0;

            // Affichage plateau de jeu
            afficherTableau(tableau,13,12,1,1);
            System.out.println("\n");

            // Appel de la méthode bouger dans joueur dans le cas où le joueur n'est pas mort
            Joueur joueur_actuel = joueur.get(aqui);
            if (joueur_actuel.mort == false) {
                joueur_actuel.bouger(tableau,aqui);
                afficherTableau(tableau,13,12,1,1);

                // Appel de la méthode détruire dans joueur
                joueur_actuel.detruire(tableau);

                // Vérification bloquer / victoire
                for (int i = 0; i < nb_joueur; i++) {
                    Joueur j_tempo = joueur.get(i);
                    j_tempo.estBloque(tableau);
                    if (j_tempo.mort == true)
                        nb_bloque++;
                }
                if (nb_bloque >= nb_joueur - 1)
                    fin = true;
            }

            // Changement de joueur
            aqui++;
            if (aqui == nb_joueur)
                aqui = 0;
        }
      
        //lorsque la partie est fini, on attribut les scores
        //pour chacun des joueurs de la liste, on leur donne le bon nombre de point en foction de s'il est en vie ou non
        boolean test_exist = false;
        for (int i = 0; i < nb_joueur; i++ )
        {
            Joueur test = joueur.get(i);
            for (Joueur score : listescore) {
                if (score.pseudo.equals(test.pseudo))
                {
                    System.out.print("joueur existant");
                    boolean test2 = test.mort;
                    if (!test2){
                        score.score -= 2;
                        System.out.println("Le joueur : " + test.pseudo + " à perdu");
                    }

                    else {
                        score.score += 5;
                        System.out.println("Le joueur : " + test.pseudo + " à gagné");
                    }

                    test_exist = true;

                }
            }
            if(test_exist == false)
            {
                Joueur score = new Joueur();
                boolean test2 = test.mort;
                if (!test2) {
                    score.score = -2;
                    score.pseudo = test.pseudo;
                    System.out.println("Le joueur : " + test.pseudo + " à perdu son  nom a été enregistrer");
                }
                else {
                    score.score = 5;
                    score.pseudo = test.pseudo;
                    System.out.println("Le joueur : " + test.pseudo + " à gagné son  nom a été enregistrer");
                }
              
                listescore.add(score);
            }
            test_exist = false;

        }
        Sauvegarder.sauvegarderScores("C:\\Users\\anton\\OneDrive\\Bureau\\coding\\Mes_projets\\destruct chess\\Rubble\\projet_java_avance\\src\\control\\score.txt",joueur);
        joueur.clear();
      
        // Retour au menu principal
        Scanner scanner3 = new Scanner(System.in);
        String touche = scanner3.nextLine();
        Menu.afficher_menu(joueur, tableau,listescore);
    }
}