package org.example.concreteFactories;

import org.example.concreteProducts.PetCat;
import org.example.concreteProducts.PetDog;
import org.example.abstractProducts.Animal;
import org.example.abstractFactories.AnimalFactory;

public class PetFactory implements AnimalFactory {
    @Override
    public Animal createAnimal(String type) {
        switch (type.toLowerCase()) {
            case "dog":
                return new PetDog();
            case "cat":
                return new PetCat();
            default:
                throw new IllegalArgumentException("Haustier nicht verfügbar: " + type);
        }
    }
}
