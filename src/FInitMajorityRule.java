import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * La classe FInitMajorityRule est une JFrame utilisée pour initialiser les paramètres de l'automate "Règle de majorité".
 */
public class FInitMajorityRule extends JFrame implements FInit{

    private int rows;
    private int cols;
    private int gridWidth;
    private int gridHeight;

    private Controller c;
    private DessinGrille dg;
    
    private Simulation sim;
    
    /**
     * Constructeur de la classe FInitMajorityRule.
     *
     * @param c    Le contrôleur de l'application.
     * @param cols Le nombre de colonnes de la grille.
     * @param rows Le nombre de lignes de la grille.
     */
    public FInitMajorityRule(Controller c, int cols, int rows) {
        this.c = c;
        this.rows = rows;
        this.cols = cols;
        this.gridWidth = 400; 
        this.gridHeight = 400; 

        setTitle("Règle de majorité");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(440, 480);
        setLocationRelativeTo(c.getAccueil());
        
        sim = new SimulationMajority();
        ((SimulationMajority)sim).init_simulation(rows, cols);

        elementFenetre();

        setVisible(true);
        
    }
    
    /**
     * Initialise les éléments de la fenêtre.
     */
    public void elementFenetre() {
        this.getContentPane().setLayout(new BorderLayout());
        
        dg = new DessinGrille(rows, cols, gridWidth, gridHeight, sim.getAutomate());
        dg.setFocusable(true);
        dg.addMouseListener(new ClickIniListener(c));
        add(dg, BorderLayout.CENTER);

        JButton opt = new JButton("Lancer la simulation");
        opt.addActionListener(new LancerListener(c));
        add(opt, BorderLayout.SOUTH);
    }
    
    /**
     * Renvoie l'objet DessinGrille associé à la fenêtre.
     * @return L'objet DessinGrille.
     */
    public DessinGrille getDessin() {
        return dg;
    }
    
    /**
     * Renvoie l'objet Simulation associé à la fenêtre.
     * @return L'objet Simulation.
     */
    public Simulation getSimu() {
        return sim;
    }
    
    /**
     * Renvoie le nombre de lignes de la grille.
     * @return Le nombre de lignes.
     */
    public int getRows() {
        return rows;
    }
    
    /**
     * Renvoie le nombre de colonnes de la grille.
     * @return Le nombre de colonnes.
     */
    public int getCols() {
        return cols;
    }
    
    /**
     * Modifie la visibilité de la fenêtre.
     * @param b true pour rendre visible, false pour rendre invisible.
     */
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
    }
}
