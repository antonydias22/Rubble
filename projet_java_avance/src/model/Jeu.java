package model;

public class Jeu {
    public static void afficherTableau(int[][] tableau, int axe_x, int axe_y, int compte_x, int compte_y) {

        if ((compte_x == 1) && (compte_y == 1))
        {
            System.out.print(" |");
        }

        if ( compte_x != axe_x - 1)
        {
            System.out.print(tableau[compte_y][compte_x] + "|" );
            afficherTableau(tableau, axe_x,axe_y,compte_x +1 ,compte_y);
        }

        if ((  compte_x == axe_x - 1 ) && (compte_y != axe_y - 2))
        {
            System.out.print("\n |");
            afficherTableau(tableau, axe_x,axe_y,1 , compte_y +1);
        }
    }

    public static void init_tableau(int[][] tableau, int compte_x, int compte_y)
    {
        if (((compte_y == 0)||(compte_y == 3)||(compte_x == 0)||(compte_x == 4)))
        {
            tableau[compte_y][compte_x] = -1;
            if (compte_x<4)
            {
                init_tableau(tableau,compte_x+1,compte_y);
            }

            else if (compte_y < 3)
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
}
