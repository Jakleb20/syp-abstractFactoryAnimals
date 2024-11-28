package org.example.concreteProducts;

import org.example.abstractProducts.Animal;

public class PetBird implements Animal {
    @Override
    public String getPhoto() {
        return "/images/bird.jpg";
    }

    @Override
    public String getName() {
        return "Vogel";
    }

    @Override
    public String getHabitat() {
        return "Lebt im Haus";
    }

    @Override
    public String getFood() {
        return "Frisst VogelFutter";
    }
}
