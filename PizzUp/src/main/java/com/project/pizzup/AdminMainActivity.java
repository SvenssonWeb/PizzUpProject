package com.project.pizzup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Hilda on 2013-12-03.
 */
public class AdminMainActivity extends Activity implements View.OnClickListener {

    Button btnAddPizza;
    Button btnAddPizzeria;
    Button btnAddIngredients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        btnAddPizza = (Button) findViewById(R.id.admin_addPizza_btn);
        btnAddPizzeria = (Button) findViewById(R.id.admin_addPizzeria_btn);
        btnAddIngredients = (Button) findViewById(R.id.admin_addIngredients_btn);

        btnAddPizza.setOnClickListener(this);
        btnAddPizzeria.setOnClickListener(this);
        btnAddIngredients.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == btnAddPizza) {
            Intent myIntent = new Intent(this, AdminPizzaActivity.class);
            this.startActivity(myIntent);
        }
        if (v == btnAddIngredients) {
            Intent myIntent = new Intent(this, IngredientAdminActivity.class);
            this.startActivity(myIntent);
        }
        if (v == btnAddPizzeria) {
            Intent myIntent = new Intent(this, AdminPizzeriaActivity.class);
            this.startActivity(myIntent);
        }
    }
}
