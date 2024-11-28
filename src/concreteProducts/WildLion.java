package org.example.concreteProducts;

import org.example.abstractProducts.Animal;

public class WildLion implements Animal {

    @Override
    public String getPhoto() {
        return "/images/lion.jpg";
    }
    @Override
    public String getName() {
        return "Löwe";
    }

    @Override
    public String getHabitat() {
        return "Lebt in der Savanne";
    }

    @Override
    public String getFood() {
        return "Frisst Fleisch";
    }
}
