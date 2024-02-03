import java.util.ArrayList;

public class SimulationMajority implements Simulation {
    Automate automate;
    
    public void init_simulation(int longueur, int largeur, int d) {
        State zero = new State("0", 80, 80, 80, false);
        State one = new State("1", 0, 0, 0, false);

        ArrayList<State> states = new ArrayList<>();
        states.add(zero);
        states.add(one);
        
        int[][] voisins;
        
        if (d == 1) {
            // Initialisation des voisins pour la dimension 1
            voisins = new int[3][1];
        } else if (d == 2) {
            // Initialisation des voisins pour la dimension 2
            voisins = new int[9][2];
        } else {
            // Gérez d'autres valeurs de dimension selon vos besoins
            throw new IllegalArgumentException("La dimension " + d + " n'est pas prise en charge.");
        }

        automate = new Automate(d, states, voisins, longueur, largeur);
        automate.initCellulesRandom(automate.getStates());
    }

    public void rechargement() {
        automate.updateCellsMajority();

        for (Cellule cell : automate.getCellules()) {
            cell.setCurrentState(cell.getNextState());
        }

        automate.print();
    }

    public Automate getAutomate() {
        return automate;
    }
}