package model;

import java.util.Scanner;

public class Joueur {

    public String pseudo;
    public int[] position;
    public boolean mort;

    public void bouger(int[][] plateau) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Où voulez-vous vous déplacer ?");
        System.out.println("1• Haut\n2• Bas\n3• Gauche\n4• Droite");

        int se_deplacer = 0;

        if (scanner.hasNextInt()) {
            se_deplacer = scanner.nextInt();
        } else {
            System.out.println("Veuillez entrer un nombre valide pour vous déplacer.");
            scanner.next();
            return;
        }
        int newX = position[1], newY = position[0];

        switch (se_deplacer) {
            case 1:
                if((plateau[newY-1][newX] == -1) || (plateau[newY-1][newX] == 1) || (plateau[newY-1][newX] == 2)
                || (plateau[newY-1][newX] == 3) || (plateau[newY-1][newX] == 4)){
                    System.out.println("Tu ne peux pas te déplacer ici !");
                    bouger(plateau);
                }
                else{
                    newY -= 1;
                }
                break;
            case 2:
                if((plateau[newY+1][newX] == -1) || (plateau[newY+1][newX] == 1) || (plateau[newY+1][newX] == 2)
                        || (plateau[newY+1][newX] == 3) || (plateau[newY+1][newX] == 4)){
                    System.out.println("Tu ne peux pas te déplacer ici !");
                    bouger(plateau);
                }
                else {
                    newY += 1;
                }
                break;
            case 3:
                if((plateau[newY][newX-1] == -1) || (plateau[newY][newX-1] == 1) || (plateau[newY][newX-1] == 2)
                        || (plateau[newY][newX-1] == 3) || (plateau[newY][newX-1] == 4)){
                    System.out.println("Tu ne peux pas te déplacer ici !");
                    bouger(plateau);
                }
                else {
                    newX -= 1;
                }
                break;
            case 4:
                if((plateau[newY][newX+1] == -1) || (plateau[newY][newX+1] == 1) || (plateau[newY][newX+1] == 2)
                        || (plateau[newY][newX+1] == 3) || (plateau[newY][newX+1] == 4)){
                    System.out.println("Tu ne peux pas te déplacer ici !");
                    bouger(plateau);
                }
                else {
                    newX += 1;
                }
                break;
            default:
                System.out.println("Ce n'est pas une direction valide !");
                return;
        }

            position[0] = newX;
            position[1] = newY;
            System.out.println("Déplacement effectué !");

    }

    public void detruire(){

    }

    public boolean estBloque(int x, int y){

        
    }

}
