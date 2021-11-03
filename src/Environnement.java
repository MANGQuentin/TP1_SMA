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
            stackDepart.push(new Agent(liste.charAt(i), this));
        hashMap.put(0, stackDepart);
        System.out.println("Entrer la liste de finale");
        String liste2 = clavier.next();
        listeFinale = new Stack<>();
        for (int i = 0; i < liste2.length(); i++)
            listeFinale.push(new Agent(liste2.charAt(i), this));

        if (stackDepart.size() != listeFinale.size()) {
            System.out.println("Les deux listes ne font pas la même taille.");
        }
        hashMap.put(1, new Stack<>());
        hashMap.put(2, new Stack<>());
    }

    public void newEnvironnement() {
        Collections.shuffle(listeFinale);

        Stack<Agent> newListInitial = (Stack<Agent>) listeFinale.clone();

        Collections.shuffle(newListInitial);
        hashMap.put(0, newListInitial);
        hashMap.put(1, new Stack<>());
        hashMap.put(2, new Stack<>());

    }

    public boolean getIsFinished() {
        for (Stack<Agent> colonne : hashMap.values()) {
            if (colonne.equals(listeFinale))
                return true;

        }
        return false;
    }


    public boolean isWellPLaced(Agent agent) {
        int indexAgentInFinaleList = listeFinale.indexOf(agent);
        char lettreEnDessous;

        if (indexAgentInFinaleList > 0) {
            lettreEnDessous = listeFinale.elementAt(indexAgentInFinaleList - 1).getLettre();
        } else
            lettreEnDessous = '1';

        for (int i = 0; i < 3; i++) {
            Stack<Agent> colonne = hashMap.get(i);
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
        int nbIterations = 0;
        for (int i = 0; i < 1000; i++) {
            while (!getIsFinished()) {
                Random rand = new Random();
                int random = rand.nextInt(3);

                Stack<Agent> colonne = hashMap.get(random);

                if (colonne.size() > 0) {
                    int random2 = rand.nextInt(colonne.size());

                    Agent a = colonne.get(random2);

                    a.run();

                    System.out.println(this);
                    nbIterations++;
                }
            }
            newEnvironnement();
        }
        System.out.print("Nombre moyen d'itérations: ");
        System.out.println(nbIterations / 1000);
    }

    public void seDeplacer(int index, Agent a) {
        hashMap.get(index).push(a);
    }

    public void pousser(Agent a) {

        boolean isFound = false;
        Stack<Agent> colonne;
        int indexAgent = 0;
        int colonneInt = 0;

        do {
            Random rand = new Random();
            colonneInt = rand.nextInt(3);
            colonne = hashMap.get(colonneInt);

            if (colonne.size() > 0) {
                indexAgent = colonne.indexOf(a);
                if (indexAgent > -1)
                    isFound = true;
            }
        }
        while (!isFound);

        if (indexAgent == colonne.size() - 1) {
            Random random = new Random();
            int indexNouvelleColonne = random.nextInt(3);

            while (indexNouvelleColonne == colonneInt) {
                indexNouvelleColonne = random.nextInt(3);
            }
            colonne.pop();
            a.seDeplacer(indexNouvelleColonne);
        } else {
            colonne.get(indexAgent + 1).pousser();
        }
    }


    @Override
    public String toString() {
        StringBuilder toString = new StringBuilder("---------------\n");

        for (int i = 0; i < 3; i++) {
            Stack<Agent> colonne = hashMap.get(i);

            for (Agent a : colonne) {
                toString.append(a.toString()).append(" | ");
            }
            toString.append("\n");
        }

        toString.append("END");

        return toString.toString();
    }
}