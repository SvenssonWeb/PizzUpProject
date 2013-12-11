package com.project.pizzup;

import android.app.Activity;
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
 * Created by cissi on 2013-12-11.
 */
public class AdminPizzaResActivity extends Activity{
    DataBaseHelper db;
    ListView pizzeria;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pizza_res);
        db = MainActivity.myDbHelper;

        pizzeria = (ListView) findViewById(R.id.pizzaAddPizzeria);
        List<Pizzeria> restaurants = db.getAllRestaurants();
        ArrayAdapter<Pizzeria> adapter = new ArrayAdapter<Pizzeria>(this, android.R.layout.simple_list_item_1, restaurants);
        pizzeria.setAdapter(adapter);

        saveBtn = (Button) findViewById(R.id.saveBtn);

    }
}
