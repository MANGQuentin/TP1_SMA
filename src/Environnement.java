import java.util.*;

public class Environnement {

    private Stack<Agent> listeFinale;
    private HashMap<Integer, Stack<Agent>> hashMap = new HashMap<>();


    public void newEnvironnement() {
        Collections.shuffle(listeFinale);

        Stack<Agent> newListInitial = (Stack<Agent>) listeFinale.clone();

        Collections.shuffle(newListInitial);
        hashMap.put(0, newListInitial);
        hashMap.put(1, new Stack<>());
        hashMap.put(2, new Stack<>());

    }

    public void newEnvironnement2() {
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

    public void run(int nombreIteration) {

        Scanner clavier = new Scanner(System.in);
        System.out.println("Entrer la liste de départ");
        String liste = clavier.next();
        Stack<Agent> stackDepart = new Stack<>();
        for (int i = 0; i < liste.length(); i++) {
            Agent2 agent2 = new Agent2(liste.charAt(i), this);
            agent2.setPriority(3);
            stackDepart.push(agent2);
        }
        hashMap.put(0, stackDepart);
        System.out.println("Entrer la liste de finale");
        String liste2 = clavier.next();
        listeFinale = new Stack<>();
        for (int i = 0; i < liste2.length(); i++) {
            Agent agent2 = new Agent1(liste2.charAt(i), this);
            listeFinale.push(agent2);
        }

        if (stackDepart.size() != listeFinale.size()) {
            System.out.println("Les deux listes ne font pas la même taille.");
        }
        hashMap.put(1, new Stack<>());
        hashMap.put(2, new Stack<>());


        int nbIterations = 0;
        for (int i = 0; i < nombreIteration; i++) {
            while (!getIsFinished()) {
                Random rand = new Random();
                int random = rand.nextInt(3);

                Stack<Agent> colonne = hashMap.get(random);

                if (colonne.size() > 0) {
                    int random2 = rand.nextInt(colonne.size());

                    Agent a = colonne.get(random2);

                    a.action();

                    System.out.println(this);
                    nbIterations++;
                }
            }
            newEnvironnement();
        }
        System.out.print("Nombre moyen d'itérations: ");
        System.out.println(nbIterations / nombreIteration);
    }

    public int bestMoveColonne(Agent2 agent) {
        int indexAgentInFinaleList = listeFinale.indexOf(agent);
        char lettreEnDessous;
        if (indexAgentInFinaleList > 0) {
            lettreEnDessous = listeFinale.elementAt(indexAgentInFinaleList - 1).getLettre();
        } else
            return -1;

        int i = 0;

        while (i < 3) {
            Stack<Agent> colonne = hashMap.get(i);

            for (Agent a : colonne) {
                if (a.getLettre() == lettreEnDessous) {
                    return i;
                }
            }
            i++;
        }

        return -1;
    }

    public void run2(int nombreIteration) {

        Scanner clavier = new Scanner(System.in);
        System.out.println("Entrer la liste de départ");
        String liste = clavier.next();
        Stack<Agent> stackDepart = new Stack<>();
        for (int i = 0; i < liste.length(); i++) {
            Agent2 agent2 = new Agent2(liste.charAt(i), this);
            agent2.setPriority(3);
            stackDepart.push(agent2);
        }
        hashMap.put(0, stackDepart);
        System.out.println("Entrer la liste de finale");
        String liste2 = clavier.next();
        listeFinale = new Stack<>();
        for (int i = 0; i < liste2.length(); i++) {
            Agent2 agent2 = new Agent2(liste2.charAt(i), this);
            agent2.setPriority(3);
            listeFinale.push(agent2);
        }

        if (stackDepart.size() != listeFinale.size()) {
            System.out.println("Les deux listes ne font pas la même taille.");
        }
        hashMap.put(1, new Stack<>());
        hashMap.put(2, new Stack<>());


        System.out.println(this);
        int nbIterations = 0;
        for (int i = 0; i < nombreIteration; i++) {
            while (!this.getIsFinished()) {
                Agent2 agentMaxPriority = null;
                for (Stack<Agent> h : hashMap.values()) {

                    if (h.size() != 0) {
                        Agent2 a = (Agent2) Collections.max(h, Comparator.comparing(s -> ((Agent2) s).getPriority()));

                        if (agentMaxPriority != null) {
                            if (a.getPriority() > agentMaxPriority.getPriority())
                                agentMaxPriority = a;
                        } else {
                            agentMaxPriority = a;
                        }
                    }

                }
                System.out.println(agentMaxPriority);

                assert agentMaxPriority != null;
                agentMaxPriority.action();

                System.out.println(this);
                nbIterations++;
                newEnvironnement2();

            }
        }

        System.out.print("Nombre moyen d'itérations: ");
        System.out.println(nbIterations / nombreIteration);
    }

    public void seDeplacer(int index, Agent a) {

        int indexAgent;
        int colonneInt;
        boolean isFound = false;
        Stack<Agent> colonne;
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

        colonne.pop();


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
            a.seDeplacer(indexNouvelleColonne);
        } else {
            colonne.get(indexAgent + 1).pousser();
        }
    }

    public boolean isLast(Agent2 agent) {
        for (int i = 0; i < 3; i++) {
            Stack<Agent> colonne = hashMap.get(i);
            int indexAgent = colonne.indexOf(agent);
            if (indexAgent > 0) {
                return indexAgent + 1 == colonne.size();
            }
        }
        return false;
    }


    public boolean isWellPLacedAbove(Agent a) {
        for (int i = 0; i < 3; i++) {
            Stack<Agent> colonne = hashMap.get(i);
            int indexAgent = colonne.indexOf(a);
            if (indexAgent > 0) {
                if (indexAgent + 1 == colonne.size()) {
                    return true;
                } else {
                    if (!this.isWellPLaced(colonne.get(indexAgent + 1))) {
                        return false;
                    }
                }
            }
        }
        return false;
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