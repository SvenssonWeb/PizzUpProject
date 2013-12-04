package com.project.pizzup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Hilda on 2013-12-03.
 */
public class AdminPizzeriaActivity extends Activity implements View.OnClickListener {
    TextView pizzeria;
    Button addBtn;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pizzeria);
        db = MainActivity.myDbHelper;

        pizzeria = (TextView) findViewById(R.id.pizzeriaAddName);
        addBtn = (Button) findViewById(R.id.admin_addPizzeria_btn);

        addBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == addBtn) {
            db.createRestaurant(pizzeria.getText().toString());
            finish();
        }
    }
}
