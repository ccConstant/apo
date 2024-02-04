import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * ActionListener pour le lancement de la simulation 1D.
 */
public class Lancer1DListener implements ActionListener {
    private JTextField ruleField; // Champ de texte pour la règle
    private JFrame frame; // Fenêtre
    private Simulation sim; // Simulation
    private Controller c; // Contrôleur

    /**
     * Constructeur de Lancer1DListener.
     *
     * @param c         Le contrôleur de l'application.
     * @param ruleField Le champ de texte pour la règle.
     * @param frame     La fenêtre.
     * @param sim       La simulation.
     */
    public Lancer1DListener(Controller c, JTextField ruleField, JFrame frame, Simulation sim) {
        this.ruleField = ruleField;
        this.frame = frame;
        this.c = c;
        this.sim = sim;
    }

    /**
     * Méthode appelée lorsqu'un événement se produit.
     *
     * @param e L'événement d'action.
     */
    public void actionPerformed(ActionEvent e) {
        String rule = ruleField.getText();
        try {
            int regle = Integer.parseInt(rule);

            if (regle >= 0 && regle <= 255) {
                ((Simulation1D) sim).setRule(regle);
            } else {
                JOptionPane.showMessageDialog(frame, "Veuillez entrer une règle entre 0 et 255.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Veuillez entrer un entier valide pour la règle.");
        }

        c.lancer(); // Appel de la méthode lancer du contrôleur pour démarrer la simulation
    }
}
