package org.example.concreteProducts;

import org.example.abstractProducts.Animal;

public class PetDog implements Animal {
    @Override
    public String getPhoto() {
        return "/images/dog.jpg";
    }
    
    @Override
    public String getName() {
        return "Hund";
    }

    @Override
    public String getHabitat() {
        return "Lebt im Haus";
    }

    @Override
    public String getFood() {
        return "Frisst Hundefutter";
    }
}
