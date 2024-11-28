package org.example;

import org.example.abstractProducts.Animal;
import org.example.abstractFactories.AnimalFactory;
import org.example.enums.AnimalCategory;
import org.example.enums.PetCategory;
import org.example.enums.WildCategory;
import org.example.provider.AnimalFactoryProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AnimalFactoryApp {
    private JFrame frame;
    private JComboBox<AnimalCategory> factorySelector;  // Verwende Enum statt String
    private JComboBox<String> animalSelector;
    private JTextArea resultArea;
    private JLabel animalImageLabel;

    public AnimalFactoryApp() {
        frame = new JFrame("Tierfabrik");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);  // Größeres Fenster für bessere Anzeige
        frame.setLayout(new BorderLayout());

        // Factory Selector
        factorySelector = new JComboBox<>(AnimalCategory.values());  // Enum-Werte direkt verwenden
        factorySelector.addActionListener(new FactorySelectionHandler());

        // Animal Selector
        animalSelector = new JComboBox<>();
        updateAnimalSelector(AnimalCategory.HAUSTIERE);  // Start mit Haustieren

        // Result Area
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));

        // Image Label
        animalImageLabel = new JLabel();
        animalImageLabel.setPreferredSize(new Dimension(300, 300));  // Bildbereich vergrößert

        // Layout
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout()); // FlowLayout für horizontale Anordnung
        topPanel.add(new JLabel("Wähle eine Fabrik:"));
        topPanel.add(factorySelector);
        topPanel.add(new JLabel("Wähle ein Tier:")); // Label für Tiere
        topPanel.add(animalSelector); // Tier-Auswahl-ComboBox hinzufügen

        JButton createButton = new JButton("Tier anzeigen");
        createButton.addActionListener(new AnimalCreationHandler());

        // Result Panel: JTextArea und Bild nebeneinander anzeigen
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());

        // ScrollPane für JTextArea
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(450, 250)); // Vergrößerte Textbereich

        resultPanel.add(scrollPane, BorderLayout.CENTER);
        resultPanel.add(animalImageLabel, BorderLayout.EAST);

        // Hinzufügen der Panels
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(createButton, BorderLayout.SOUTH);
        frame.add(resultPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void updateAnimalSelector(AnimalCategory category) {
        animalSelector.removeAllItems();  // Entfernt alle bisherigen Einträge

        if (category == AnimalCategory.HAUSTIERE) {
            // Alle Haustiere aus dem PetType Enum hinzufügen
            for (PetCategory pet : PetCategory.values()) {
                animalSelector.addItem(pet.name());  // Enum-Wert als String hinzufügen
            }
        } else if (category == AnimalCategory.WILDTIERE) {
            // Alle Wildtiere aus dem WildType Enum hinzufügen
            for (WildCategory wild : WildCategory.values()) {
                animalSelector.addItem(wild.name());  // Enum-Wert als String hinzufügen
            }
        }
    }

    private class FactorySelectionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AnimalCategory selectedCategory = (AnimalCategory) factorySelector.getSelectedItem();  // Enum verwenden
            updateAnimalSelector(selectedCategory);  // Update die Tierauswahl basierend auf der Kategorie
        }
    }

    private class AnimalCreationHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AnimalCategory selectedCategory = (AnimalCategory) factorySelector.getSelectedItem();  // Enum verwenden
            String animalType = (String) animalSelector.getSelectedItem();

            AnimalFactory factory = AnimalFactoryProvider.getFactory(selectedCategory);  // Enum-Wert als String übergeben

            try {
                Animal animal;
                if (selectedCategory == AnimalCategory.HAUSTIERE) {
                    animal = factory.createAnimal(PetCategory.valueOf(animalType).name().toLowerCase());
                } else {
                    animal = factory.createAnimal(WildCategory.valueOf(animalType).name().toLowerCase());
                }

                resultArea.setText("Tier: " + animal.getName() +
                        "\nLebensraum: " + animal.getHabitat() +
                        "\nNahrung: " + animal.getFood());

                // Bild laden und anzeigen
                ImageIcon animalImage = new ImageIcon(new File("src" + animal.getPhoto()).getAbsolutePath());
                Image img = animalImage.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH); // Bild skalieren
                animalImageLabel.setIcon(new ImageIcon(img));

            } catch (IllegalArgumentException ex) {
                resultArea.setText(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new AnimalFactoryApp();
    }
}
