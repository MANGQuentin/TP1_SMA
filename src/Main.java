import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Environnement e = new Environnement();


        System.out.println("Veuillez choisir quelle partie du TP lancer (1 - 2)");

        Scanner clavier = new Scanner(System.in);
        int choix = clavier.nextInt();
        System.out.println("Veuillez choisir le nombre d'itération");

        int nbIteration = clavier.nextInt();

        if (choix == 1)
            e.run(nbIteration);
        else
            e.run2(nbIteration);
    }
}