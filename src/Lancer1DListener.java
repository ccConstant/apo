import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Lancer1DListener implements ActionListener {
    private JTextField ruleField;
    private JFrame frame;
    private Simulation sim;
    private Controller c;
    
public Lancer1DListener(Controller c, JTextField ruleField , JFrame frame, Simulation sim) {
	this.ruleField = ruleField;
	this.frame = frame;
	this.c = c;
	this.sim = sim;
	}
	
public void actionPerformed(ActionEvent e) {

	String rule = ruleField.getText();
	try {
	int regle = Integer.parseInt(rule);
    ((Simulation1D) sim).setRule(regle);

	if (regle >= 0 && regle <= 255) {
	// Pourcentage feu valide
	} else {
	JOptionPane.showMessageDialog(frame, "Veuillez entrer une régle entre 0 et 255.");
	}
	} catch (NumberFormatException ex) {
	JOptionPane.showMessageDialog(frame, "Veuillez entrer un entier valide pour la régle.");
	}
	c.lancer();


}
}
