import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class FGrille extends JFrame{/* implements Serializable{
	
	private static final long serialVersionUID = 2L;*/

    private int rows;
    private int cols;
    private int startX;
    private int startY;
    private int gridWidth;
    private int gridHeight;

    private Controller c;
    private DessinGrille dg;
    
    SimulationForestFire f;

    public FGrille(Controller c, int rows, int cols) {
        this.c = c;
        this.rows = rows;
        this.cols = cols;
        this.startX = 10; 
        this.startY = 10; 
        this.gridWidth = 400; 
        this.gridHeight = 400; 

        setTitle("Grille paramétrée");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(440, 480);
        setLocationRelativeTo(c.getAccueil());
        
        f = new SimulationForestFire();
        f.init_simulation(2, rows, cols);

        elementFenetre();

        setVisible(true);
        

    }

    public void elementFenetre() {
        this.getContentPane().setLayout(new BorderLayout());
        
        dg = new DessinGrille(rows, cols, startX, startY, gridWidth, gridHeight, f);
        dg.setFocusable(true);
        add(dg, BorderLayout.CENTER);

        JButton opt = new JButton("Options");
        opt.addActionListener(new OptListener(c));
        add(opt, BorderLayout.NORTH);
    }
    
    public DessinGrille getDessin() {
    	return dg;
    }
    
}