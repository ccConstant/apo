import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FInitForestFire extends JFrame {
    static int HAUTEUR = 600;
    final static int LARGEUR = 500;

    static JComboBox<Integer> combobox1;
    static JComboBox<String> combobox2;
    static JCheckBox checkBox1, checkBox2;
    static JTextField percentageField, forestNumber, probability, wind, windForce, qProba;

    static JLabel l1, l2, l3, l4, l5, l6, l7, l8;

    public FInitForestFire() {
        setTitle("Paramètres: Automate feu de forêt");
        setSize(LARGEUR, HAUTEUR);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        Integer s[] = {4, 6, 8};
        combobox1 = new JComboBox<>(s);
        combobox1.setPreferredSize(new Dimension(100, 20));

        String v[] = {"Nord", "Sud", "Est", "Ouest"};
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
    }

    private JPanel createButtonPanel() {
        JPanel gridBtn = new JPanel();
        gridBtn.setLayout(new GridLayout(1, 2));

        JButton lancer = new JButton("Lancer");
        lancer.addActionListener(e -> {
        });

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
        optionsPanel.add(percentageField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        optionsPanel.add(new JLabel("%"), gbc);

        gbc.gridx = 3;
        gbc.gridy = 1;
        JButton genererPourc = new JButton("Générer");
        optionsPanel.add(genererPourc, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        optionsPanel.add(l3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        forestNumber = new JTextField(10); 
        optionsPanel.add(forestNumber, gbc);

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
        optionsPanel.add(probability, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        optionsPanel.add(l6, gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        wind = new JTextField(10); 
        optionsPanel.add(wind, gbc); 

        gbc.gridx = 0;
        gbc.gridy = 6;
        optionsPanel.add(l7, gbc);

        gbc.gridx = 1;
        gbc.gridy = 6;
        optionsPanel.add(combobox2, gbc); 

        gbc.gridx = 0;
        gbc.gridy = 7;
        optionsPanel.add(l8, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        qProba = new JTextField(10); 
        optionsPanel.add(qProba, gbc);

        checkBox2.addItemListener(e -> {
            boolean isEnabled = !checkBox2.isSelected(); 
            probability.setEnabled(isEnabled);
            wind.setEnabled(isEnabled);
            combobox2.setEnabled(isEnabled);
            qProba.setEnabled(isEnabled);
        });

        return optionsPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FInitForestFire fff = new FInitForestFire();
            fff.setVisible(true);
        });
    }
}
