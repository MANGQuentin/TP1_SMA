public class Agent extends Thread {

    private char lettre;
    private char enDessous;
    private Environnement e;

    public Agent(char lettre, char enDessous, Environnement e) {
        this.lettre = lettre;
        this.enDessous = enDessous;
        this.e = e;
    }

    @Override   //Multi Threads
    public void run() {

    }

    public char getLettre() {
        return lettre;
    }

    public char getEnDessous() {
        return enDessous;
    }

    /*
    public boolean seDeplacer(int x){
        if(e.lettreEnDessous(this)!=enDessous){
            if(e.presenceAuDessus(this)){
                e.pousser(this);
            }else{

            }
        }
    }

    public boolean pousser(){

    }

 */
}
