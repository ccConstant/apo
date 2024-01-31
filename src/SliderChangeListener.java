	import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.*;

/*
 * Classe implementant un changeListener pour faire fonctionner les slider des options de la fenêtre principale
 */
public class SliderChangeListener implements ChangeListener	{

	//Variable contenant le slider donné
	private JSlider s;
	//Variable contenant le texte du slider donné
	private JLabel l;
	//Variable contenant le numéro du slider donné
	private int numSlider;
	
	public SliderChangeListener (JSlider j, JLabel l, int sl) {
		s=j;
		this.l = l;
		numSlider = sl;
	}
	
	//fonction permetant de changer le texte du slider dès qu'il change de valeur
	//en fonction du numéro donné (1 : colonne, 2 : ligne)
	public void stateChanged(ChangeEvent e) {
		int var = s.getValue(); //récupère la valeur du slider
		switch (numSlider) {
		case 1 : l.setText("Nombre en X : " + String.valueOf(var));break;
		case 2 : l.setText("Nombre en Y : " + String.valueOf(var));break;
		case 3 : l.setText("Nombre en Z : " + String.valueOf(var));break;
		}
	}
}
