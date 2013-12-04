package com.project.pizzup;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

import com.project.pizzup.Objects.Pizza;
import com.project.pizzup.Objects.Pizzeria;

public class PizzaActivity extends Activity implements RatingBar.OnRatingBarChangeListener {

	DataBaseHelper myDbHelper;
	Pizzeria pizzeria;
	Pizza pizza;

	TextView resTitle;
	TextView resPhone;
	TextView resAddress;
	TextView pizzaTitle;
	TextView pizzaPrice;
	TextView pizzaIngredient;
	RatingBar pizzaRating;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);
		myDbHelper = MainActivity.myDbHelper;

		resTitle = (TextView) findViewById(R.id.restaurantNameTxt);
		resPhone = (TextView) findViewById(R.id.restaurantPhoneTxt);
		resAddress = (TextView) findViewById(R.id.restaurantAddressTxt);
		pizzaTitle = (TextView) findViewById(R.id.pizzaNameTxt);
		pizzaPrice = (TextView) findViewById(R.id.pizzaPrizeTxt);
		pizzaIngredient = (TextView) findViewById(R.id.pizzaIngredientsTxt);
		pizzaRating = (RatingBar) findViewById(R.id.pizzaRating);
		pizzaRating.setOnRatingBarChangeListener(this);

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
		pizzaIngredient.setText(pizza.ingredientsToString());
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
        if (id == R.id.action_admin) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
		int temp = myDbHelper.setPizzaRating(pizza.id, rating);
		Log.i("pizz", "Database returnvalue: " + temp);
	}
}
