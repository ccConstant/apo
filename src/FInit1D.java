import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FInit1D extends JFrame implements FInit {
    static int HAUTEUR = 600;
    final static int LARGEUR = 900;

    static JLabel l1, l2;
    static JTextField ruleField;
    static JTable table;
    private Controller c;
    private int rows;
    private int cols;
    private Simulation sim;
    private DessinGrille dg;
    private int gridWidth;
    private int gridHeight;

    public FInit1D(Controller c, int cols, int rows) {
        this.c = c;
        this.rows = rows;
        this.cols = cols;

        this.gridWidth = 400;
        this.gridHeight = 400;

        setTitle("Paramètres: Automate feu de forêt");
        setSize(LARGEUR, HAUTEUR);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        sim = new SimulationLifeGame();
        ((SimulationLifeGame) sim).init_simulation(rows, cols);

        l1 = new JLabel("Saisir règle: ");
        ruleField = new JTextField(10);
        ruleField.setText("0");
        l2 = new JLabel("(cliquer sur Entrer pour générer le tableau)");

        ruleField.addActionListener(e -> generateTableData());

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createOptionsPanel(), BorderLayout.NORTH);
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);
        getContentPane().add(mainPanel, BorderLayout.CENTER); 

        dg = new DessinGrille(rows, cols, gridWidth, gridHeight, sim.getAutomate());
        dg.setFocusable(true);
        dg.addMouseListener(new ClickIniListener(c));
        mainPanel.add(dg, BorderLayout.CENTER); 

        setLocationRelativeTo(null);
        generateTableData();
        setVisible(true);
    }

    private JPanel createButtonPanel() {
        JPanel gridBtn = new JPanel();
        gridBtn.setLayout(new GridLayout(1, 2));

        JButton lancer = new JButton("Lancer");
        lancer.addActionListener(e -> generateTableData());

        JButton fermer = new JButton("Quitter");
        fermer.addActionListener(e -> dispose());

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
        optionsPanel.add(l1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        optionsPanel.add(ruleField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        optionsPanel.add(l2, gbc);

        String[] columnNames = {"Configuration de voisinage", "111", "110", "101", "100", "011", "010", "001", "000"};
        DefaultTableModel model = new DefaultTableModel(new Object[][]{{"Nouvelle valeur", "", "", "", "", "", "", "", ""}}, columnNames);
        table = new JTable(model);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(400);

        JScrollPane scrollPane = new JScrollPane(table); 
        scrollPane.setPreferredSize(new Dimension(600, 300)); 

        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.gridwidth = 2;
        optionsPanel.add(scrollPane, gbc); 
        return optionsPanel;
    }

    private static void generateTableData() {
        String rule = ruleField.getText();
        int ruleDecimal = Integer.parseInt(rule);
        String ruleBinary = String.format("%8s", Integer.toBinaryString(ruleDecimal)).replace(' ', '0');

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] rowData = model.getDataVector().get(0).toArray();

        for (int i = 0; i < 8; i++) {
            model.setValueAt(Character.getNumericValue(ruleBinary.charAt(i)), 0, i + 1);
        }

        table.setModel(model);
    }

    public DessinGrille getDessin() {
        return dg;
    }

    public Simulation getSimu() {
        return sim;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
