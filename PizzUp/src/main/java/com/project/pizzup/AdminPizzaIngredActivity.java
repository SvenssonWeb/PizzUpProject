package com.project.pizzup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.project.pizzup.Objects.Ingredient;

import java.util.List;

/**
 * Created by Hilda on 2013-12-11.
 */
public class AdminPizzaIngredActivity extends Activity implements View.OnClickListener {
    DataBaseHelper db;
    ListView ingredients;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pizza_ingred);
        db = MainActivity.myDbHelper;

        ingredients = (ListView) findViewById(R.id.pizzaAddIngredient);
        List<Ingredient> ingredient = db.getAllIngredients();
        ArrayAdapter<Ingredient> adapter = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_multiple_choice, ingredient);
        ingredients.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ingredients.setAdapter(adapter);

        saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
