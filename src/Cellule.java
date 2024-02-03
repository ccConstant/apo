import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
     * Rechargement de la cellule dans le cas de la simulation manuelle
     * @param a automate auquel appartient la cellule
     * 
     */
    public void rechargementSimulManuelle(Automate a, Map<String, State> transitions) {
    	if (a.getDimension()==1) {
    		String states="";
			for (Cellule voisin : a.getVoisins2D(this, false)) { //TODO remplacer par la méthode de kawthar 
				states+=voisin.getCurrentState().getState();
			}
			if (transitions.containsKey(states)) {
				this.setNextState(transitions.get(states));
			}
			return ;
    	}
		if(a.getDimension()==2) {
			String states="";
			for (Cellule voisin : a.getVoisins2D(this, false)) {
				states+=voisin.getCurrentState().getState();
			}
			if (transitions.containsKey(states)) {
				this.setNextState(transitions.get(states));
			}
			return ;
		}if(a.getDimension()==3) {
			String states="";
			for (Cellule voisin : a.getVoisins3D(this)) {
				states+=voisin.getCurrentState().getState();
			}
			if (transitions.containsKey(states)) {
				this.setNextState(transitions.get(states));
			}
			return ;
			
		}
    	
    	
    	
    }
    
    
    /** 
     * Rechargement de la cellule dans le cas du feu de forêt
     * @param a automate auquel appartient la cellule
     */
    public void rechargementForet(Automate a, boolean proba, double p, double q, String orVent, double fv, boolean hexa) {
    	setNextState(a.getStates().get(0));
    	
    	//Si l'état actuel est 0, on reste dans l'état 0 
    	if (this.currentState.getState()==a.getStates().get(0).getState()) {
    		setNextState(a.getStates().get(0));
    	}
    	
    	
    	// Si l'état actuel est forêt, on va regarder si on a des voisins en feu
    	if (this.currentState.getState()==a.getStates().get(1).getState()) {
    		int cpt=a.nbrVoisinsInState(a.getStates().get(2), this, hexa);
    		
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
    			double calculProba=0 ; 
    			int x=this.position[0];
    			int y=this.position[1];
    			
    			//nord
    			boolean nordIsFire; 
    			Cellule nord=a.getCelluleNord(x,y,hexa); 
    			if (nord!=null) {
    				nordIsFire=nord.getCurrentState().equals(a.getStates().get(2));
    			}else {
    				nordIsFire=false;
    			}
    			
    			//sud
    			boolean sudIsFire; 
    			Cellule sud=a.getCelluleSud(x,y,hexa); 
    			if (sud!=null) {
    				sudIsFire=sud.getCurrentState().equals(a.getStates().get(2));
    			}else {
    				sudIsFire=false;
    			}
    			
    			//est
    			boolean estIsFire; 
    			Cellule est=a.getCelluleEst(x,y,hexa); 
    			if (est!=null) {
    				estIsFire=est.getCurrentState().equals(a.getStates().get(2));
    			}else {
    				estIsFire=false;
    			}
    			
    			//ouest
    			boolean ouestIsFire; 
    			Cellule ouest=a.getCelluleOuest(x,y,hexa); 
    			if (ouest!=null) {
    				ouestIsFire=ouest.getCurrentState().equals(a.getStates().get(2));
    			}else {
    				ouestIsFire=false;
    			}
    			boolean vent=true;
    			switch(orVent) {
	    			case "Nord":
	    				if (nordIsFire) {
	    					calculProba+=fv+p ; 
	    				}
	    				if (sudIsFire) {
	    					calculProba+=p-fv ;
	    				}
	    				if (estIsFire) {
	    					calculProba+=p ;
	    				}
	    				if (ouestIsFire) {
	    					calculProba+=p ;
	    				}
	    				break ; 
	    			case "Sud" :
	    				if (sudIsFire) {
	    					calculProba+=p+fv ;
	    				}
	    				if (nordIsFire) {
	    					calculProba+=p-fv ; 
	    				}
	    				if (estIsFire) {
	    					calculProba+=p ;
	    				}
	    				if (ouestIsFire) {
	    					calculProba+=p ;
	    				}
	    				break ; 
	    				
	    			case "Est":
	    				if (sudIsFire) {
	    					calculProba+=p ;
	    				}
	    				if (nordIsFire) {
	    					calculProba+=p ; 
	    				}
	    				if (estIsFire) {
	    					calculProba+=p+fv ;
	    				}
	    				if (ouestIsFire) {
	    					calculProba+=p-fv ;
	    				}
	    				break ;
	    				
	    			case "Ouest" : 
	    				if (sudIsFire) {
	    					calculProba+=p ;
	    				}
	    				if (nordIsFire) {
	    					calculProba+=p ; 
	    				}
	    				if (estIsFire) {
	    					calculProba+=p-fv ;
	    				}
	    				if (ouestIsFire) {
	    					calculProba+=p+fv ;
	    				}
	    				break ;
	    			
	    			case "Aucun":
	    				vent=false;
	    				break;
    			}
    			if (!vent) {
    				calculProba=cpt*p+q ; 
    			}
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
    	
    	int in1=a.nbrVoisinsInState(a.getStates().get(1), this, false);
    	
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
	//1D
	public void updateCell(Automate automate, int rule) {
        // Convertir le numéro de règle en binaire et remplir le dictionnaire de règles
        Map<String, Integer> ruleMap = generateRuleMap(rule);

        // Obtenez les états des voisins
        ArrayList<Cellule> neighbours = automate.getThreeNeighbours(this);
        StringBuilder binaryConfig = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            binaryConfig.append(neighbours.get(i).getCurrentState().getState());
        }

        // Utilisez le dictionnaire de règles pour obtenir le nouvel état
        int newValue = ruleMap.get(binaryConfig.toString());

        setNextState(automate.getStates().get(newValue));
    }

    private Map<String, Integer> generateRuleMap(int ruleNumber) {
        Map<String, Integer> ruleMap = new HashMap<>();
        String binaryRule = String.format("%8s", Integer.toBinaryString(ruleNumber)).replace(' ', '0');

        for (int i = 0; i < 8; i++) {
            String binaryConfig = String.format("%3s", Integer.toBinaryString(i)).replace(' ', '0');
            ruleMap.put(binaryConfig, binaryRule.charAt(7 - i) - '0');
        }

        return ruleMap;
    }
	//1D


	public void updateCellMajority(Automate automate) {
		automate.updateCellMajority(this);
	}

}