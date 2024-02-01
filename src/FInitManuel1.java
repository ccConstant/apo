import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FInitManuel1 extends JFrame implements FInit{

    private int rows;
    private int cols;
    private int gridWidth;
    private int gridHeight;

    private Controller c;
    private DessinGrille dg;
    
    private Automate auto;
    
    
	public FInitManuel1(Controller c, int cols, int rows) {
        this.c = c;
        this.rows = rows;
        this.cols = cols;
        this.gridWidth = 400; 
        this.gridHeight = 400; 

        setTitle("Grille paramétrée");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(440, 480);
        setLocationRelativeTo(c.getAccueil());
        
        

        elementFenetre();

        setVisible(true);
        
	}
	public void elementFenetre() {
        this.getContentPane().setLayout(new BorderLayout());
        
        dg = new DessinGrille(rows, cols, gridWidth, gridHeight, auto);
        dg.setFocusable(true);
        dg.addMouseListener(new ClickIniListener(c));
        add(dg, BorderLayout.CENTER);

        JButton opt = new JButton("Lancer la simulation");
        opt.addActionListener(new LancerListener(c));
        add(opt, BorderLayout.SOUTH);
    }
	
    public DessinGrille getDessin() {
    	return dg;
    }
    
	public Simulation getSimu() {
		
		return null;
	}
	
	public int getRows() {
		return rows;
	}
    
	public int getCols() {
		return cols;
	}
	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		
	}
}
