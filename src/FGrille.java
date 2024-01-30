import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class FGrille extends JFrame{/* implements Serializable{
	
	private static final long serialVersionUID = 2L;*/

    private int rows;
    private int cols;
    private int gridWidth;
    private int gridHeight;

    private Controller c;
    private DessinGrille dg;
    
    private SimulationLifeGame sim;

    public FGrille(Controller c, int rows, int cols, SimulationLifeGame sim) {
        this.c = c;
        this.rows = rows;
        this.cols = cols;
        this.gridWidth = 400; 
        this.gridHeight = 400; 
        
        this.sim = sim;

        setTitle("Grille paramétrée");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(440, 480);
        setLocationRelativeTo(c.getAccueil());

        elementFenetre();

        setVisible(true);
        

    }

    public void elementFenetre() {
        this.getContentPane().setLayout(new BorderLayout());
        
        dg = new DessinGrille(cols, rows, gridWidth, gridHeight, sim.getAutomate());
        dg.setFocusable(true);
        add(dg, BorderLayout.CENTER);

        JButton opt = new JButton("Options");
        opt.addActionListener(new OptListener(c));
        add(opt, BorderLayout.NORTH);
    }
    
    public DessinGrille getDessin() {
    	return dg;
    }

	public SimulationLifeGame getSimu() {
		
		return sim;
	}
    
    
}