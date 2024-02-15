package model; // Déclaration du package

import java.util.Scanner; // Importation de la classe Scanner

/**
 * Classe représentant un joueur dans le jeu
 */
public class Joueur {
    public int score = 0;
    public String pseudo; // Pseudo du joueur
    public int[] position; // Position du joueur sur le plateau
    public boolean mort; // Indique si le joueur est mort ou non
    private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Directions possibles : Haut, Bas, Gauche, Droite

    /**
     * Méthode pour déplacer le joueur sur le plateau
     * @param plateau : le plateau de jeu
     * @param aqui : numéro du joueur
     */
    public void bouger(int[][] plateau, int aqui) {
        Scanner scanner = new Scanner(System.in); // Création d'un objet Scanner pour lire l'entrée utilisateur
        int se_deplacer = 0; // Variable pour stocker le choix de déplacement
        boolean deplacementValide = false; // Variable pour vérifier si le déplacement est valide

        // Boucle tant que le déplacement n'est pas valide
        while (!deplacementValide) {
            // Choix de déplacement du joueur
            switch(aqui+1){

                case 1:
                    System.out.println("Où voulez-vous vous déplacer joueur " + pseudo + " : " + "\u001B[31m" + "♥" + "\u001B[0m" + "?");
                    break;

                case 2:
                    System.out.println("Où voulez-vous vous déplacer joueur " + pseudo + " : " + "\u001B[33m" + "♠" + "\u001B[0m" + "?");
                    break;

                case 3:
                    System.out.println("Où voulez-vous vous déplacer joueur " + pseudo + " : " + "\u001B[34m" + "♣" + "\u001B[0m" + "?");
                    break;

                case  4:
                    System.out.println("Où voulez-vous vous déplacer joueur " + pseudo + " : " + "\u001B[32m" + "♦" + "\u001B[0m" + "?");
                    break;

                default:
                    System.out.println("Choix incorrect");
                    break;
            }
            System.out.println("1• Haut\n2• Bas\n3• Gauche\n4• Droite");

            // Vérification si l'entrée utilisateur est un entier
            if (scanner.hasNextInt()) {
                se_deplacer = scanner.nextInt();
                // Vérification si le choix de déplacement est valide
                if (se_deplacer >= 1 && se_deplacer <= 4) {
                    int newY = position[0] + directions[se_deplacer - 1][0];
                    int newX = position[1] + directions[se_deplacer - 1][1];
                    // Vérification si le déplacement est possible
                    if (estDeplacementPossible(plateau, newY, newX)) {
                        plateau[position[0]][position[1]] = 0;
                        position[0] = newY;
                        position[1] = newX;
                        plateau[position[0]][position[1]] = aqui+1;
                        deplacementValide = true;
                        System.out.println("Déplacement effectué !");
                    } else {
                        System.out.println("Tu ne peux pas te déplacer ici !");
                    }
                } else {
                    System.out.println("Ce n'est pas une direction valide !");
                }
            } else {
                System.out.println("Veuillez entrer un nombre valide pour vous déplacer.");
            }
        }
    }

    /**
     * Méthode pour vérifier si le déplacement est possible à une certaine position.
     * @param plateau Le plateau de jeu.
     * @param y Coordonnée Y de la nouvelle position.
     * @param x Coordonnée X de la nouvelle position.
     * @return True si le déplacement est possible, sinon False.
     */
    private boolean estDeplacementPossible(int[][] plateau, int y, int x) {
        // Vérifie si la nouvelle position est dans les limites du plateau et n'est pas un obstacle
        return y >= 0 && y < plateau.length && x >= 0 && x < plateau[0].length && (plateau[y][x] != -1 && plateau[y][x] != 1 && plateau[y][x] != 2 && plateau[y][x] != 3 && plateau[y][x] != 4);
    }

    /**
     * Méthode pour détruire une case sur le plateau
     * @param plateau : le plateau de jeu
     */
    public void detruire(int[][] plateau) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nSélectionnez la case à détruire.");
        System.out.print("Entrez la coordonnée X : ");
        int x = scanner.nextInt();
        System.out.print("Entrez la coordonnée Y : ");
        int y = scanner.nextInt();

        if (y >= 0 && y < plateau.length && x >= 0 && x < plateau[0].length) {
            if (plateau[y][x] == 0) {
                plateau[y][x] = -1; // Remplace la valeur par -1
                System.out.println("La case a été détruite.");
            } else {
                System.out.println("Action impossible : la case est un obstacle, un joueur ou une bordure.");
                detruire(plateau);
            }
        } else {
            System.out.println("Les coordonnées sélectionnées sont hors des limites du plateau.");
            detruire(plateau); // Demande de saisie de nouvelles coordonnées
        }
    }

    /**
     * Méthode pour vérifier si le joueur est bloqué
     * @param plateau : le plateau de jeu
     * @return : true si le joueur est bloqué, sinon False
     */
    public boolean estBloque(int[][] plateau) {
        int y = position[0]; // Position Y actuelle du joueur
        int x = position[1]; // Position X actuelle du joueur


        for (int i = 0; i < directions.length; i++) {
            int directionY = directions[i][0]; // Déplacement en Y (haut ou bas)
            int directionX = directions[i][1]; // Déplacement en X (gauche ou droite)

            int newY = y + directionY;
            int newX = x + directionX;

            // Vérifie si la position est dans les limites du plateau
            if (newY >= 0 && newY < plateau.length && newX >= 0 && newX < plateau[0].length) {
                int caseAdjacente = plateau[newY][newX];
                // Si la case adjacente est libre (0), le joueur n'est pas bloqué
                if (caseAdjacente == 0) {
                    return false;
                }
            } else {
                // Si la nouvelle position est hors des limites, cela signifie que le joueur
                // ne peut pas se déplacer dans cette direction, mais cela ne signifie pas
                // nécessairement qu'il est bloqué. Nous devons vérifier les autres directions
                continue;
            }
        }

        // Si toutes les directions sont bloquées par des obstacles (-1) ou d'autres joueurs (1 à 4),
        // alors le joueur est considéré comme bloqué
        mort = true; // Le joueur est marqué comme mort
        return true; // Le joueur est bloqué
    }
}