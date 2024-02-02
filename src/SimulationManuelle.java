import java.util.ArrayList;

public class SimulationManuelle implements Simulation {

	Automate automate ; 
	
	/** 
     * Initialisation de la simulation manuelle avec une seule dimension
     *  @param d dimension de l'automate
     * @param states états dans lesquels les cellules de l'automate peuvent être
     * @param voisins position des différentes voisins d'une cellule
     * @param largeur de la grille
     */
	public void init_simulation(int d, ArrayList<State> states, int voisins[][], int largeur) { 
		this.automate=new Automate(d, states, voisins, largeur); 
		State byDefault=null ; 
		for (State s : states) {
			if (s.getByDefault()) {
				byDefault=s ; 
			}
		}
		if (byDefault==null) {
			throw new Error("Il n'y a pas d'état par défaut");
		}
		automate.initCellules(byDefault);
	}
	
	/** 
     * Initialisation de la simulation manuelle avec une deux dimensions
     *  @param d dimension de l'automate
     * @param states états dans lesquels les cellules de l'automate peuvent être
     * @param voisins position des différentes voisins d'une cellule
     * @param longueur de la grille
     * @param largeur de la grille
     * 
     */
	public void init_simulation(int d, ArrayList<State> states, int voisins[][], int longueur, int largeur) { 
		this.automate=new Automate(d, states, voisins, longueur, largeur); 
		State byDefault=null ; 
		for (State s : states) {
			if (s.getByDefault()) {
				byDefault=s ; 
			}
		}
		if (byDefault==null) {
			throw new Error("Il n'y a pas d'état par défaut");
		}
		automate.initCellules(byDefault);
	}
	
	
	/** 
     * Initialisation de la simulation manuelle avec une trois dimensions
     * @param d dimension de l'automate
     * @param states états dans lesquels les cellules de l'automate peuvent être
     * @param voisins position des différentes voisins d'une cellule
     * @param longueur de la grille
     * @param largeur de la grille
     * @param z de la grille
     */
	public void init_simulation(int d, ArrayList<State> states, int voisins[][], int longueur, int largeur, int z) { 
		this.automate=new Automate(d, states, voisins, longueur, largeur, z); 
		State byDefault=null ; 
		for (State s : states) {
			if (s.getByDefault()) {
				byDefault=s ; 
			}
		}
		if (byDefault==null) {
			throw new Error("Il n'y a pas d'état par défaut");
		}
	}
		
	
	
	@Override
	public void rechargement() {
		
		
	}

	@Override
	public Automate getAutomate() {
		return automate ; 
	}

}
