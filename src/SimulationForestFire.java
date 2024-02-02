import java.util.ArrayList;

public class SimulationForestFire implements Simulation {
	
	Automate automate ; 
	boolean proba ; 
	double p ; 
	double q ;
	double foretBrulee ; 
	int caseForetDepart ; 
	int nbrIteration ; 
	String orVent ; 
	double fv ; 
	boolean hexa=false;
	
	/** 
     * Initialisation de la simulation du feu de forêt 
     * @param nbrFeu le nombre de cellules à mettre en feu au début du jeu 
     * @param rows le nombre de ligne de la grille
     * @param rows le nombre de colonne de la grille
     * @param nbrVoisins nombre de voisins à prendre en compte pour une cellule : 4 ou 8 
     * @param proba devons-nous inclure les probabilités ? 
     * @param p : probabilité d'une cellule forêt de prendre feu si au moins 1 de ses voisins est en feu
     * @param q : probabilité d'une cellule forêt de prendre feu si aucun de ses voisins ne sont en feu
     * @param orVent : origine du vent  
     * @param fv : force du vent 
     * 
     */
	public void init_simulation(int nbrFeu, int rows, int col, int nbrVoisins, boolean proba, double p, double q, String orVent, double fv) { 
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
		this.orVent=orVent ; 
		this.fv=fv ; 
		
		nbrIteration=0 ; 
		this.automate=new Automate(2, states, voisins, rows, col); 
		
		if (nbrVoisins==6) {
			hexa=true;
			automate.position7Voisins2D();
			automate.initCellulesHexa(foret);
		}else {
			automate.initCellules(foret);
			if (nbrVoisins==4) {
				automate.position5Voisins2D();
			}else {
				automate.position9Voisins2D();
			}
		}
		this.caseForetDepart=automate.nbrCellulesInState(foret);
		if (nbrFeu>caseForetDepart) {
			throw new Error("Le nombre de feu est supérieur au nombre de case Forêt") ; 
		}
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
			a.rechargementForet(automate, proba, p,q, orVent, fv, hexa);
		}
		for(Cellule a : automate.getCellules()) {
			a.setCurrentState(a.getNextState());
		}
		int nbrBrulee=automate.nbrCellulesInState(automate.getStates().get(3));
		foretBrulee=nbrBrulee*100/caseForetDepart ; 
		System.out.println(foretBrulee);
		
		nbrIteration++ ; 
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
	
	/**
     * Getter du temps de propagation du feu
     * @return nbrIteration
     */
	public double getNbrIteration() {
		return nbrIteration;
	}
		
}
