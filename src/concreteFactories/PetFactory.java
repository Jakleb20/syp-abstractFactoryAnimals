package org.example.concreteFactories;

import org.example.concreteProducts.PetBird;
import org.example.concreteProducts.PetCat;
import org.example.concreteProducts.PetDog;
import org.example.abstractProducts.Animal;
import org.example.abstractFactories.AnimalFactory;

public class PetFactory implements AnimalFactory {
    @Override
    public Animal createAnimal(String type) {
        switch (type.toLowerCase()) {
            case "hund":
                return new PetDog();
            case "katze":
                return new PetCat();
            case "vogel":
                return new PetBird();    
            default:
                throw new IllegalArgumentException("Haustier nicht verfügbar: " + type);
        }
    }
}
