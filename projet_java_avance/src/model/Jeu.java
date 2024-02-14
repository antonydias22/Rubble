package model;

import java.util.ArrayList;
import java.util.Random;

public class Jeu {

    /**
     * Fonction permettant d'afficher le plateau de jeu lorsque l'on lui demande, elle va alors afficher toutes les
     * valeurs d'un tableau à l'execption des extrémité
     * @param tableau : le tableau qui contient les infos que l'on veut afficher
     * @param axe_x : taille du plateau en hauteur
     * @param axe_y : taille du plateau en largeur
     * @param compte_x : pointeur selon l'axe horizontale qui va nous permettre de nous repérer sur le plateau
     * @param compte_y : pointeur selon l'axe verticale qui va nous permettre de nous repérer sur le plateau
     */
    public static void afficherTableau(int[][] tableau, int axe_x, int axe_y, int compte_x, int compte_y) {

        if ((compte_x == 1) && (compte_y == 1))
        {
            System.out.print(" |");
        }

        if ( compte_x != axe_x -1)
        {
            System.out.print(tableau[compte_y][compte_x] + "|" );
            afficherTableau(tableau, axe_x,axe_y,compte_x +1 ,compte_y);
        }

        if ((  compte_x == axe_x - 1 ) && (compte_y != axe_y - 3))
        {
            System.out.print("\n |");
            afficherTableau(tableau, axe_x,axe_y,1 , compte_y +1);
        }
    }

    /**
     *
     * @param tableau: le tableau qui contient les infos que l'on veut afficher
     * @param compte_x: pointeur selon l'axe horizontale qui va nous permettre de nous repérer sur le plateau
     * @param compte_y: pointeur selon l'axe verticale qui va nous permettre de nous repérer sur le plateau
     */
    public static void init_tableau(int[][] tableau, int compte_x, int compte_y)
    {
        if (((compte_y == 0)||(compte_y ==11)||(compte_x == 0)||(compte_x == 12)))
        {

            tableau[compte_y][compte_x] = -1;
            if (compte_x<12)
            {
                init_tableau(tableau,compte_x+1,compte_y);
            }

            else if (compte_y < 11)
            {
                init_tableau(tableau,0,compte_y+1);
            }
        }
        else
        {
            tableau[compte_y][compte_x] = 0;
            init_tableau(tableau,compte_x+1,compte_y);
        }
    }

    /**
     * Cette fonction permet d'initialiser la partie en donnant un nom au joueur, préparant le plateau et donnant le
     * joueur qui va commencer la partie
     * @param tableau: le tableau qui contient les infos que l'on veut afficher
     * @param joueur: la liste des joueurs que l'on va remplir à l'initialisation de la partie
     * @return on retourne le joueur qui va commencer pour lancer notre boucle de jeu
     */
    public static void initialisation_jeu(int[][]tableau, ArrayList<Joueur> joueur)
    {
        Jeu.init_tableau(tableau,1,1);
        //demander nombre de joueur
        int nb_joueur = 2;
        //création des joueurs dans la liste joueur
        for(int i = 0;i < nb_joueur;i++) {
            Joueur objet = new Joueur();
            objet.mort = false;
            // en fonction du joueur, on va leur donner une position de départ différentes
            if (i == 0)
            {
                objet.position = new int[2];
                objet.position[0] = 5;
                objet.position[1] = 6;
                tableau[objet.position[0]][objet.position[1]] = 1;
            }
            else if(i == 1)
            {
                objet.position = new int[2];
                objet.position[0] = 6;
                objet.position[1] = 5;
                tableau[objet.position[0]][objet.position[1]] = 2;
            }
            //on donne un pseudo différent de chaque joueur
            objet.pseudo = "bloop" + i;
            joueur.add(objet);
        }

        //remplissage variable quijoue avec un nombre aléatoire
        int aqui;
        Random r = new Random();
        aqui = r.nextInt(nb_joueur);
        //initialisation du plateau de jeu

        tour_de_jeu(tableau,joueur,aqui);
    }
    public static void tour_de_jeu(int[][]tableau, ArrayList<Joueur> joueur, int aqui)
    {
        //compter nb_joueur
        int nb_joueur = joueur.size();
        boolean fin = false;
        //tant que la partie n'est pas finie
        while (fin == false)
        {
            int nb_bloque = 0;
            //affichage plateau de jeu
            afficherTableau(tableau,13,12,1,1);
            System.out.println("\n");
            //appel de bouger dans joueur
            Joueur joueur_actuel = joueur.get(aqui);
            if (joueur_actuel.mort == false) {
                joueur_actuel.bouger(tableau,aqui);

                afficherTableau(tableau,13,12,1,1);

                //appel de détruire dans joueur
                joueur_actuel.detruire(tableau);
                //vérification bloquer / victoire
                for (int i = 0; i < nb_joueur; i++) {
                    Joueur j_tempo = joueur.get(i);
                    j_tempo.estBloque(tableau);
                    if (j_tempo.mort == true)
                        nb_bloque++;
                }
                if (nb_bloque >= nb_joueur - 1)
                    fin = true;
            }
            //changement de joueur
            aqui++;
            if (aqui == nb_joueur)
                aqui = 0;
        }
    }
}
