public class Agent extends Thread {

    private final char lettre;
    private final Environnement e;

    public Agent(char lettre, Environnement e) {
        this.lettre = lettre;
        this.e = e;
    }

    @Override   //Multi Threads
    public void run() {
        System.out.println("On demarre l'agent " + this.lettre);
        do {
            if (!e.isWellPLaced(this)) {
                pousser();
            }
        } while (!e.getIsFinished());
    }

    public char getLettre() {
        return lettre;
    }


    public void seDeplacer(int x) {
        e.seDeplacer(x, this);
    }

    public void pousser() {
        e.pousser(this);
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Agent)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Agent c = (Agent) o;

        // Compare the data members and return accordingly
        return c.lettre == this.lettre;
    }


}
