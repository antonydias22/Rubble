package model;

import java.util.Scanner;

public class Joueur {

    public String pseudo;
    public int[] position;
    public boolean mort;
    private final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Haut, Bas, Gauche, Droite

    public void bouger(int[][] plateau) {
        Scanner scanner = new Scanner(System.in);
        int se_deplacer = 0;
        boolean deplacementValide = false;

        while (!deplacementValide) {
            System.out.println("Où voulez-vous vous déplacer ?");
            System.out.println("1• Haut\n2• Bas\n3• Gauche\n4• Droite");

            if (scanner.hasNextInt()) {
                se_deplacer = scanner.nextInt();
                if (se_deplacer >= 1 && se_deplacer <= 4) {
                    int newY = position[0] + directions[se_deplacer - 1][0];
                    int newX = position[1] + directions[se_deplacer - 1][1];
                    if (estDeplacementPossible(plateau, newY, newX)) {
                        position[0] = newY;
                        position[1] = newX;
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

    private boolean estDeplacementPossible(int[][] plateau, int y, int x) {
        // Vérifie si la nouvelle position est dans les limites du plateau et n'est pas un obstacle
        return y >= 0 && y < plateau.length && x >= 0 && x < plateau[0].length && (plateau[y][x] != -1 && plateau[y][x] != 1 && plateau[y][x] != 2 && plateau[y][x] != 3 && plateau[y][x] != 4);
    }


    public void detruire(int[][] plateau) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Sélectionnez la case à détruire.");
        System.out.print("Entrez la coordonnée X : ");
        int x = scanner.nextInt();
        System.out.print("Entrez la coordonnée Y : ");
        int y = scanner.nextInt();

        if (y >= 0 && y < plateau.length && x >= 0 && x < plateau[0].length) {
            if (plateau[y][x] == 0) {
                plateau[y][x] = -1;
                System.out.println("La case a été détruite.");
            } else {
                System.out.println("Action impossible : la case est un obstacle, un joueur ou une bordure.");
            }
        } else {
            System.out.println("Les coordonnées sélectionnées sont hors des limites du plateau.");
            detruire(plateau);
        }
    }

    public boolean estBloque(int[][] plateau) {
        int y = position[0]; // Position Y actuelle du joueur
        int x = position[1]; // Position X actuelle du joueur


        for (int i = 0; i < directions.length; i++) {
            int directionY = directions[i][0]; // Deplacement en Y (haut ou bas)
            int directionX = directions[i][1]; // Deplacement en X (gauche ou droite)

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
                // nécessairement qu'il est bloqué. Nous devons vérifier les autres directions.
                continue;
            }
        }

        // Si toutes les directions sont bloquées par des obstacles (-1) ou d'autres joueurs (1 à 4),
        // alors le joueur est considéré comme bloqué.
        mort = true;
        return true;
    }


}
