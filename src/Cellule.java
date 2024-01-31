public class Cellule{
    State currentState ;
    State nextState ; 
    int[] position ; // doit être de taille [d]

    /** 
     * Constructeur
     * @param state état actuel de la cellule à créer
     * @param position position de la cellule à créer
     */
    public Cellule(State state, int[] position) {
        this.currentState = state;
        this.position = position;
        this.nextState=state;
    }
    
    /** 
     * ToString 
     * @return String une chaine de caractères représentant la cellule avec son type actuel et sa position 
     */
    public String toString() {
       return "| " + currentState.toString() + " x : "+ position[0] + " y : "+ position[1]+" |" ;
    }
    
    /**
     * Getter de la position de la cellule
     * @return int[] position de la cellule
     */
    public int[] getPosition() {
    	return position ;
    }
    
    /**
     * Setter de l'état courant de la cellule
     * @param s état courant de la cellule pour modification
     */
    public void setCurrentState(State s) {
    	currentState=s;
    }
    
    /**
     * Setter du prochain état de la cellule
     * @param s prochain état de la cellule pour modification
     */
    public void setNextState(State s) {
    	nextState=s;
    }
    
    /**
     * Getter de l'état actuel de la cellule
     * @return State état actuel de la cellule
     */
    public State getCurrentState() {
    	return currentState;
    }
    
    /**
     * Getter du prochain état de la cellule
     * @return State prochain état de la cellule
     */
    public State getNextState() {
    	return nextState ;
    }
    
    
    /** 
     * Rechargement de la cellule dans le cas du feu de forêt
     * @param a automate auquel appartient la cellule
     */
    public void rechargementForet(Automate a, boolean proba, double p, double q) {
    	setNextState(a.getStates().get(0));
    	
    	//Si l'état actuel est 0, on reste dans l'état 0 
    	if (this.currentState.getState()==a.getStates().get(0).getState()) {
    		setNextState(a.getStates().get(0));
    	}
    	
    	
    	// Si l'état actuel est forêt, on va regarder si on a des voisins en feu
    	if (this.currentState.getState()==a.getStates().get(1).getState()) {
    		int cpt=a.nbrVoisinsInState(a.getStates().get(2), this);
    		
    		//si nous ne sommes pas dans le cas probabiliste 
    		if (!proba) {
    	    	
	    		//Si on a pas de voisins en feu, la case reste forêt
	    		if (cpt==0) {
	    			setNextState(a.getStates().get(1));
	    		//Si on a au moins un voisin en feu, le prochain état de la case sera en feu 
	    		}else {
	    			setNextState(a.getStates().get(2));
	    		}
	    	//cas probabiliste
    		}else {
    			double calculProba=cpt*p+q ; 
    			double number=Math.random();
    			if (number<calculProba) {
    				setNextState(a.getStates().get(2));
    			}else {
    				setNextState(a.getStates().get(1));
    			}
    			
    		}
    	}
    	
    	//Si la case est en feu actuel, son prochain état sera brûlé
    	if (this.currentState.getState()==a.getStates().get(2).getState()) {
    		setNextState(a.getStates().get(3));
    	}
    	
    	//Si la case est brûlé, elle le reste
    	if (this.currentState.getState()==a.getStates().get(3).getState()) {
    		setNextState(a.getStates().get(3));
    	}
    }
    
    /** 
     * Rechargement de la cellule dans le cas du jeu de la vie
     * @param a automate auquel appartient la cellule
     */
    public void rechargementLife(Automate a) {
    	setNextState(a.getStates().get(0));
    	
    	int in1=a.nbrVoisinsInState(a.getStates().get(1), this);
    	
    	switch(in1) {
    		//Si nbrVoisins à 1 est <2 on va dans l'état 0 
    		case 0:
    		case 1:
    			setNextState(a.getStates().get(0));
    			break;
    		
    	
    		case 2: 
    			//Si actuellement état 0 => 0 
    			if (this.currentState.getState()==a.getStates().get(0).getState()){
    				setNextState(a.getStates().get(0));
    				
    			//Si actuellement état 1 => 1
    			}else {
    				setNextState(a.getStates().get(1));
    			}
    			//Si actuellement état 1 => 1
    			break;
    			
    		//Si nbrVoisins à 1 ==3 on va dans l'état 1
    		case 3: 
    			setNextState(a.getStates().get(1));
    			break;
    		
    			//Si nbrVoisins à 1 est >3 on va dans l'état 0 
    		case 4:
    		case 5: 
    		case 6:
    		case 7:
    		case 8:
    		case 9:
    			setNextState(a.getStates().get(0));
    			break;
    			
    			
    	}
    }
}