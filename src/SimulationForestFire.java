import java.util.ArrayList;

public class SimulationForestFire {
	
	Automate automate ; 
	
	/** 
     * Initialisation de la simulation du feu de forêt 
     * @param nbrFeu le nombre de cellules à mettre en feu au début du jeu 
     * @param rows le nombre de ligne de la grille
     * @param rows le nombre de colonne de la grille
     * @param proba devont-nous inclure les probabilités ? 
     * @param nbrVoisins nombre de voisins à prendre en compte pour une cellule : 4 ou 8 
     * 
     */
	public void init_simulation(int nbrFeu, int rows, int col) { //boolean proba, int nbrVoisins
		State vide=new State("vide", 80, 80, 80, false);
		State feu=new State("en feu", 80, 8, 8, false);
		State brule=new State("brûlé", 0,0,0, false);
		State foret=new State("forêt", 0, 80,0, true);
		ArrayList<State> states=new ArrayList<State>();
		states.add(vide);
		states.add(foret);
		states.add(feu);
		states.add(brule);
		int voisins[][]=new int[9][2];
		
		this.automate=new Automate(2, states, voisins, rows, col); 
		
		automate.position9Voisins2D();
		automate.initCellules(foret);
		
		for (int i=0 ; i<nbrFeu ; i++) {
			int getRandomValue = (int) (Math.random() * (automate.getCellules().size() - 1)) + 1;
			Cellule c=automate.getCellules().get(getRandomValue) ;
			c.setCurrentState(feu);	
		}
	}
	
	/** 
     * Rechargement des états des cellules en suivant le principe du feu de forêt et affichage de l'automate
     */
	public void rechargement() {
		for(Cellule a : automate.getCellules()) {
			a.rechargementForet(automate);
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
