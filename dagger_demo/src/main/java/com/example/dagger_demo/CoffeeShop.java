package com.example.dagger_demo;

import dagger.Component;

@Component(modules = {DripCoffeeModule.class,FruitModule.class})
interface CoffeeShop {
    CoffeeMaker maker();
}