package com.project.pizzup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.pizzup.Objects.Ingredient;

import java.util.List;

/**
 * Created by Hilda on 2013-12-03.
 */
public class AdminPizzaActivity extends Activity implements View.OnClickListener {
    public static final String RES = "dhfgiehfdisgdfudy";
    public static final String INGRED = "fdiodnfivnsdibwiv";
    private static final int STATIC_R = 10001;
    private static final int STATIC_I = 20002;

    DataBaseHelper db;
    EditText pizza;
    EditText price;
    Button openIngred;
    Button openRes;
    TextView pizzeriaName;
    TextView ingredName;

    Button addBtn;

    TempPizza tempPizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pizza);
        tempPizza = new TempPizza();
        db = MainActivity.myDbHelper;

        pizza = (EditText) findViewById(R.id.pizzaAddName);
        price = (EditText) findViewById(R.id.pizzaAddPrice);
        pizzeriaName = (TextView) findViewById(R.id.pizzeriaName);
        ingredName = (TextView) findViewById(R.id.ingredName);

        addBtn = (Button) findViewById(R.id.admin_addPizza_btn);
        openIngred = (Button) findViewById(R.id.admin_addIngred_btn);
        openRes = (Button) findViewById(R.id.admin_addPizzeria_btn);

        addBtn.setOnClickListener(this);
        openIngred.setOnClickListener(this);
        openRes.setOnClickListener(this);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("pizzup-test", ""+requestCode + "||" + resultCode + "||" + data.getExtras().toString());
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (STATIC_R) : {
                if (resultCode == Activity.RESULT_OK) {
                    String res = data.getStringExtra(RES);
                    Log.i("pizzup-test-ingred", res);
                    pizzeriaName.setText("Vald pizzeria: " + db.getRestaurant(Integer.parseInt(res)).name);
                    tempPizza.resID = res;
                }
                break;
            }
            case (STATIC_I) : {
                if (resultCode == Activity.RESULT_OK) {
                    String ingred = data.getStringExtra(INGRED);
                    Log.i("pizzup-test-ingred", ingred);
                    String text = "";
                    List<Ingredient> ingredients = db.getIngredients(ingred);
                    for (Ingredient i : ingredients){
                        text += i.name + ", ";
                    }
                    text = text.substring(0, text.length()-1);
                    ingredName.setText("Valda ingredienser: " + text);
                    tempPizza.ingredIDs = ingred;
                }
                break;
            }
            default:
            // Default?
        }
    }


    @Override
    public void onClick(View v) {
        if (v == openRes) {
            Intent myIntent = new Intent(this, AdminPizzaResActivity.class);
            startActivityForResult(myIntent, STATIC_R);
        }
        if (v == openIngred) {
            Intent myIntent = new Intent(this, AdminPizzaIngredActivity.class);
            startActivityForResult(myIntent, STATIC_I);

        }
        if (v == addBtn) {
            tempPizza.name = pizza.getText().toString();
            tempPizza.price = price.getText().toString();
            db.createPizza(tempPizza);
            finish();
        }
    }
    class TempPizza {
        String name;
        String price;
        String resID;
        String ingredIDs;
    }
}
