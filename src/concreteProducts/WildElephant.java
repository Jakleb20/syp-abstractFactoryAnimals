package org.example.concreteProducts;

import org.example.abstractProducts.Animal;

public class WildElephant implements Animal {

    @Override
    public String getPhoto() {
        return "/images/elephant.jpg";
    }
    
    @Override
    public String getName() {
        return "Elefant";
    }

    @Override
    public String getHabitat() {
        return "Lebt in der Savanne";
    }

    @Override
    public String getFood() {
        return "Frisst Pflanzen";
    }
}
