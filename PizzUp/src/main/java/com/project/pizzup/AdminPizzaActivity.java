package com.project.pizzup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

        pizza = (EditText) findViewById(R.id.pizzaAddName);
        price = (EditText) findViewById(R.id.pizzaAddPrice);

        addBtn = (Button) findViewById(R.id.admin_addPizza_btn);
        addBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        /*if (v == addBtn) {
            pizza.getText().toString();
            price.getText();


            //db.createPizza();
            finish();
        }*/
    }
}
