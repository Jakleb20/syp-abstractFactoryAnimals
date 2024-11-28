package org.example;

import org.example.concreteFactories.PetFactory;
import org.example.concreteFactories.WildFactory;
import org.example.abstractProducts.Animal;
import org.example.abstractFactories.AnimalFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnimalFactoryApp {
    private JFrame frame;
    private JComboBox<String> factorySelector;
    private JComboBox<String> animalSelector;
    private JTextArea resultArea;

    private AnimalFactory petFactory = new PetFactory();
    private AnimalFactory wildFactory = new WildFactory();

    public AnimalFactoryApp() {
        frame = new JFrame("Tierfabrik");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Factory Selector
        factorySelector = new JComboBox<>(new String[]{"Haustiere", "Wildtiere"});
        factorySelector.addActionListener(new FactorySelectionHandler());

        // Animal Selector
        animalSelector = new JComboBox<>();
        updateAnimalSelector("Haustiere");

        // Result Area
        resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Layout
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Wähle eine Fabrik:"));
        topPanel.add(factorySelector);

        JPanel middlePanel = new JPanel();
        middlePanel.add(new JLabel("Wähle ein Tier:"));
        middlePanel.add(animalSelector);

        JButton createButton = new JButton("Tier anzeigen");
        createButton.addActionListener(new AnimalCreationHandler());

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(middlePanel, BorderLayout.CENTER);
        frame.add(createButton, BorderLayout.SOUTH);
        frame.add(new JScrollPane(resultArea), BorderLayout.EAST);

        frame.setVisible(true);
    }

    private void updateAnimalSelector(String factory) {
        animalSelector.removeAllItems();
        if (factory.equals("Haustiere")) {
            animalSelector.addItem("dog");
            animalSelector.addItem("cat");
        } else if (factory.equals("Wildtiere")) {
            animalSelector.addItem("lion");
            animalSelector.addItem("elephant");
        }
    }

    private class FactorySelectionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedFactory = (String) factorySelector.getSelectedItem();
            updateAnimalSelector(selectedFactory);
        }
    }

    private class AnimalCreationHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String factoryType = (String) factorySelector.getSelectedItem();
            String animalType = (String) animalSelector.getSelectedItem();

            AnimalFactory factory = factoryType.equals("Haustiere") ? petFactory : wildFactory;
            try {
                Animal animal = factory.createAnimal(animalType.toLowerCase());
                resultArea.setText("Tier: " + animal.getName() +
                        "\nLebensraum: " + animal.getHabitat() +
                        "\nNahrung: " + animal.getFood());
            } catch (IllegalArgumentException ex) {
                resultArea.setText(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new AnimalFactoryApp();
    }
}
