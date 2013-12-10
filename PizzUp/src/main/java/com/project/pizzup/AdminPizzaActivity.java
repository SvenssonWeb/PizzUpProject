package com.project.pizzup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.project.pizzup.Objects.Pizzeria;

import java.util.List;

/**
 * Created by Hilda on 2013-12-03.
 */
public class AdminPizzaActivity extends Activity implements View.OnClickListener {
    DataBaseHelper db;
    TextView pizza;
    ListView pizzeria;
    List<Pizzeria> restaurants;
    //CheckBox ingredient;

    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pizza);
        db = MainActivity.myDbHelper;

        restaurants = db.getAllRestaurants();
        pizzeria = (ListView) findViewById(R.id.pizzaAddPizzeria);

        pizza = (TextView) findViewById(R.id.pizzaAddName);

        addBtn = (Button) findViewById(R.id.admin_addPizza_btn);
        addBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == addBtn) {
            pizza.getText().toString();

            //db.createPizza();
            finish();
        }
    }
}
