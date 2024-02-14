package model;

import java.util.Scanner;

public class Joueur {

    public String pseudo;
    public int[] position;
    public boolean mort;

    public void bouger() {
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
        int newX = position[0], newY = position[1];

        switch (se_deplacer) {
            case 1:
                newY -= 1;
                break;
            case 2:
                newY += 1;
                break;
            case 3:
                newX -= 1;
                break;
            case 4:
                newX += 1;
                break;
            default:
                System.out.println("Ce n'est pas une direction valide !");
                return;
        }

        if (estBloque(newX, newY)) {
            System.out.println("Cette direction est bloquée !");
        } else {
            position[0] = newX;
            position[1] = newY;
            System.out.println("Déplacement effectué !");
        }
    }

    public void detruire(){

    }

    public boolean estBloque(int x, int y){

    return false;

    }

}
