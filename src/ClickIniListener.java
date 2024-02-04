import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * La classe ClickIniListener implémente l'interface MouseListener
 * pour écouter les événements de clic de souris sur la grille d'initialisation.
 */
public class ClickIniListener implements MouseListener {
    
    Controller c; // Le contrôleur associé à l'écouteur de clic
    
    /**
     * Constructeur de la classe ClickIniListener.
     * @param c Le contrôleur associé à l'écouteur de clic.
     */
    public ClickIniListener(Controller c) {
        this.c = c;
    }

    /**
     * Méthode appelée lorsque la souris est cliquée.
     * @param e L'événement de la souris.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        c.changeStateClick(e.getX(), e.getY()); // Appel de la méthode du contrôleur pour changer l'état au clic
    }

    // Méthodes suivantes non utilisées, mais nécessaires en raison de l'implémentation de l'interface MouseListener
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
}
