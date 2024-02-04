import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ActionListener pour le bouton option de la fenêtre de la grille.
 * Fait réapparaître la fenêtre principale.
 */
public class LancerListener implements ActionListener {

    // Variable contenant la fenêtre principale
    private Controller c;

    /**
     * Constructeur de LancerListener.
     *
     * @param c Le contrôleur de l'application.
     */
    public LancerListener(Controller c) {
        this.c = c;
    }

    /**
     * Méthode appelée lorsqu'un événement d'action se produit.
     *
     * @param e L'événement d'action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        c.lancer(); // Fait réapparaître la fenêtre principale
    }
}
