public class Agent {

    private final char lettre;
    private final Environnement e;

    public Agent(char lettre, Environnement e) {
        this.lettre = lettre;
        this.e = e;
    }

    public void run() {
        if (!e.isWellPLaced(this)) {
            pousser();
        }
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
    public String toString() {

        return "Lettre : " + this.lettre;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Agent)) {
            return false;
        }
        Agent c = (Agent) o;

        return c.lettre == this.lettre;
    }


}
