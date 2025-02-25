import java.awt.Component;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;

public class Controller {

	private FenetreAccueil fa;
	private FGrille fg;
	private FInit fi;
	
	private ReloadTimer rt;
	private boolean hexa;
	private int x;
	private int y;
	private int z;
	
	private int currentZ;
	
	public Controller() {
		fa = new FenetreAccueil(this);
		hexa = false;
		
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
	
	public void generer(String type) {
		fa.setVisible(false);
		switch(fa.getDim()) {
        case 1 : x = fa.getValX(); y=1; z=1;break;
        case 2 :  x = fa.getValX(); y=fa.getValY(); z=1;break;
        case 3 :  x = fa.getValX(); y=fa.getValY(); z=fa.getValZ();break;
		}
		
		switch(type) {
		case "Jeu de la vie" : fi = new FInitLife(this, x, y);break;
		case "Feu de forêt" : fi = new FInitForestFire(this, x, y, 0);break;
		case "Règle de majorité" : fi = new FInitMajorityRule(this, x, y);break;
		case "1D" : fi = new FInit1D(this, x, 1);break;
		case "Manuel" : fi = new FInitManuel1(this, x, y, z, fa.getDim());break;
		}
		
		
		int tps = fa.getTps();
		int ite = fa.getIte();
		rt = new ReloadTimer(tps, ite, this);	
	}
	

	public void reload() {
		fg.getSimu().rechargement();
		fg.getDessin().repaint();
		
	}
	
	public void pause(JButton ps) {
		if(rt.getPaused()) {
			rt.resumeReload();
			ps.setText("Pause");
		}else {
			rt.pauseReload();
			ps.setText("Reprendre");
		}
	}
	
	public void parametre() {
		
	}
	
	public void changeStateClick(int xClick, int yClick) {
		Automate auto;
		if((!(fi instanceof FInitManuel1)) && (!(fi instanceof FInitManuel2))) {
			auto = fi.getSimu().getAutomate();
		} else if(fi instanceof FInitManuel1) {
			auto = ((FInitManuel1)fi).getAuto();
		} else {
			auto = ((FInitManuel2)fi).getAuto();
		}
		
		int size = fi.getDessin().getCellSize();
		int x = (xClick-10)/size;
		int y = (yClick-10)/size;
		if (x < fi.getCols() && y < fi.getRows()) {
			ArrayList<State> states = auto.getStates();
			Cellule target = auto.getCelluleFromPosition(x, y, currentZ, hexa);
			State next = states.get((states.indexOf(target.getCurrentState())+1)%states.size());
			if(next.getR() == -1) {
				next = states.get((states.indexOf(target.getCurrentState())+2)%states.size());
			}
			if(target.getCurrentState().getR() != -1) {
				target.setCurrentState(next);
			}
			
			fi.getDessin().repaint();
		}
	}

	public void lancer() {
		fi.setVisible(false);
		fg = new FGrille(this, x, y, fi.getSimu());
		fg.getDessin().setHexa(hexa);
		fg.getDessin().repaint();
		rt.startReload();
		
	}
	
	public void setHexa(boolean b) {
		hexa = b;
	}

	public void nextManuel(int[][] voisins, ArrayList<State> arrayList) {
		fi.setVisible(false);
		fi = new FInitManuel2(this,x, y , voisins, arrayList, z, fa.getDim());
		fi.setVisible(true);
	}
	
	public void initManuel(int[][] voisins, ArrayList<State> arrayList, Map<String, State> regle) {
		fi.setVisible(false);
		fi = new FInitManuel3(this,x, y , voisins, arrayList, regle, z, fa.getDim());
		fi.setVisible(true);
	}

	public void changeZ(int value) {
		currentZ = value;
		if(fg == null) {
			fi.getDessin().setZ(value);
			fi.getDessin().repaint();
		} else {
			fg.getDessin().setZ(value);
			fg.getDessin().repaint();
		}
		
	}
	
	

}
