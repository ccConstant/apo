import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SimulationManuelle implements Simulation {

	Automate automate ; 
	Map<String, State> transitions=new HashMap<String, State>() ; 
	
	
	/** 
     * Initialisation de la simulation manuelle avec une seule dimension
     *  @param d dimension de l'automate
     * @param states états dans lesquels les cellules de l'automate peuvent être
     * @param voisins position des différentes voisins d'une cellule
     * @param largeur de la grille
     * @param map représentant les transitions entre les différents états
     * @param nombre de voisins
     */
	public void init_simulation(int d, ArrayList<State> states, int voisins[][], int largeur, Map<String, State> transitions, int nbrVoisins) { 
		this.automate=new Automate(d, states, voisins, largeur); 
		this.transitions=transitions ; 
		State byDefault=null ; 
		automate.setNombreVoisins(nbrVoisins);
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
     * @param map représentant les transitions entre les différents états
     * @param nombre de voisins
     * 
     */
	public void init_simulation(int d, ArrayList<State> states, int voisins[][], int longueur, int largeur, Map<String, State> transitions, int nbrVoisins) { 
		this.automate=new Automate(d, states, voisins, longueur, largeur); 
		State byDefault=null ; 
		automate.setNombreVoisins(nbrVoisins);
		this.transitions=transitions ; 
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
     * @param map représentant les transitions entre les différents états
     * @param nombre de voisins
     */
	public void init_simulation(int d, ArrayList<State> states, int voisins[][], int longueur, int largeur, int z, Map<String, State> transitions, int nbrVoisins) { 
		this.automate=new Automate(d, states, voisins, longueur, largeur, z); 
		State byDefault=null ;
		this.transitions=transitions ; 
		automate.setNombreVoisins(nbrVoisins);
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
		
	
	
	@Override
	public void rechargement() {
		for(Cellule a : automate.getCellules()) {
			a.rechargementSimulManuelle(automate, transitions);
		}
		for(Cellule a : automate.getCellules()) {
			a.setCurrentState(a.getNextState());
		}
		automate.print();
		
	}

	@Override
	public Automate getAutomate() {
		return automate ; 
	}

}
