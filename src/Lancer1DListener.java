import java.awt.event.ActionEvent;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Lancer1DListener {
    private JTextField ruleField;
    private JFrame frame;
    
    
public Lancer1DListener(JTextField ruleField , JFrame frame) {
	this.ruleField = ruleField;
	this.frame = frame;
	}
	
public void actionPerformed(ActionEvent e) {

	String rule = ruleField.getText();
	try {
	int regle = Integer.parseInt(rule);
	if (regle >= 0 && regle <= 255) {
	// Pourcentage feu valide
	} else {
	JOptionPane.showMessageDialog(frame, "Veuillez entrer une régle entre 0 et 255.");
	}
	} catch (NumberFormatException ex) {
	JOptionPane.showMessageDialog(frame, "Veuillez entrer un entier valide pour la régle.");
	}


}
}
