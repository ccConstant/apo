import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;

/**
 * Le contrôleur de l'application, responsable de la coordination entre les différents composants.
 */
public class Controller {

    private FenetreAccueil fa;
    private FGrille fg;
    private FInit fi;
    private ReloadTimer rt;
    private boolean hexa;

    /**
     * Constructeur par défaut de la classe Controller.
     */
    public Controller() {
        fa = new FenetreAccueil(this);
        hexa = false;
    }

    /**
     * Affiche la fenêtre d'accueil.
     */
    public void afficherAccueil() {
        fa.setVisible(true);
        fa.setLocation(600, 250);
        fa.setAlwaysOnTop(true);
    }

    /**
     * Ferme la fenêtre d'accueil.
     */
    public void quitter() {
        fa.dispose();
    }

    /**
     * Renvoie le composant de la fenêtre d'accueil.
     *
     * @return Le composant de la fenêtre d'accueil.
     */
    public Component getAccueil() {
        return fa;
    }

    /**
     * Génère une nouvelle simulation en fonction du type sélectionné.
     *
     * @param type Le type de simulation à générer.
     */
    public void generer(String type) {
        fa.setVisible(false);
        int x = fa.getValX();
        int y = fa.getValY();
        int z = fa.getValZ();

        switch (type) {
            case "Jeu de la vie":
                fi = new FInitLife(this, x, y);
                break;
            case "Feu de forêt":
                fi = new FInitForestFire(this, x, y, 0);
                break;
            case "Règle de majorité":
                fi = new FInitMajorityRule(this, x, y);
                break;
            case "1D":
                fi = new FInit1D(this, x, 1);
                break;
            case "Manuel":
                fi = new FInitManuel1(this, x, y);
                break;
        }

        int tps = fa.getTps();
        int ite = fa.getIte();
        rt = new ReloadTimer(tps, ite, this);
    }

    /**
     * Recharge la simulation en cours.
     */
    public void reload() {
        fg.getSimu().rechargement();
        fg.getDessin().repaint();
    }

    /**
     * Met en pause ou reprend le rechargement automatique de la simulation.
     *
     * @param ps Le bouton de pause/reprise.
     */
    public void pause(JButton ps) {
        if (rt.getPaused()) {
            rt.resumeReload();
            ps.setText("Pause");
        } else {
            rt.pauseReload();
            ps.setText("Reprendre");
        }
    }

    /**
     * Ouvre la fenêtre de paramétrage.
     */
    public void parametre() {
        // À implémenter
    }

    /**
     * Change l'état d'une cellule suite à un clic de souris.
     *
     * @param xClick L'abscisse du clic.
     * @param yClick L'ordonnée du clic.
     */
    public void changeStateClick(int xClick, int yClick) {
        Automate auto;
        if (!(fi instanceof FInitManuel1)) {
            auto = fi.getSimu().getAutomate();
        } else {
            auto = ((FInitManuel1) fi).getAuto();
        }

        int size = fi.getDessin().getCellSize();
        int x = (xClick - 10) / size;
        int y = (yClick - 10) / size;
        if (x < fi.getCols() && y < fi.getRows()) {
            ArrayList<State> states = auto.getStates();
            Cellule target = auto.getCelluleFromPosition(x, y, hexa);
            State next = states.get((states.indexOf(target.getCurrentState()) + 1) % states.size());
            target.setCurrentState(next);
            fi.getDessin().repaint();
        }
    }

    /**
     * Lance la simulation.
     */
    public void lancer() {
    	
        fi.setVisible(false);
        int x = fa.getValX();
        int y = fa.getValY();
        int z = fa.getValZ();
        fg = new FGrille(this, x, y, fi.getSimu());
        rt.startReload();
    }

    
    

    /**
     * Définit le mode hexagonal pour la simulation.
     *
     * @param b true pour activer le mode hexagonal, false sinon.
     */
    public void setHexa(boolean b) {
        hexa = b;
    }

    /**
     * Passe à l'étape suivante pour une simulation manuelle.
     */
    public void nextManuel() {
        // À implémenter
    }
}
