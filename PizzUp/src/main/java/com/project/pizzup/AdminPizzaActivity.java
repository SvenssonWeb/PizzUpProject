package com.project.pizzup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.project.pizzup.Objects.Ingredient;
import com.project.pizzup.Objects.Pizzeria;

import java.util.List;

/**
 * Created by Hilda on 2013-12-03.
 */
public class AdminPizzaActivity extends Activity implements View.OnClickListener {
    DataBaseHelper db;
    EditText pizza;
    EditText price;
    ListView pizzeria;
    ListView ingredients;

    Button openIngred;
    Button openRes;

    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pizza);
        db = MainActivity.myDbHelper;

        pizzeria = (ListView) findViewById(R.id.pizzaAddPizzeria);
        List<Pizzeria> restaurants = db.getAllRestaurants();
        ArrayAdapter<Pizzeria> adapter = new ArrayAdapter<Pizzeria>(this, android.R.layout.simple_list_item_1, restaurants);
        pizzeria.setAdapter(adapter);

        ingredients = (ListView) findViewById(R.id.pizzaAddIngredient);
        List<Ingredient> ingredient = db.getAllIngredients();
        ArrayAdapter<Ingredient> adapter2 = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_multiple_choice, ingredient);
        ingredients.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ingredients.setAdapter(adapter2);

        pizza = (EditText) findViewById(R.id.pizzaAddName);
        price = (EditText) findViewById(R.id.pizzaAddPrice);

        addBtn = (Button) findViewById(R.id.admin_addPizza_btn);
        openIngred = (Button) findViewById(R.id.admin_addIngred_btn);
        openRes = (Button) findViewById(R.id.admin_addPizzeria_btn);

        addBtn.setOnClickListener(this);
        openIngred.setOnClickListener(this);
        openRes.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v == openRes) {
            Intent myIntent = new Intent(this, AdminPizzaResActivity.class);
            this.startActivity(myIntent);
        }
        if (v == openIngred) {
            Intent myIntent = new Intent(this, AdminPizzaIngredActivity.class);
            this.startActivity(myIntent);
        }
        /*if (v == addBtn) {
            pizza.getText().toString();
            price.getText();


            //db.createPizza();
            finish();
        }*/
    }
}
