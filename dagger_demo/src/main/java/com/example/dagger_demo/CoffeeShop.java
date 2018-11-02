package com.example.dagger_demo;

import dagger.Component;

@Component(modules = DripCoffeeModule.class)
interface CoffeeShop {
    CoffeeMaker maker();
}