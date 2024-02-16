import model.*;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args) {
        ArrayList<Joueur> liste = new ArrayList<Joueur>();
        ArrayList<Joueur> score = new ArrayList<Joueur>();
        int[][] tab = new int [12][13];
        Menu.afficher_menu(liste,tab,score);
    }
}