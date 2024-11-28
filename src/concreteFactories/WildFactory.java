package org.example.concreteFactories;

import org.example.concreteProducts.WildElephant;
import org.example.concreteProducts.WildLion;
import org.example.abstractProducts.Animal;
import org.example.abstractFactories.AnimalFactory;

public class WildFactory implements AnimalFactory {
    @Override
    public Animal createAnimal(String type) {
        switch (type.toLowerCase()) {
            case "lion":
                return new WildLion();
            case "elephant":
                return new WildElephant();
            default:
                throw new IllegalArgumentException("Wildtier nicht verfügbar: " + type);
        }
    }
}
