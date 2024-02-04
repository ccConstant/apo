import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * ActionListener pour le lancement de la simulation Forest Fire.
 */
public class LancerFFListener implements ActionListener {
    private Controller c; // Contrôleur
    private JComboBox<Integer> combobox1; // JComboBox pour le nombre de voisins
    private JComboBox<String> combobox2; // JComboBox pour la direction du vent
    private JCheckBox checkBox1; // JCheckBox pour la sélection probabiliste
    private JTextField fireNumber, probability, windForce, qProba; // JTextFields pour divers paramètres
    private JFrame frame; // Fenêtre
    private Simulation sim; // Simulation

    /**
     * Constructeur de LancerFFListener.
     *
     * @param c            Le contrôleur de l'application.
     * @param combobox1    JComboBox pour le nombre de voisins.
     * @param combobox2    JComboBox pour la direction du vent.
     * @param checkBox1    JCheckBox pour la sélection probabiliste.
     * @param fireNumber   JTextField pour le nombre de feu de départ.
     * @param probability  JTextField pour la probabilité.
     * @param windForce    JTextField pour la force du vent.
     * @param qProba       JTextField pour la probabilité de Q.
     * @param frame        La fenêtre.
     * @param sim          La simulation.
     */
    public LancerFFListener(Controller c, JComboBox<Integer> combobox1, JComboBox<String> combobox2,
                            JCheckBox checkBox1, JTextField fireNumber, JTextField probability,
                            JTextField windForce, JTextField qProba, JFrame frame, Simulation sim) {
        this.c = c;
        this.combobox1 = combobox1;
        this.combobox2 = combobox2;
        this.checkBox1 = checkBox1;
        this.fireNumber = fireNumber;
        this.probability = probability;
        this.windForce = windForce;
        this.qProba = qProba;
        this.frame = frame;
        this.sim = sim;
    }

    /**
     * Méthode appelée lorsqu'un événement se produit.
     *
     * @param e L'événement d'action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        boolean proba = checkBox1.isSelected();
        ((SimulationForestFire) sim).setProbabiliste(proba);

        String vent = (String) combobox2.getSelectedItem();
        ((SimulationForestFire) sim).setOrVent(vent);

        String fire = fireNumber.getText();
        try {
            int feu = Integer.parseInt(fire);
            ((SimulationForestFire) sim).setNbrFeu(feu);
            ((SimulationForestFire) sim).setFeuAutomate(feu);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Veuillez entrer un entier valide pour le nombre de feu.");
        }

        int voisins = (int) combobox1.getSelectedItem();
        ((SimulationForestFire) sim).setNbrVoisins(voisins);

        if (proba) {
            String p = probability.getText();
            try {
                double prob = Double.parseDouble(p);

                if (prob >= 0 && prob <= 1) {
                    ((SimulationForestFire) sim).setProba(prob);
                } else {
                    JOptionPane.showMessageDialog(frame, "Veuillez entrer une valeur entre 0 et 1 pour la probabilité.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Veuillez entrer un nombre valide pour la probabilité.");
            }

            String force = windForce.getText();
            try {
                double forceVent = Double.parseDouble(force);

                if (forceVent >= 0 && forceVent <= 1) {
                    ((SimulationForestFire) sim).setForceVent(forceVent);
                } else {
                    JOptionPane.showMessageDialog(frame, "Veuillez entrer une valeur entre 0 et 1 pour la force du vent.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Veuillez entrer un nombre valide pour la force du vent.");
            }

            String q = qProba.getText();
            try {
                double qproba = Double.parseDouble(q);

                if (qproba >= 0 && qproba <= 1) {
                    ((SimulationForestFire) sim).setQProba(qproba);
                } else {
                    JOptionPane.showMessageDialog(frame, "Veuillez entrer une valeur entre 0 et 1 pour la probabilité de Q.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Veuillez entrer un nombre valide pour la probabilité de Q.");
            }
        }

        c.lancer(); // Lancement de la simulation
    }
}
