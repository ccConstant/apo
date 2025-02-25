import javax.swing.*;
import java.awt.*;

/**
 * La classe FGrille est une JFrame utilisée pour afficher la grille de simulation de l'automate.
 */
public class FGrille extends JFrame {

    private int rows;
    private int cols;
    private int gridWidth;
    private int gridHeight;

    private Controller c;
    private DessinGrille dg;
    private Simulation sim;
	private JSlider SLZ;

    /**
     * Constructeur de la classe FGrille.
     *
     * @param c    Le contrôleur de l'application.
     * @param rows Le nombre de lignes de la grille.
     * @param cols Le nombre de colonnes de la grille.
     * @param sim  La simulation à afficher.
     */
    public FGrille(Controller c, int rows, int cols, Simulation sim) {
        this.c = c;
        this.rows = rows;
        this.cols = cols;
        this.gridWidth = 400;
        this.gridHeight = 400;
        this.sim = sim;

        setTitle("Simulation Automate");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(440, 480);
        setLocationRelativeTo(c.getAccueil());

        elementFenetre();

        setVisible(true);
    }

    /**
     * Méthode privée pour ajouter les éléments à la fenêtre.
     */
    private void elementFenetre() {
        getContentPane().setLayout(new BorderLayout());

        JPanel gridMid = new JPanel();
        gridMid.setLayout(new BorderLayout());
        dg = new DessinGrille(cols, rows, gridWidth, gridHeight, sim.getAutomate());
        dg.setFocusable(true);
        gridMid.add(dg, BorderLayout.CENTER);
        if(sim.getAutomate().getDimension() == 3) {
        	JLabel labZ = new JLabel("z = 1 : ");
        	JPanel slider = new JPanel();
        	slider.setLayout(new FlowLayout());
        	slider.add(labZ);
    		c.changeZ(0);
        	SLZ = new JSlider(0, sim.getAutomate().getHauteur()-1, 0);
        	SLZ.addChangeListener(e -> {
        		c.changeZ(SLZ.getValue());
        		labZ.setText("z = " + (SLZ.getValue() + 1) + " : ");
            });
        	slider.add(SLZ);
        	gridMid.add(slider, BorderLayout.SOUTH);
        }
        
        add(gridMid, BorderLayout.CENTER);

        JPanel gridBas = new JPanel();
        gridBas.setLayout(new GridLayout(1, 2));

        JButton quitter = new JButton("Quitter");
        JButton ps = new JButton("Reprendre");
        ps.addActionListener(new PauseListener(c, ps));
        add(ps, BorderLayout.SOUTH);

        gridBas.add(ps);
        gridBas.add(quitter);

        quitter.addActionListener(e -> dispose());

        add(gridBas, BorderLayout.SOUTH);
    }

    /**
     * Renvoie l'objet DessinGrille associé à cette fenêtre.
     *
     * @return L'objet DessinGrille.
     */
    public DessinGrille getDessin() {
        return dg;
    }

    /**
     * Renvoie l'objet Simulation associé à cette fenêtre.
     *
     * @return L'objet Simulation.
     */
    public Simulation getSimu() {
        return sim;
    }
}
