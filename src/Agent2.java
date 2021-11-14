public class Agent2 extends Agent {

    int priority;


    Agent2(char lettre, Environnement e) {
        super(lettre, e);
    }

    @Override
    public void pousser() {
        this.setPriority(3);
        e.pousser(this);
    }


    public int communication1() {
        return e.bestMoveColonne(this);
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public void action() {
        if (!e.isWellPLaced(this)) {
            int nextColonne = communication1();

            if (e.isLast(this) && nextColonne != -1)
                seDeplacer(nextColonne);
            else
                pousser();
        } else
            setPriority(0);
    }


    @Override
    public String toString() {

        return "Lettre : " + this.getLettre() + " (" + getPriority() + ")";
    }

}
