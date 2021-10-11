import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Environnement {

    private HashMap<Integer, Stack<Agent>> hashMap = new HashMap<Integer, Stack<Agent>>();

    private Stack<Agent> listeFinale;

    public Environnement() {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Entrer la liste de départ");
        String liste = clavier.next();
        Stack<Agent> stackDepart = new Stack<Agent>();
        for (int i = 0; i < liste.length(); i++) {
            if (i == 0) {
                stackDepart.push(new Agent(liste.charAt(0), '1', this));
            } else {
                stackDepart.push(new Agent(liste.charAt(i), liste.charAt(i - 1), this));
            }
        }
        hashMap.put(0, stackDepart);
        System.out.println("Entrer la liste de finale");
        String liste2 = clavier.next();
        listeFinale = new Stack<>();
        for (int i = 0; i < liste2.length(); i++) {
            if (i == 0) {
                listeFinale.push(new Agent(liste2.charAt(0), '1', this));
            } else {
                listeFinale.push(new Agent(liste2.charAt(i), liste2.charAt(i - 1), this));
            }
        }
        if (stackDepart.size() != listeFinale.size()) {
            System.out.println("Les deux listes ne font pas la même taille.");
        }
    }

    public void run() {
        for (Agent agent : hashMap.get(0)) {
            agent.start();
        }
    }

    public Agent presenceAuDessus(Agent a) {
        for(int i=0;i<3;i++){
            if(hashMap.get(i).contains(a)){

            }
        }
        return true;
    }

    public void pousser(Agent a) {
        int stock;
        Random random = new Random();
        int nb;
        if (colonne1.contains(a)) {
            nb = random.nextInt(2);
            stock = colonne1.indexOf(a);
            colonne1.get(stock + 1).seDeplacer(nb + 1);
        } else if (colonne2.contains((a))) {
            nb = random.nextInt(3);
            while (nb == 1) {
                nb = random.nextInt(3);
                stock = colonne2.indexOf(a);
                colonne2.get(stock + 1).seDeplacer(nb);
            }
        } else if (colonne3.contains((a))) {
            nb = random.nextInt(2);
            stock = colonne3.indexOf(a);
            colonne3.get(stock + 1).seDeplacer(nb);
        }
    }

    public void changerDeColonne(Agent a, int x) {
        if (colonne1.contains(a)) {
            colonne1.removeLast();
            if(x==2){
                colonne2.add(a);
            }else if(x==3){
                colonne3.add(a);
            }
        } else if(colonne2.contains(a)) {
            colonne2.removeLast();
            if (x == 1) {
                colonne1.add(a);
            } else if (x == 3) {
                colonne3.add(a);
            }
        }else if(colonne3.contains(a)) {
            colonne3.removeLast();
            if (x == 1) {
                colonne1.add(a);
            } else if (x == 2) {
                colonne2.add(a);
            }
        }
    }

 */
}
