import java.util.ArrayList;

public class SimulationMajority implements Simulation {
    Automate automate;

    public void init_simulation(int longueur, int largeur) {
        State zero = new State("0", 80, 80, 80, false);
        State one = new State("1", 0, 0, 0, false);

        ArrayList<State> states = new ArrayList<>();
        states.add(zero);
        states.add(one);

        int[][] voisins = new int[3][1];
        automate = new Automate(1, states, voisins, longueur, largeur);
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