import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * ActionListener pour le bouton de pause.
 */
public class PauseListener implements ActionListener {
	
    // Le contrôleur de l'application
    private Controller c;
    
    // Le bouton de pause
    private JButton ps;

    /**
     * Constructeur de PauseListener.
     *
     * @param c Le contrôleur de l'application.
     * @param ps Le bouton de pause.
     */
    public PauseListener(Controller c, JButton ps) {
        this.c = c;
        this.ps = ps;
    }

    /**
     * Méthode appelée lorsqu'un événement d'action se produit.
     *
     * @param e L'événement d'action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        c.pause(ps); // Appelle la méthode pause du contrôleur
    }

}
