import java.util.ArrayList;

public class SimulationForestFire implements Simulation {
	
	Automate automate ; 
	boolean proba ; 
	double p ; 
	double q ;
	double foretBrulee ; 
	int caseForetDepart ; 
	
	/** 
     * Initialisation de la simulation du feu de forêt 
     * @param nbrFeu le nombre de cellules à mettre en feu au début du jeu 
     * @param rows le nombre de ligne de la grille
     * @param rows le nombre de colonne de la grille
     * @param nbrVoisins nombre de voisins à prendre en compte pour une cellule : 4 ou 8 
     * @param proba devons-nous inclure les probabilités ? 
     * @param p : probabilité d'une cellule forêt de prendre feu si au moins 1 de ses voisins est en feu
     * @param q : probabilité d'une cellule forêt de prendre feu si aucun de ses voisins ne sont en feu
     * 
     */
	public void init_simulation(int nbrFeu, int rows, int col, int nbrVoisins, boolean proba, double p, double q) { //mettre le vent
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
		this.proba=proba ; 
		this.p=p;
		this.q=q;
		
		this.automate=new Automate(2, states, voisins, rows, col); 
		
		if (nbrVoisins==4) {
			automate.position5Voisins2D();
		}else {
			automate.position9Voisins2D();
		}
		automate.position9Voisins2D();
		automate.initCellules(foret);
		this.caseForetDepart=automate.nbrCellulesInState(foret);
		boolean find=false;
		
		for (int i=0 ; i<nbrFeu ; i++) {
			find=false;
			while(!find) {
				int getRandomValue = (int) (Math.random() * (automate.getCellules().size() - 1)) + 1;
				Cellule c=automate.getCellules().get(getRandomValue) ;
				if (c.getCurrentState()==foret) {
					c.setCurrentState(feu);	
					find=true;
				}
			}
		}
	}
	
	/** 
     * Rechargement des états des cellules en suivant le principe du feu de forêt et affichage de l'automate
     */
	public void rechargement() {
		for(Cellule a : automate.getCellules()) {
			a.rechargementForet(automate, proba, p,q);
		}
		for(Cellule a : automate.getCellules()) {
			a.setCurrentState(a.getNextState());
		}
		int nbrBrulee=automate.nbrCellulesInState(automate.getStates().get(3));
		foretBrulee=nbrBrulee*100/caseForetDepart ; 
		System.out.println(foretBrulee);
		
		
		automate.print();
	}
	
	/**
     * Getter de l'automate
     * @return Automate
     */
	public Automate getAutomate() {
		return automate;
	}
	
	/**
     * Getter du pourcentage de forêt brulée
     * @return foretBrulee
     */
	public double getForetBrulee() {
		return foretBrulee;
	}
		
}
