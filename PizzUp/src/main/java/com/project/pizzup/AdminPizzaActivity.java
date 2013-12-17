package com.project.pizzup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.project.pizzup.Objects.Ingredient;

/**
 * Created by Hilda on 2013-12-03.
 */
public class AdminPizzaActivity extends Activity implements View.OnClickListener {
    public static final String RES = "";
    public static final String INGRED = "";
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pizza);
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
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (STATIC_R) : {
                if (resultCode == Activity.RESULT_OK) {
                    String res = data.getStringExtra(RES);
                    pizzeriaName.setText("Vald pizzeria: " + db.getRestaurant(Integer.parseInt(res)).name);

                }
                break;
            }
            case (STATIC_I) : {
                if (resultCode == Activity.RESULT_OK) {
                    String ingred = data.getStringExtra(INGRED);
                    String text = "";
                    for (Ingredient i : db.getIngredients(ingred)){
                        text += i.name + ", ";
                    }
                    ingredName.setText("Valda ingredienser: " + text);
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
        /*if (v == addBtn) {
            //db.createPizza();
            finish();
        }*/
    }

}
