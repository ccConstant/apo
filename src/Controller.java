import java.awt.Component;

public class Controller {

	private FenetreAccueil fa;
	private FGrille fg;
	
	public Controller() {
		fa = new FenetreAccueil(this);
		
	}

	public void afficherAccueil() {
		fa.setVisible(true);
		fa.setLocation(600,250);
		fa.setAlwaysOnTop(true);
	}

	public void quitter() {
		fa.dispose();
	}

	public Component getAccueil() {
		return fa;
	}
	
	public void generer() {
		fa.setVisible(false);
		int x = fa.getValX();
		int y = fa.getValY();
		int z = fa.getValZ();
		fg = new FGrille(this, x, y);
		
	}

	public void parametre() {
		fg.getDessin().getAuto().rechargement();
		fg.getDessin().repaint();
		
	}
}
