import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener pour générer une simulation de feu de forêt avec un pourcentage de forêt spécifié.
 */
public class GenererFFListener implements ActionListener {
    private JTextField percentageField; // Champ de texte pour le pourcentage de forêt
    private JFrame frame; // Fenêtre de l'application
    private SimulationForestFire sim; // Simulation de feu de forêt
    private int rows; // Nombre de lignes dans la grille
    private int cols; // Nombre de colonnes dans la grille
    private Controller c; // Contrôleur de l'application
    private DessinGrille dg; // Dessin de la grille

    /**
     * Constructeur de GenererFFListener.
     *
     * @param c              Le contrôleur de l'application.
     * @param percentageField Le champ de texte pour le pourcentage de forêt.
     * @param frame          La fenêtre de l'application.
     * @param sim            La simulation de feu de forêt.
     * @param rows           Le nombre de lignes dans la grille.
     * @param cols           Le nombre de colonnes dans la grille.
     * @param dg             Le dessin de la grille.
     */
    public GenererFFListener(Controller c, JTextField percentageField, JFrame frame, SimulationForestFire sim, int rows, int cols, DessinGrille dg) {
        this.percentageField = percentageField;
        this.frame = frame;
        this.sim = sim;
        this.rows = rows;
        this.cols = cols;
        this.c = c;
        this.dg = dg;
    }

    /**
     * Méthode appelée lorsqu'un événement se produit.
     *
     * @param e L'événement d'action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String pourcentage = percentageField.getText(); // Récupère le pourcentage de forêt saisi dans le champ de texte
        try {
            int pourcentageForet = Integer.parseInt(pourcentage); // Convertit le pourcentage en entier

            // Vérifie si le pourcentage est dans la plage valide (entre 0 et 100)
            if (pourcentageForet >= 0 && pourcentageForet <= 100) {
                sim.setPourcentage(pourcentageForet); // Définit le pourcentage de forêt dans la simulation
                sim.init_simulation(0, rows, cols, 0, false, 0, 0, "", 0, pourcentageForet); // Initialise la simulation avec le nouveau pourcentage de forêt
                dg.updateAutomate(sim.getAutomate()); // Met à jour le dessin de la grille avec la nouvelle simulation
            } else {
                JOptionPane.showMessageDialog(frame, "Veuillez entrer un entier entre 0 et 100 pour le pourcentage de forêt.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Veuillez entrer un entier valide pour le pourcentage de forêt.");
        }
    }
}
