import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class F1D extends JFrame implements ItemListener {
    static int HAUTEUR = 600;
    final static int LARGEUR = 900;

    static JLabel l1;
    static JTextField ruleField;
    static JTable table;

    public F1D() {
        setTitle("Paramètres: Automate feu de forêt");
        setSize(LARGEUR, HAUTEUR);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        l1 = new JLabel("Saisir règle: ");
        ruleField = new JTextField(10);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createOptionsPanel(), BorderLayout.CENTER);
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH);
        getContentPane().add(mainPanel);

        setLocationRelativeTo(null);
    }

    private JPanel createButtonPanel() {
        JPanel gridBtn = new JPanel();
        gridBtn.setLayout(new GridLayout(1, 2));

        JButton lancer = new JButton("Lancer");
        lancer.addActionListener(e -> {
            generateTableData();
        });

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
        gbc.insets = new Insets(0, 0, 5, 5);
        optionsPanel.add(l1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        optionsPanel.add(ruleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span the table across 2 columns
        optionsPanel.add(new JLabel(" "), gbc); // Spacing

        // Create table model with 1 row and 9 columns
        String[] columnNames = {"Configuration de voisinage", "111", "110", "101", "100", "011", "010", "001", "000"};
        DefaultTableModel model = new DefaultTableModel(new Object[][]{{"Nouvelle valeur", "", "", "", "", "", "", "", ""}}, columnNames);
        table = new JTable(model);
        table.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(400); // Adjust width of first column
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span the table across 2 columns
        gbc.gridheight = 1; // Set the height of the table to 1 row
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow the table to expand horizontally
        optionsPanel.add(scrollPane, gbc);
        scrollPane.setPreferredSize(new Dimension(500, 400)); // Set preferred size of scroll pane

        return optionsPanel;
    }

    private static void generateTableData() {
        String rule = ruleField.getText();
        int ruleDecimal = Integer.parseInt(rule);

        // Convert the decimal rule into an 8-bit binary number
        String ruleBinary = String.format("%8s", Integer.toBinaryString(ruleDecimal)).replace(' ', '0');

        // Get the existing data from the first row of the table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] rowData = model.getDataVector().get(0).toArray();

        // Set the binary bits in each column according to the rule
        for (int i = 0; i < 8; i++) {
            model.setValueAt(Character.getNumericValue(ruleBinary.charAt(i)), 0, i + 1);
        }

        // Update the table model
        table.setModel(model);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            F1D f1d = new F1D();
            f1d.setVisible(true);
        });
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // Your itemStateChanged logic here
    }
}
