import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

import javax.swing.*;

public class FInitManuel1 extends JFrame implements FInit{

    private int rows;
    private int cols;
    private int z;
    private int dim;
    
    private int gridWidth;
    private int gridHeight;

    private Controller c;
    private DessinGrille dg;
    
    private Automate auto;
	private JTextField TFn1;
	private JTextField TFr1;
	private JTextField TFg1;
	private JTextField TFb1;
	private JCheckBox CBdef1;
	private JTextField TFn2;
	private JTextField TFr2;
	private JTextField TFg2;
	private JTextField TFb2;
	private JCheckBox CBdef2;
	private JTextField TFn3;
	private JTextField TFr3;
	private JTextField TFg3;
	private JTextField TFb3;
	private JCheckBox CBdef3;
	private JCheckBox CBe3;
    
    
	public FInitManuel1(Controller c, int cols, int rows, int z, int d) {
        this.c = c;
        this.rows = rows;
        this.cols = cols;
        this.z = z;
        this.dim = d;
        this.gridWidth = 550; 
        this.gridHeight = 400; 

        setTitle("Choix états et voisins");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 560);
        setLocationRelativeTo(c.getAccueil());
        
        
        
        State selected=new State("selected", 200, 200, 200, true);
        State unselected=new State("vide", 80, 80, 80, false);
        ArrayList<State> states=new ArrayList<State>();
        states.add(selected);
        states.add(unselected);
        switch(dim) {
        case 1 : auto = new Automate(2,states , null, 3);break;
        case 2 : auto = new Automate(2,states , null, 3, 3);break;
        case 3 : auto = new Automate(2,states , null, 3, 3, 3);break;
        }
        auto.initCellules(unselected);
        auto.print();
        
        elementFenetre();

        setVisible(true);
        
	}
	public void elementFenetre() {
        this.getContentPane().setLayout(new BorderLayout());
        
        JPanel etats = new JPanel();
        etats.setLayout(new GridLayout(3, 11));
        
        JLabel e1 = new JLabel("Etat 1 : ");
        JLabel n1 = new JLabel("Nom : ");
        TFn1 = new JTextField("0");
        JLabel r1 = new JLabel("R : ");
        TFr1 = new JTextField("0");
        JLabel g1 = new JLabel("G : ");
        TFg1 = new JTextField("0");
        JLabel b1 = new JLabel("B : ");
        TFb1 = new JTextField("0");
        JLabel d1 = new JLabel("Def : ");
        CBdef1 = new JCheckBox();
        CBdef1.setSelected(true);
        
        etats.add(new JLabel());
        etats.add(e1);
        etats.add(n1);
        etats.add(TFn1);
        etats.add(r1);
        etats.add(TFr1);
        etats.add(g1);
        etats.add(TFg1);
        etats.add(b1);
        etats.add(TFb1);
        etats.add(d1);
        etats.add(CBdef1);
        
        JLabel e2 = new JLabel("Etat 2 : ");
        JLabel n2 = new JLabel("Nom : ");
        TFn2 = new JTextField("1");
        JLabel r2 = new JLabel("R : ");
        TFr2 = new JTextField("40");
        JLabel g2 = new JLabel("G : ");
        TFg2 = new JTextField("40");
        JLabel b2 = new JLabel("B : ");
        TFb2 = new JTextField("40");
        JLabel d2 = new JLabel("Def : ");
        CBdef2 = new JCheckBox();
        
        etats.add(new JLabel());
        etats.add(e2);
        etats.add(n2);
        etats.add(TFn2);
        etats.add(r2);
        etats.add(TFr2);
        etats.add(g2);
        etats.add(TFg2);
        etats.add(b2);
        etats.add(TFb2);
        etats.add(d2);
        etats.add(CBdef2);
        
        CBe3 = new JCheckBox();
        CBe3.setSelected(true);
        JLabel e3 = new JLabel("Etat 3 : ");
        JLabel n3 = new JLabel("Nom : ");
        TFn3 = new JTextField("2");
        JLabel r3 = new JLabel("R : ");
        TFr3 = new JTextField("80");
        JLabel g3 = new JLabel("G : ");
        TFg3 = new JTextField("80");
        JLabel b3 = new JLabel("B : ");
        TFb3 = new JTextField("80");
        JLabel d3 = new JLabel("Def : ");
        CBdef3 = new JCheckBox();
        
        CBe3.addActionListener(e -> {
        	boolean enabled = CBe3.isSelected();
            e3.setEnabled(enabled);
            n3.setEnabled(enabled);
            TFn3.setEnabled(enabled);
            r3.setEnabled(enabled);
            TFr3.setEnabled(enabled);
            g3.setEnabled(enabled);
            TFg3.setEnabled(enabled);
            b3.setEnabled(enabled);
            TFb3.setEnabled(enabled);
            d3.setEnabled(enabled);
            CBdef3.setEnabled(enabled);
        });
        
        etats.add(CBe3);
        etats.add(e3);
        etats.add(n3);
        etats.add(TFn3);
        etats.add(r3);
        etats.add(TFr3);
        etats.add(g3);
        etats.add(TFg3);
        etats.add(b3);
        etats.add(TFb3);
        etats.add(d3);
        etats.add(CBdef3);
        
        CBdef1.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
            	CBdef3.setSelected(false);
            	CBdef2.setSelected(false);
            }
        });
        
        CBdef2.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
            	CBdef1.setSelected(false);
            	CBdef3.setSelected(false);
            }
        });

        CBdef3.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
            	CBdef1.setSelected(false);
            	CBdef2.setSelected(false);
            }
        });
        
        
        add(etats, BorderLayout.NORTH);
        dg = new DessinGrille(auto.getLongueur(), auto.getLargeur(), gridWidth, gridHeight, auto);
        dg.setFocusable(true);
        dg.addMouseListener(new ClickIniListener(c));
        add(dg, BorderLayout.CENTER);

        JButton opt = new JButton("Lancer la simulation");
        opt.addActionListener(e -> {
            c.nextManuel(createVoisin(), createStates() );
        });
        add(opt, BorderLayout.SOUTH);
    }
	
    public DessinGrille getDessin() {
    	return dg;
    }
    
	public Simulation getSimu() {
		
		return null;
	}
	
	public Automate getAuto() {
		return auto;
		
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
	
	public ArrayList<State> createStates(){
		ArrayList<State> states = new ArrayList<>();

	    // Création du premier état
	    String name1 = TFn1.getText();
	    int r1 = Integer.parseInt(TFr1.getText());
	    int g1 = Integer.parseInt(TFg1.getText());
	    int b1 = Integer.parseInt(TFb1.getText());
	    boolean byDefault1 = CBdef1.isSelected();
	    State state1 = new State(name1, r1, g1, b1, byDefault1);
	    states.add(state1);

	    // Création du deuxième état
	    String name2 = TFn2.getText();
	    int r2 = Integer.parseInt(TFr2.getText());
	    int g2 = Integer.parseInt(TFg2.getText());
	    int b2 = Integer.parseInt(TFb2.getText());
	    boolean byDefault2 = CBdef2.isSelected();
	    State state2 = new State(name2, r2, g2, b2, byDefault2);
	    states.add(state2);

	    // Création du troisième état
	    if(CBe3.isSelected()) {
		    String name3 = TFn3.getText();
		    int r3 = Integer.parseInt(TFr3.getText());
		    int g3 = Integer.parseInt(TFg3.getText());
		    int b3 = Integer.parseInt(TFb3.getText());
		    boolean byDefault3 = CBdef3.isSelected();
		    State state3 = new State(name3, r3, g3, b3, byDefault3);
		    states.add(state3);
	    }
	    
	    return states;
	}
	
	
	public int[][] createVoisin(){
		int cpt = 0;
		for(int i = 0; i<auto.getLongueur(); i++) {
			for(int j = 0; j<auto.getLargeur(); j++) {
				if(auto.getCelluleFromPosition(j, i, false).getCurrentState().toString().equals("selected")) {
					cpt += 1;
				}
			}
		}
		int nbVoisin = cpt;
		int[][] voisin = new int[cpt][2];
		
		for(int i = 0; i<auto.getLongueur(); i++) {
			for(int j = 0; j<auto.getLargeur(); j++) {
				if(auto.getCelluleFromPosition(j, i, false).getCurrentState().toString().equals("selected")) {
					voisin[nbVoisin-cpt][0] = i-1;
					voisin[nbVoisin-cpt][1] = j-1;
					
					cpt--;
				}
			}
		}
		return voisin;
		
	}
	
}
