package com.project.pizzup;

import android.app.Activity;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

import com.project.pizzup.Objects.Pizza;
import com.project.pizzup.Objects.Pizzeria;

import java.io.IOException;

public class PizzaActivity extends Activity {

	DataBaseHelper myDbHelper;
	Pizzeria pizzeria;
	Pizza pizza;

	TextView resTitle;
	TextView resPhone;
	TextView resAddress;
	TextView pizzaTitle;
	TextView pizzaPrice;
	TextView pizzaIngretient;
	RatingBar pizzaRating;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);
		setUpDatabase();

		resTitle = (TextView) findViewById(R.id.restaurantNameTxt);
		resPhone = (TextView) findViewById(R.id.restaurantPhoneTxt);
		resAddress = (TextView) findViewById(R.id.restaurantAddressTxt);
		pizzaTitle = (TextView) findViewById(R.id.pizzaNameTxt);
		pizzaPrice = (TextView) findViewById(R.id.pizzaPrizeTxt);
		pizzaIngretient = (TextView) findViewById(R.id.pizzaIngredientsTxt);
		pizzaRating = (RatingBar) findViewById(R.id.pizzaRating);

		Bundle data = getIntent().getExtras();
		assert data != null;
		pizzeria = data.getParcelable(ResActivity.COM_PROJECT_PIZZUP_MESSAGE_PIZZERIA);
		pizza = data.getParcelable(ResActivity.COM_PROJECT_PIZZUP_MESSAGE_PIZZA);
		pizza.ingredients = myDbHelper.getPizzaIngredients(pizza.id);

		resTitle.setText(pizzeria.name);
		resPhone.setText(pizzeria.phone+"");
		resAddress.setText(pizzeria.address);
		pizzaTitle.setText(pizza.name);
		pizzaPrice.setText(pizza.price+"");
		pizzaIngretient.setText(pizza.ingredientsToString());
		pizzaRating.setRating(pizza.rating);

	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pizza, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	public void setUpDatabase(){
		myDbHelper = new DataBaseHelper(this);

		try {
			myDbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}
		try {
			myDbHelper.openDataBase();
		}catch(SQLException sqle){
			throw sqle;
		}
	}
}
