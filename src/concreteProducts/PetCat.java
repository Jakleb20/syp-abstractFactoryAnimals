package org.example.concreteProducts;

import org.example.abstractProducts.Animal;

public class PetCat implements Animal {
    @Override
    public String getPhoto() {
        return "/images/cat.jpg";
    }

    @Override
    public String getName() {
        return "Katze";
    }

    @Override
    public String getHabitat() {
        return "Lebt im Haus";
    }

    @Override
    public String getFood() {
        return "Frisst Katzenfutter";
    }
}
