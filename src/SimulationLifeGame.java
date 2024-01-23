import java.util.ArrayList;

public class SimulationLifeGame {
	Automate automate ; 
	
	
	/** 
     * Initialisation de la simulation du jeu de la vie
     * @param rows le nombre de ligne de la grille
     * @param rows le nombre de colonne de la grille
     */
	public void init_simulation(int rows, int col) {
		State zero=new State("zero", 80, 80, 80, false); 
		State one=new State("one", 0,0,0, false);
		
		ArrayList<State> states=new ArrayList<State>();
		states.add(zero);
		states.add(one);
		int voisins[][]=new int[9][2];
		
		this.automate=new Automate(2, states, voisins, rows, col); 
		
		automate.position9Voisins2D();
		automate.initCellulesRandom(states);
	}
	
	

	/** 
     * Rechargement des états des cellules en suivant le principe du jeu de la vie et affichage de l'automate
     */
	public void rechargement() {
		for(Cellule a : automate.getCellules()) {
			a.rechargementLife(automate);
		}
		for(Cellule a : automate.getCellules()) {
			a.setCurrentState(a.getNextState());
		}
		
		automate.print();
	}
	
	/**
     * Getter de l'automate
     * @return Automate
     */
	public Automate getAutomate() {
		return automate;
	}
		
}


	

