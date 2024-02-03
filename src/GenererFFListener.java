import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class GenererFFListener implements ActionListener {
	 private JTextField percentageField;
	    private JFrame frame;

	 
	 public GenererFFListener(JTextField percentageField, JFrame frame) {
		 this.percentageField = percentageField;
		 this.frame = frame;
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		
        String pourcentage = percentageField.getText();
        try {
            int pourcentageForet = Integer.parseInt(pourcentage);
            if (pourcentageForet >= 0 && pourcentageForet <= 100) {
            } else {
                JOptionPane.showMessageDialog(frame, "Veuillez entrer un entier entre 0 et 100 pour le pourcentage de foret.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Veuillez entrer un entier valide pour le pourcentage de foret.");
        }
	}

}
