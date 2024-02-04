import java.util.ArrayList;
/**
 * La classe Simulation1D implémente l'interface Simulation et représente une simulation unidimensionnelle.
 * Elle utilise un automate cellulaire pour modéliser l'évolution d'une ligne de cellules en fonction d'une règle spécifiée.
 */
public class Simulation1D implements Simulation {
    /**
     * L'automate cellulaire utilisé pour la simulation.
     */
    Automate automate;

    /**
     * Le numéro de règle utilisé dans la simulation.
     */
    int regle;

    /**
     * Initialise la simulation avec les paramètres spécifiés.
     *
     * @param longueur La longueur de la ligne de cellules.
     * @param largeur La largeur de la ligne de cellules (dans le cas unidimensionnel, largeur = 1).
     * @param regle Le numéro de la règle utilisée pour l'évolution des cellules.
     */
    public void init_simulation(int largeur, int regle) {
        this.regle = regle;

        // Définition des états possibles (0 et 1)
        State zero = new State("0", 80, 80, 80, false);
        State one = new State("1", 0, 0, 0, false);

        // Création de la liste d'états
        ArrayList<State> states = new ArrayList<>();
        states.add(zero);
        states.add(one);

        // Définition des voisins pour l'automate (1D)
        int[][] voisins = new int[3][1];  

        // Initialisation de l'automate
        automate = new Automate(1, states, voisins, largeur);
        automate.initCellulesRandom(automate.getStates());
    }

    /**
     * Effectue une mise à jour de l'automate en fonction de la règle spécifiée.
     * Après la mise à jour, les états actuels des cellules sont mis à jour avec leurs états suivants.
     */
    public void rechargement() {
        automate.updateCells1D(regle);  

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