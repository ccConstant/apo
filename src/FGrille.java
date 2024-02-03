import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class FGrille extends JFrame{
	
    private int rows;
    private int cols;
    private int gridWidth;
    private int gridHeight;

    private Controller c;
    private DessinGrille dg;
    
    private Simulation sim;

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

    public void elementFenetre() {
        this.getContentPane().setLayout(new BorderLayout());
        
        dg = new DessinGrille(cols, rows, gridWidth, gridHeight, sim.getAutomate());
        dg.setFocusable(true);
        add(dg, BorderLayout.CENTER);
        
		JPanel gridBas = new JPanel(); 
		gridBas.setLayout(new GridLayout(1,2));
		
		JButton quitter = new JButton("Quitter");
        JButton ps = new JButton("Reprendre");
        ps.addActionListener(new PauseListener(c, ps));
        add(ps, BorderLayout.SOUTH);
        
		gridBas.add(ps);
		gridBas.add(quitter);
		
		quitter.addActionListener(e -> dispose());
		
		this.add(gridBas, BorderLayout.SOUTH);
    }
    
    public DessinGrille getDessin() {
    	return dg;
    }

	public Simulation getSimu() {
		
		return sim;
	}
    
    
}