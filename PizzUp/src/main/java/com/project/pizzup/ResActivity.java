package com.project.pizzup;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.project.pizzup.Objects.MenuAdapter;
import com.project.pizzup.Objects.Pizza;
import com.project.pizzup.Objects.Pizzeria;
import com.project.pizzup.Objects.Sorting;

import java.util.List;

public class ResActivity extends Activity implements View.OnClickListener, RatingBar.OnRatingBarChangeListener, MenuItem.OnMenuItemClickListener {

    public final static String COM_PROJECT_PIZZUP_MESSAGE_PIZZA = "com.project.pizzup.MESSAGE.PIZZA";
    public final static String COM_PROJECT_PIZZUP_MESSAGE_PIZZERIA = "com.project.pizzup.MESSAGE.PIZZERIA";
    ListView listView;
    DataBaseHelper myDbHelper;
	Pizzeria pizzeria;
	List<Pizza> pizzas;
	TextView itemName;
	TextView itemAdress;
	TextView itemPhone;
	RatingBar ratingBar;
	MenuAdapter menuAdapter;
    MenuItem ratingItem;
    MenuItem priceItem;
    MenuItem nameItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
	    myDbHelper = MainActivity.myDbHelper;

	    itemName = (TextView) findViewById(R.id.resTitle);
	    itemAdress = (TextView) findViewById(R.id.resAdress);
	    itemPhone = (TextView) findViewById(R.id.resPhone);
	    ratingBar = (RatingBar) findViewById(R.id.resRatingBar);

	    Bundle data = getIntent().getExtras();
	    assert data != null;
	    pizzeria = data.getParcelable(MainActivity.EXTRA_MESSAGE);

	    Log.i("pizz",pizzeria.id+"");

	    pizzas = myDbHelper.getAllPizzas(pizzeria.id, Sorting.NAME);

	    itemName.setText(pizzeria.name);
	    itemAdress.setText(pizzeria.address);
	    itemAdress.setOnClickListener(this);
	    itemPhone.setText(pizzeria.phone);
	    itemPhone.setOnClickListener(this);
	    ratingBar.setRating(pizzeria.rating);
	    ratingBar.setOnRatingBarChangeListener(this);

	    menuAdapter = new MenuAdapter(this, R.layout.pizza_list_item, pizzas);

	    listView = (ListView) findViewById(R.id.pizzaMenu);
        listView.setAdapter(menuAdapter);

        menuAdapter.notifyDataSetChanged();

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item value
                Pizza item = (Pizza) listView.getItemAtPosition(position);

                // Show Alert
                assert item != null;
                toPizza(pizzeria, item);
            }

        });
    }

	public void onResume(){
		super.onResume();
		menuAdapter.clear();
		menuAdapter.addAll(myDbHelper.getAllPizzas(pizzeria.id, Sorting.NAME));
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.res, menu);

        ratingItem = menu.getItem(1);
        priceItem = menu.getItem(2);
        nameItem = menu.getItem(3);
        ratingItem.setOnMenuItemClickListener(this);
        priceItem.setOnMenuItemClickListener(this);
        nameItem.setOnMenuItemClickListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_admin:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void toPizza(Pizzeria pizzeria, Pizza pizza){
        Intent intent = new Intent(this, PizzaActivity.class);
        intent.putExtra(COM_PROJECT_PIZZUP_MESSAGE_PIZZA, pizza);
        intent.putExtra(COM_PROJECT_PIZZUP_MESSAGE_PIZZERIA, pizzeria);
        startActivity(intent);
    }

	@Override
	public void onClick(View v) {
		String map = "http://maps.google.co.in/maps?q=" + pizzeria.address;

		Intent i = null;

		if (v.equals(itemPhone)){
			i = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + pizzeria.phone));
		} else if (v.equals(itemAdress)){
			i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
        }
		if (i != null){
			startActivity(i);
		}
//        myDbHelper.getAllPizzas(pizzeria.id, "rating");
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.equals(ratingItem)){
            pizzas = myDbHelper.getAllPizzas(pizzeria.id, Sorting.RATING);
        } else if (menuItem.equals(priceItem)){
            pizzas = myDbHelper.getAllPizzas(pizzeria.id, Sorting.PRICE);
        } else if (menuItem.equals(nameItem)) {
            pizzas = myDbHelper.getAllPizzas(pizzeria.id, Sorting.NAME);
        } else {
           return false;
        }

        menuAdapter = new MenuAdapter(this, R.layout.pizza_list_item, pizzas);
        listView.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();

        return true;
    }

	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
		int temp = myDbHelper.setRestaurantRating(pizzeria.id, rating);
		Log.i("pizz", "Database returnvalue: "+temp);
	}
}
