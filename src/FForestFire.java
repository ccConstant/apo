import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


public class FForestFire extends JFrame implements ItemListener {
    static int HAUTEUR = 600;
    final static int LARGEUR = 500;

    static JComboBox<Integer> combobox1;
    static JComboBox<String> combobox2;
    static JCheckBox checkBox1, checkBox2;
    static JTextField percentageField, forestNumber, probability, wind, windForce, qProba;


    static JLabel l1, l2, l3, l4, l5, l6, l7, l8;

    public FForestFire() {
        setTitle("Paramètres: Automate feu de forêt");
        setSize(LARGEUR, HAUTEUR);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        getContentPane().setLayout(new BorderLayout());

        Integer s[] = {4, 6, 8};

        // Créer une case à cocher
        combobox1 = new JComboBox<>(s);
        combobox1.setPreferredSize(new Dimension(100, 20)); // Set preferred size

        // Ajouter ItemListener
        combobox1.addItemListener(this);
        
        String v[] = {"Nord", "Sud", "Est", "Ouest"};

        // Créer une case à cocher
        combobox2 = new JComboBox<>(v);
        combobox2.setPreferredSize(new Dimension(100, 20)); // Set preferred size

        // Ajouter ItemListener
        combobox2.addItemListener(this);
        

        // Créer des étiquettes
        l1 = new JLabel("Nombre de voisins: ");

        l2 = new JLabel("Pourcentage de forêt: ");

        l3 = new JLabel("Nombre de feu de départ: ");

        l4 = new JLabel("Probabiliste: ");

        checkBox1 = new JCheckBox("Oui");

        checkBox2 = new JCheckBox("Non");


        l5 = new JLabel("Probabilité: ");

        l6 = new JLabel("Vent: ");

        l7 = new JLabel("Force du vent : ");
        
        l8 = new JLabel("Probabilité de Q: ");


        // Ajout des composants au panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createOptionsPanel(), BorderLayout.CENTER);
        mainPanel.add(createButtonPanel(), BorderLayout.SOUTH); // Add button panel to the bottom
        getContentPane().add(mainPanel);

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    private JPanel createButtonPanel() {
        JPanel gridBtn = new JPanel();
        gridBtn.setLayout(new GridLayout(1, 2));

        JButton lancer = new JButton("Lancer");
        lancer.addActionListener(e -> {
            // Action performed when "Lancer" button is clicked
        });

        JButton fermer = new JButton("Quitter");
        fermer.addActionListener(e -> {
            // Action performed when "Quitter" button is clicked
            dispose(); // Close the frame
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
        optionsPanel.add(new JLabel(" "), gbc); // Spacing

        gbc.gridx = 0;
        gbc.gridy = 2;
        optionsPanel.add(l2, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        JTextField percentageField = new JTextField(10);
        optionsPanel.add(percentageField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        optionsPanel.add(new JLabel("%"), gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 2;
        JButton genererPourc = new JButton("Générer");
        optionsPanel.add(genererPourc, gbc) ;
        //genererPourc.addActionListener(e -> {};


        gbc.gridx = 0;
        gbc.gridy = 3;
        optionsPanel.add(new JLabel(" "), gbc); // Spacing

        gbc.gridx = 0;
        gbc.gridy = 4;
        optionsPanel.add(l3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        JTextField forestNumber = new JTextField(10);
        optionsPanel.add(forestNumber, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        optionsPanel.add(new JLabel(" "), gbc); // Spacing

        gbc.gridx = 0;
        gbc.gridy = 6;
        optionsPanel.add(l4, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        optionsPanel.add(checkBox1, gbc); // Add checkbox

        gbc.gridx = 2;
        gbc.gridy = 6;
        optionsPanel.add(checkBox2, gbc); // Add checkbox

        gbc.gridx = 0;
        gbc.gridy = 7;
        optionsPanel.add(new JLabel(" "), gbc); // Spacing

        gbc.gridx = 0;
        gbc.gridy = 8;
        optionsPanel.add(l5, gbc);

        gbc.gridx = 1;
        gbc.gridy = 8;
        JTextField probability = new JTextField(10);
        optionsPanel.add(probability, gbc);

        gbc.gridx = 0;
        gbc.gridy = 9;
        optionsPanel.add(new JLabel(" "), gbc); // Spacing

        gbc.gridx = 0;
        gbc.gridy = 10;
        optionsPanel.add(l6, gbc);

        gbc.gridx = 1;
        gbc.gridy = 10;
        JTextField wind = new JTextField(10);
        optionsPanel.add(wind, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        optionsPanel.add(new JLabel(" "), gbc); // Spacing

        gbc.gridx = 0;
        gbc.gridy = 12;
        optionsPanel.add(l7, gbc);

        gbc.gridx = 1;
        gbc.gridy = 12;
        optionsPanel.add(combobox2, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 13;
        optionsPanel.add(new JLabel(" "), gbc); // Spacing

        gbc.gridx = 0;
        gbc.gridy = 14;
        optionsPanel.add(l8, gbc);

        gbc.gridx = 1;
        gbc.gridy = 14;
        JTextField qProba = new JTextField(10);
        optionsPanel.add(qProba, gbc);
        

        // Add an item listener to the checkBox2
        checkBox2.addItemListener(e -> {
            boolean isEnabled = !checkBox2.isSelected(); // If "Non" checkbox is not selected, enable text fields
            probability.setEnabled(isEnabled);
            wind.setEnabled(isEnabled);
            combobox2.setEnabled(isEnabled);
            qProba.setEnabled(isEnabled);

            
        });

        return optionsPanel;
    }

    public int getSelectedNeighbors() {
        return (Integer) combobox1.getSelectedItem();
    }

    public int getPercentageForest() {
        try {
            return Integer.parseInt(percentageField.getText());
        } catch (NumberFormatException e) {
            return 0; // Return default value if parsing fails
        }
    }

    public int getFireStartNumber() {
        try {
            return Integer.parseInt(forestNumber.getText());
        } catch (NumberFormatException e) {
            return 0; // Return default value if parsing fails
        }
    }

    public boolean isProbabilistic() {
        return checkBox1.isSelected();
    }

    public String getWind() {
        return wind.getText();
    }

    public int getWindForce() {
        try {
            return Integer.parseInt(windForce.getText());
        } catch (NumberFormatException e) {
            return 0; // Return default value if parsing fails
        }
    }
    
    public double getProbability() {
        try {
            double probabilityValue = Double.parseDouble(probability.getText());
            if (probabilityValue < 0 || probabilityValue > 1) {
                throw new NumberFormatException();
            }
            return probabilityValue;
        } catch (NumberFormatException e) {
            return 0.0; // Return default value if parsing fails or value is out of range
        }
    }

    public double getQProbability() {
        try {
            double qProbabilityValue = Double.parseDouble(qProba.getText());
            if (qProbabilityValue < 0 || qProbabilityValue > 1) {
                throw new NumberFormatException();
            }
            return qProbabilityValue;
        } catch (NumberFormatException e) {
            return 0.0; // Return default value if parsing fails or value is out of range
        }
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FForestFire fff = new FForestFire();
            fff.setVisible(true);
        });
    }
    

    @Override
    public void itemStateChanged(ItemEvent e) {
        // Your itemStateChanged logic here
    }
}
