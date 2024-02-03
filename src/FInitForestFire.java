import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class FInitForestFire extends JFrame implements FInit {
    static int HAUTEUR = 750;
    final static int LARGEUR = 440;
    
    private int rows;
    private int cols;
    private int gridWidth;
    private int gridHeight;
    private Controller c;
    static JComboBox<Integer> combobox1;
    static JComboBox<String> combobox2;
    static JCheckBox checkBox1, checkBox2;
    static JTextField percentageField, fireNumber, probability, windForce, qProba;
    private JFrame frame;
    private Simulation sim;
    private DessinGrille dg;
	private boolean isDisabled;
    static JLabel l1, l2, l3, l4, l5, l6, l7, l8;

    public FInitForestFire(Controller c, int cols, int rows) {
    	
        this.c = c;
        this.rows = rows;
        this.cols = cols;
        this.gridWidth = 400; 
        this.gridHeight = 400; 
        
        sim = new SimulationForestFire();
        ((SimulationForestFire) sim).init_simulation(0, rows, cols, 0, false, 0, 0, "", 0);
        
        
        setTitle("Paramètres: Automate feu de forêt");
        setSize(LARGEUR, HAUTEUR);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        Integer s[] = {4, 6, 8};
        combobox1 = new JComboBox<>(s);
        combobox1.setPreferredSize(new Dimension(100, 20));

        

        String v[] = {"Aucun", "Nord", "Sud", "Est", "Ouest"};
        combobox2 = new JComboBox<>(v);
        combobox2.setPreferredSize(new Dimension(100, 20)); 


        l1 = new JLabel("Nombre de voisins: ");

        l2 = new JLabel("Pourcentage de forêt: ");

        l3 = new JLabel("Nombre de feu de départ: ");

        l4 = new JLabel("Probabiliste: ");

        checkBox1 = new JCheckBox("Oui");
        checkBox1.setSelected(true);
        
        checkBox2 = new JCheckBox("Non");

        l5 = new JLabel("Probabilité: ");

        l6 = new JLabel("Vent: ");

        l7 = new JLabel("Force du vent : ");

        l8 = new JLabel("Probabilité de Q: ");
        


        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createOptionsPanel(), BorderLayout.NORTH);
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH); 
        getContentPane().add(mainPanel);

        setLocationRelativeTo(null);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(checkBox1);
        buttonGroup.add(checkBox2);

        checkBox1.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                checkBox2.setSelected(false);
            }
        });

        checkBox2.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                checkBox1.setSelected(false);
            }
        });
       
        
        
        dg = new DessinGrille(rows, cols, gridWidth, gridHeight, sim.getAutomate());
        dg.setFocusable(true);
        dg.addMouseListener(new ClickIniListener(c));
        combobox1.addItemListener(e -> {
            if (combobox1.getSelectedItem().toString().equals("6")) {
            	dg.setHexa(true);
                c.setHexa(true);
            }else { 
                dg.setHexa(false);
                c.setHexa(false);
            }
            });
        mainPanel.add(dg, BorderLayout.CENTER); 
        setVisible(true);

    }

    private JPanel createButtonPanel() {
        JPanel gridBtn = new JPanel();
        gridBtn.setLayout(new GridLayout(1, 2));

        JButton lancer = new JButton("Lancer");
        lancer.addActionListener(new LancerFFListener(c, combobox1, combobox2, checkBox1, fireNumber, probability, windForce, qProba, frame, sim));

        
        

        JButton fermer = new JButton("Quitter");
        fermer.addActionListener(e -> {
            dispose();
        });

        gridBtn.add(lancer);
        gridBtn.add(fermer);

        return gridBtn;
    }

    private JPanel createOptionsPanel() {
        JPanel optionsPanel = new JPanel(new GridBagLayout());
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, 0, 5, 5);
        optionsPanel.add(l1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        optionsPanel.add(combobox1, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        optionsPanel.add(l2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        percentageField = new JTextField(10); 
        percentageField.setText("0");

        optionsPanel.add(percentageField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        optionsPanel.add(new JLabel("%"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        JButton genererPourc = new JButton("Générer");
        optionsPanel.add(genererPourc, gbc);
       genererPourc.addActionListener(new GenererFFListener(percentageField, frame));

        gbc.gridx = 0;
        gbc.gridy = 2;
        optionsPanel.add(l3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        fireNumber = new JTextField(10); 
        fireNumber.setText("0");

        optionsPanel.add(fireNumber, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        optionsPanel.add(l4, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        optionsPanel.add(checkBox1, gbc); 

        gbc.gridx = 2;
        gbc.gridy = 3;
        optionsPanel.add(checkBox2, gbc); 

        gbc.gridx = 0;
        gbc.gridy = 4;
        optionsPanel.add(l5, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        probability = new JTextField(10); 
        probability.setText("0");
        optionsPanel.add(probability, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        optionsPanel.add(l6, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 5;
        optionsPanel.add(combobox2, gbc); 



        gbc.gridx = 0;
        gbc.gridy = 6;
        optionsPanel.add(l7, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        windForce = new JTextField(10); 
        windForce.setText("0");
        optionsPanel.add(windForce, gbc); 

        gbc.gridx = 0;
        gbc.gridy = 7;
        optionsPanel.add(l8, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        qProba = new JTextField(10); 
        qProba.setText("0");

        optionsPanel.add(qProba, gbc);

        checkBox2.addItemListener(e -> {
            boolean isEnabled = !checkBox2.isSelected(); 
            probability.setEnabled(isEnabled);
            windForce.setEnabled(isEnabled);
            combobox2.setEnabled(isEnabled);
            qProba.setEnabled(isEnabled);
        });

        return optionsPanel;
    }

	@Override
	public DessinGrille getDessin() {
		// TODO Auto-generated method stub
		return dg;
	}

	@Override
	public Simulation getSimu() {
		// TODO Auto-generated method stub
		return sim;
	}

	@Override
	public int getRows() {
		// TODO Auto-generated method stub
		return rows;
	}

	@Override
	public int getCols() {
		// TODO Auto-generated method stub
		return cols;
	}
}
