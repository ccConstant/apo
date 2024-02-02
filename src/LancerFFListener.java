import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LancerFFListener implements ActionListener {
    private Controller c;
    private JComboBox<Integer> combobox1;
    private JComboBox<String> combobox2;
    private JCheckBox checkBox1;
    private JTextField percentageField, forestNumber, probability, windForce, qProba;
    private JFrame frame;

    public LancerFFListener(Controller c, JComboBox<Integer> combobox1, JComboBox<String> combobox2,
                            JCheckBox checkBox1, JTextField percentageField,
                            JTextField forestNumber, JTextField probability, JTextField windForce,
                            JTextField qProba, JFrame frame) {
        this.c = c;
        this.combobox1 = combobox1;
        this.combobox2 = combobox2;
        this.checkBox1 = checkBox1;
        this.percentageField = percentageField;
        this.forestNumber = forestNumber;
        this.probability = probability;
        this.windForce = windForce;
        this.qProba = qProba;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean proba = checkBox1.isSelected();
        Integer voisins = (Integer) combobox1.getSelectedItem();
        String vent = (String) combobox2.getSelectedItem();

        String pourcentage = percentageField.getText();
        try {
            int pourcentageFeu = Integer.parseInt(pourcentage);
            if (pourcentageFeu >= 0 && pourcentageFeu <= 100) {
                // Pourcentage feu valide
            } else {
                JOptionPane.showMessageDialog(frame, "Veuillez entrer un entier entre 0 et 100 pour le pourcentage de feu.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Veuillez entrer un entier valide pour le pourcentage de feu.");
        }

        String forest = forestNumber.getText();
        try {
            Integer densite = Integer.parseInt(forest);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Veuillez entrer un entier valide pour la densité de la forêt.");
        }

        String p = probability.getText();
        try {
            double prob = Double.parseDouble(p);
            if (prob >= 0 && prob <= 1) {
            } else {
                JOptionPane.showMessageDialog(frame, "Veuillez entrer une valeur entre 0 et 1 pour la probabilité.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Veuillez entrer un nombre valide pour la probabilité.");
        }

        String force = windForce.getText();
        try {
            double forceVent = Double.parseDouble(force);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Veuillez entrer un nombre valide pour la force du vent.");
        }

        String q = qProba.getText();
        try {
            double qproba = Double.parseDouble(q);
            if (qproba >= 0 && qproba <= 1) {
            } else {
                JOptionPane.showMessageDialog(frame, "Veuillez entrer une valeur entre 0 et 1 pour la probabilité de Q.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Veuillez entrer un nombre valide pour la probabilité de Q.");
        }
        
        
    }
}
