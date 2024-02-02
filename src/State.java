public class State{
    int r ; 
    int g ; 
    int b ; 
    String state;
    boolean byDefault ; 


    /** 
     * Constructeur
     * @param state libellé de l'état à créer
     * @param valeur de R de la couleur représentant l'état
     * @param valeur de G de la couleur représentant l'état
     * @param valeur de B de la couleur représentant l'état
     * @param cet état est-il à définir par défaut?
     */
    public State(String state, int r, int g, int b, boolean byDefault) {
        this.state = state;
        this.r=r; 
        this.g=g;
        this.b=b;
        this.byDefault = byDefault;
    }
    
    /** 
     * ToString 
     * @return String une chaine de caractères représentant l'état avec libellé
     */
    public String toString() {
        return state ;
     }
    

    /**
     * Getter du libellé de l'état 
     * @return String le libellé de l'état
     */
    public String getState() {
    	return state;
    }
    

    /**
     * Getter de la valeur de R de la couleur représentant l'état
     * @return int la valeur de R de la couleur représentant l'état
     */
    public int getR() {
    	return r;
    }
    
    /**
     * Getter de la valeur de G de la couleur représentant l'état
     * @return int la valeur de G de la couleur représentant l'état
     */
    public int getG() {
    	return g;
    }
    
    /**
     * Getter de la valeur de B de la couleur représentant l'état
     * @return int la valeur de B de la couleur représentant l'état
     */
    public int getB() {
    	return b;
    }
    
    /**
     * Getter de la valeur de byDefault
     * @return boolean : est-ce que c'est l'état par défaut?
     */
    public boolean getByDefault() {
    	return byDefault;
    }
}