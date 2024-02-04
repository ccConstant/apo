import java.util.ArrayList;
/**
 * La classe SimulationMajority implémente l'interface Simulation et représente une simulation basée sur la règle de majorité.
 * Elle utilise un automate cellulaire pour modéliser l'évolution des cellules en fonction de l'état majoritaire parmi leurs voisins.
 */
public class SimulationMajority implements Simulation {
    /**
     * L'automate cellulaire utilisé pour la simulation.
     */
    Automate automate;
    
    /**
     * Initialise la simulation avec les paramètres spécifiés.
     *
     * @param longueur La longueur de la grille de cellules.
     * @param largeur La largeur de la grille de cellules.
     * @param d La dimension de l'automate cellulaire (1 pour une dimension, 2 pour deux dimensions, etc.).
     * @throws IllegalArgumentException Si la dimension spécifiée n'est pas prise en charge.
     */
    public void init_simulation(int longueur, int largeur, int d) {
        // Définition des états possibles (0 et 1)
        State zero = new State("0", 80, 80, 80, false);
        State one = new State("1", 0, 0, 0, false);

        // Création de la liste d'états
        ArrayList<State> states = new ArrayList<>();
        states.add(zero);
        states.add(one);
        
        // Initialisation des voisins en fonction de la dimension
        int[][] voisins;
        
        if (d == 1) {
            // Initialisation des voisins pour la dimension 1
            voisins = new int[3][1];
        } else if (d == 2) {
            // Initialisation des voisins pour la dimension 2
            voisins = new int[9][2];
        } else {
            
            throw new IllegalArgumentException("La dimension " + d + " n'est pas prise en charge.");
        }

        // Initialisation de l'automate
        automate = new Automate(d, states, voisins, longueur, largeur);
        automate.initCellulesRandom(automate.getStates());
    }

    /**
     * Effectue une mise à jour de l'automate en fonction de la règle de majorité.
     * Après la mise à jour, les états actuels des cellules sont mis à jour avec leurs états suivants.
     */
    public void rechargement() {
        automate.updateCellsMajority();

        // Mise à jour des états actuels des cellules avec leurs états suivants
        for (Cellule cell : automate.getCellules()) {
            cell.setCurrentState(cell.getNextState());
        }

        // Affichage de l'état actuel de l'automate
        automate.print();
    }

    /**
     * Récupère l'automate utilisé dans la simulation.
     *
     * @return L'automate cellulaire utilisé dans la simulation.
     */
    public Automate getAutomate() {
        return automate;
    }
}