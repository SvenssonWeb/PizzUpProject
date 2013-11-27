package com.project.pizzup;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.project.pizzup.Objects.Pizza;
import com.project.pizzup.Objects.Pizzeria;

import java.io.IOException;
import java.util.List;


public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.project.pizzup.MESSAGE";
    ListView listView;
	DataBaseHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	    setUpDatabase();
	    List<Pizza> pizzas = myDbHelper.getAllPizzas(1);
	    Log.i("pizz", pizzas.get(0).name);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);

	    List<Pizzeria> pizzerias = myDbHelper.getAllRestaurants();

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<Pizzeria> adapter = new ArrayAdapter<Pizzeria>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, pizzerias);

        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item value
                Pizzeria item = (Pizzeria) listView.getItemAtPosition(position);

                // Show Alert
	            assert item != null;
	            Log.i("pizz",  myDbHelper.getAllPizzas(item.id).get(0).name);
	            toRestaurant(item);
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

	public void toRestaurant(Pizzeria pizzeria){
		Intent intent = new Intent(this, ResActivity.class);
		intent.putExtra(EXTRA_MESSAGE, pizzeria);
		startActivity(intent);
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
			Log.e("Pizz: SQL Exception", sqle.getMessage());
			throw sqle;
		}
	}
}
