package com.project.pizzup;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.project.pizzup.Objects.Ingredient;
import com.project.pizzup.Objects.Pizza;
import com.project.pizzup.Objects.Sorting;

import java.util.List;


public class FilterActivity extends Activity implements AdapterView.OnItemSelectedListener {

    Pizza pizza = new Pizza();
    DataBaseHelper myDbHelper;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

        ListView pizzaWithIngredients = (ListView) findViewById(R.id.pizzaIngretient);

        // Ingredient selectedIngredient = pizza.ingredients.get(pos);
        List<Pizza> pizzas = myDbHelper.getAllPizzasWithIngredient(pos);

        ArrayAdapter<Pizza> adapter = (ArrayAdapter<Pizza>)pizzaWithIngredients.getAdapter();

        adapter.clear();

        adapter.addAll(pizzas);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)


        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        myDbHelper = MainActivity.myDbHelper;

        List<Pizza> allPizzas = myDbHelper.getAllPizzas(1, Sorting.NAME);

        pizza.ingredients = myDbHelper.getPizzaIngredients(-1);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<Ingredient> adapter = new ArrayAdapter<Ingredient>(this,
                 android.R.layout.simple_spinner_item, pizza.ingredients);


// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        ListView pizzaWithIngredients = (ListView) findViewById(R.id.pizzaIngretient);

        ArrayAdapter<Pizza> pizzaWithIngredientsAdapter = new ArrayAdapter<Pizza>(this, android.R.layout.simple_spinner_item, allPizzas);

        pizzaWithIngredientsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pizzaWithIngredients.setAdapter(pizzaWithIngredientsAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.filter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
