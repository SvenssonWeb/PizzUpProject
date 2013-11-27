package com.project.pizzup;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.project.pizzup.Objects.MenuAdapter;
import com.project.pizzup.Objects.Pizza;
import com.project.pizzup.Objects.Pizzeria;

import java.io.IOException;
import java.util.List;

public class ResActivity extends Activity implements View.OnClickListener {

    ListView listView;
    DataBaseHelper myDbHelper;
	Pizzeria pizzeria;
	List<Pizza> pizzas;
	TextView itemName;
	TextView itemAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
	    itemName = (TextView) findViewById(R.id.resTitel);
	    itemAdress = (TextView) findViewById(R.id.resAdress);
	    setUpDatabase();

	    Intent intent = getIntent();
	    int resId = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, -1);
	    Log.i("pizz",resId+"");
	    pizzas = myDbHelper.getAllPizzas(resId);
	    pizzeria = myDbHelper.getRestaurant(resId);

	    itemName.setText(pizzeria.name);
	    itemAdress.setText(pizzeria.address);
	    itemAdress.setOnClickListener(this);

	    ArrayAdapter<Pizza> adapter = new ArrayAdapter<Pizza>(this,
			    android.R.layout.simple_list_item_1, android.R.id.text1, pizzas);

	    MenuAdapter menuAdapter = new MenuAdapter(this, R.layout.pizza_list_item, pizzas);

	    listView = (ListView) findViewById(R.id.pizzaMenu);
        //ArrayAdapter adapter = new ArrayAdapter<Pizza>(this,R.layout.pizza_list_item);
        listView.setAdapter(menuAdapter);
        menuAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.res, menu);
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
	@Override
	public void onClick(View v) {
		String map = "http://maps.google.co.in/maps?q=" + pizzeria.address;

		Intent i;
		i = new Intent(Intent.ACTION_VIEW, Uri.parse(map));
		if (v.equals(null)){
			i = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:123"));
		}
		startActivity(i);
	}
}
