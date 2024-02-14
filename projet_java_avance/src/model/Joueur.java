package model;

import java.util.Scanner;

public class Joueur {

    public String pseudo;
    public int[] position;
    public boolean mort;

    public void bouger(){
        Scanner scanner = new Scanner(System.in);
        int se_deplacer = 0;

        System.out.println("Ou voulez-vous vous déplacer ?");
        System.out.println("\n1•Haut");
        System.out.println("2•Bas");
        System.out.println("3•gauche");
        System.out.println("4•droite");

        if (scanner.hasNextInt()){
            se_deplacer = scanner.nextInt();
        }
        else {
            se_deplacer = 5;
        }

        if ((se_deplacer != 1) && (se_deplacer != 2) && (se_deplacer != 3) && (se_deplacer != 4)){
            System.out.println("c'est pas une direction !!");
            bouger();
        }
        else{
            switch(se_deplacer){

                case 1:
                    System.out.println("Tu t'es déplacé vers le haut !");
                    position[1] -=1;
                    break;


                case 2:
                    System.out.println("Tu t'es déplacé vers le bas !");
                    position[1] +=1;
                    break;

                case 3:
                    System.out.println("Tu t'es déplacé vers le gauche !");
                    position[0] -=1;
                    break;

                case 4:
                    System.out.println("Tu t'es déplacé vers le droite !");
                    position[0] +=1;
                    break;

                default:
                    System.out.println("Choix incorrect");
                    break;
            }

        }

    }

    public void détruire(){

    }

}
