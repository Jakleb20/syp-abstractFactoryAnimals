package org.example.abstractFactories;

import org.example.abstractProducts.Animal;

public interface AnimalFactory {
    Animal createAnimal(String type);
}
