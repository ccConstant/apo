import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import java.awt.event.ActionEvent;


public class GenererListener implements ActionListener {
	//Variable contenant la fenêtre principale
	private Controller c;
	private JComboBox<String> type;
	
	public GenererListener (Controller c, JComboBox<String> cbAuto) {
		this.c = c;
		this.type = cbAuto;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		c.generer(type.getSelectedItem().toString()); //lance la fonction genererGrille de la fenêtre principale
	}
	

}
