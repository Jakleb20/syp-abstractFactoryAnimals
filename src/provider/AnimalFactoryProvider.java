package org.example.provider;

import org.example.abstractFactories.AnimalFactory;
import org.example.concreteFactories.PetFactory;
import org.example.concreteFactories.WildFactory;
import org.example.enums.AnimalCategory;  // Import der Enum-Klasse für die Kategorien

public class AnimalFactoryProvider {

    public static AnimalFactory getFactory(AnimalCategory category) {
        switch (category) {
            case HAUSTIERE:
                return new PetFactory();
            case WILDTIERE:
                return new WildFactory();
            default:
                throw new IllegalArgumentException("Unbekannte Fabrik: " + category);
        }
    }
}
