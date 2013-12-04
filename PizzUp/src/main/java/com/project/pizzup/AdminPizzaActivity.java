package com.project.pizzup;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Hilda on 2013-12-03.
 */
public class AdminPizzaActivity extends Activity {
    TextView pizza;
    ListView pizzeria;
    //CheckBox ingredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pizza);

        pizza = (TextView) findViewById(R.id.pizzaAddName);
        pizzeria = (ListView) findViewById(R.id.pizzaAddPizzeria);

    }
}
