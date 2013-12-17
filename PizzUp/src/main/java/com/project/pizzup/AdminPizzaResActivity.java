package com.project.pizzup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.project.pizzup.Objects.Ingredient;
import com.project.pizzup.Objects.Pizzeria;
import com.project.pizzup.Objects.Sorting;

import java.util.List;

/**
 * Created by cissi on 2013-12-11.
 */
public class AdminPizzaResActivity extends Activity {
    DataBaseHelper db;
    ListView pizzeria;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pizza_res);
        db = MainActivity.myDbHelper;

        pizzeria = (ListView) findViewById(R.id.pizzaAddPizzeria);
        List<Pizzeria> restaurants = db.getAllRestaurants();
        ArrayAdapter<Pizzeria> adapter = new ArrayAdapter<Pizzeria>(this, android.R.layout.simple_list_item_1, restaurants);
        pizzeria.setAdapter(adapter);

        pizzeria.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // ListView Clicked item value
                Pizzeria item = (Pizzeria) pizzeria.getItemAtPosition(position);

                // Show Alert
                assert item != null;
                save(item);
            }

        });
    }

    private void save(Pizzeria item) {
        String temp = item.id+"";
        Intent resultIntent = new Intent();
        resultIntent.putExtra(AdminPizzaActivity.RES, temp);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
