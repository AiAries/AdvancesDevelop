package com.example.dagger_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
//    @Inject
//     Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView age = findViewById(R.id.age);
        CoffeeShop coffeeShop = DaggerCoffeeShop.create();
        coffeeShop.maker().brew();
//        age.setText(person.age);

    }
}
