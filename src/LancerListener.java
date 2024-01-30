import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Classe implementant un actionListener pour faire fonctionner le bouton option de la fenêtre de la grille
 * Fait réapparaitre la fenêtre principale
 */
public class LancerListener implements ActionListener {

	//Variable contenant la fenêtre principale
	public Controller c;
	
	public LancerListener(Controller c) {
		this.c = c; 
	}

	public void actionPerformed(ActionEvent e) {
		c.lancer();
	}

}
