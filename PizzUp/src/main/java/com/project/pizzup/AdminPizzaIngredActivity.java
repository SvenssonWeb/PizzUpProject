package com.project.pizzup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.project.pizzup.Objects.Ingredient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hilda on 2013-12-11.
 */
public class AdminPizzaIngredActivity extends Activity implements View.OnClickListener {
    DataBaseHelper db;
    ListView ingredients;
    Button saveBtn;
    ArrayAdapter<Ingredient> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pizza_ingred);
        db = MainActivity.myDbHelper;

        ingredients = (ListView) findViewById(R.id.pizzaAddIngredient);
        List<Ingredient> ingredient = db.getAllIngredients();
        adapter = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_multiple_choice, ingredient);
        ingredients.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        ingredients.setAdapter(adapter);

        saveBtn = (Button) findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SparseBooleanArray checked = ingredients.getCheckedItemPositions();
        ArrayList<Ingredient> selectedItems = new ArrayList<Ingredient>();
        for (int i = 0; i < checked.size(); i++) {
            int position = checked.keyAt(i);
            if (checked.valueAt(i))
                selectedItems.add(adapter.getItem(position));
        }

        String temp ="";
        for (Ingredient i : selectedItems) {
            temp += i.id+",";
        }


        Intent resultIntent = new Intent();
        resultIntent.putExtra(AdminPizzaActivity.INGRED, temp);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();

    }
}
