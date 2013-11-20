package com.project.pizzup;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.project.pizzup.Objects.Pizza;

import java.io.IOException;
import java.util.List;

public class ResActivity extends Activity {

    ListView listView;
    DataBaseHelper myDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res);
	    setUpDatabase();

	    Intent intent = getIntent();
	    int resId = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, -1);
	    Log.i("pizz",resId+"");
	    List<Pizza> pizzas = myDbHelper.getAllPizzas(resId);
	    ArrayAdapter<Pizza> adapter = new ArrayAdapter<Pizza>(this,
			    android.R.layout.simple_list_item_1, android.R.id.text1, pizzas);


	    listView = (ListView) findViewById(R.id.pizza_menu);
        //ArrayAdapter adapter = new ArrayAdapter<Pizza>(this,R.layout.pizza_list_item);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
}
