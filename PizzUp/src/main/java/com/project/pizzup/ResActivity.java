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

	    itemName = (TextView) findViewById(R.id.resTitel);
	    itemAdress = (TextView) findViewById(R.id.resAdress);
	    ratingBar = (RatingBar) findViewById(R.id.resRatingBar);

	    Bundle data = getIntent().getExtras();
	    assert data != null;
	    pizzeria = data.getParcelable(MainActivity.EXTRA_MESSAGE);

	    Log.i("pizz",pizzeria.id+"");

	    pizzas = myDbHelper.getAllPizzas(pizzeria.id, null);

	    itemName.setText(pizzeria.name);
	    itemAdress.setText(pizzeria.address);
	    itemAdress.setOnClickListener(this);
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
		menuAdapter.addAll(myDbHelper.getAllPizzas(pizzeria.id, null));
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.res, menu);

        ratingItem = menu.getItem(1);
        priceItem = menu.getItem(2);
        nameItem = menu.getItem(3);
        //ratingItem = (MenuItem) findViewById(R.id.action_rating);
        ratingItem.setOnMenuItemClickListener(this);
        //priceItem = (MenuItem) findViewById(R.id.action_price);
        priceItem.setOnMenuItemClickListener(this);
        //nameItem = (MenuItem) findViewById(R.id.action_name);
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

		Intent i;
		i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
		if (v.equals(null)){
			i = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:123"));
		} else if (v.equals(itemAdress)){

        }
		startActivity(i);

//        myDbHelper.getAllPizzas(pizzeria.id, "rating");

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.equals(ratingItem)){
            myDbHelper.getAllPizzas(pizzeria.id, "rating");
        } else if (menuItem.equals(priceItem)){
            myDbHelper.getAllPizzas(pizzeria.id, "price");
        } else if (menuItem.equals(nameItem)) {
            myDbHelper.getAllPizzas(pizzeria.id, "name");
        }
        return false;
    }

	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
		int temp = myDbHelper.setRestaurantRating(pizzeria.id, rating);
		Log.i("pizz", "Database returnvalue: "+temp);
	}
}
