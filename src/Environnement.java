import java.util.*;

public class Environnement {

    private final Stack<Agent> listeFinale;
    private HashMap<Integer, Stack<Agent>> hashMap = new HashMap<>();

    public Environnement() {
        Scanner clavier = new Scanner(System.in);
        System.out.println("Entrer la liste de départ");
        String liste = clavier.next();
        Stack<Agent> stackDepart = new Stack<>();
        for (int i = 0; i < liste.length(); i++)
            stackDepart.push(new Agent(liste.charAt(0), this));

        hashMap.put(0, stackDepart);
        System.out.println("Entrer la liste de finale");
        String liste2 = clavier.next();
        listeFinale = new Stack<>();
        for (int i = 0; i < liste2.length(); i++)
            listeFinale.push(new Agent(liste2.charAt(0), this));

        if (stackDepart.size() != listeFinale.size()) {
            System.out.println("Les deux listes ne font pas la même taille.");
        }
    }


    public boolean getIsFinished() {
        for (Stack<Agent> colonne : hashMap.values()) {
            if (colonne == listeFinale) return true;
        }
        return false;
    }


    public boolean isWellPLaced(Agent agent) {
        int indexAgentInFinaleList = listeFinale.indexOf(agent);
        char lettreEnDessous;

        if (indexAgentInFinaleList > 0)
            lettreEnDessous = listeFinale.elementAt(indexAgentInFinaleList - 1).getLettre();
        else
            lettreEnDessous = '1';

        for (Stack<Agent> colonne : hashMap.values()) {
            int indexAgent = colonne.indexOf(agent);
            if (indexAgent > 0) {
                if (colonne.get(indexAgent - 1).getLettre() == lettreEnDessous) {
                    return true;
                }
            } else if (indexAgent == 0) {
                if (lettreEnDessous == '1')
                    return true;
            }
        }
        return false;
    }

    public void run() {
        for (Agent agent : hashMap.get(0)) {
            agent.start();
        }
    }

    public void seDeplacer(int index, Agent a) {
        hashMap.get(index).push(a);
    }

    public void pousser(Agent a) {

        System.out.println("On pousse");

        for (Map.Entry<Integer, Stack<Agent>> entry : hashMap.entrySet()) {
            Integer key = entry.getKey();
            Stack<Agent> colonne = entry.getValue();

            int indexAgent = colonne.indexOf(a);
            if (indexAgent > -1) {
                if (indexAgent == colonne.size()) {
                    Random random = new Random();
                    int indexNouvelleColonne = random.nextInt(3);

                    while (indexNouvelleColonne == key) {
                        indexNouvelleColonne = random.nextInt(3);
                    }
                    colonne.pop();
                    a.seDeplacer(indexNouvelleColonne);
                } else {
                    colonne.get(indexAgent + 1).pousser();
                }
            }
        }
    }
}
