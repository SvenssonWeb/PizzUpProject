package com.project.pizzup.Objects;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.project.pizzup.R;

import java.util.List;

/**
 * Created by Artwar on 2013-11-26.
 */
public class MenuAdapter extends ArrayAdapter<Pizza> {

	Context context;
	int layoutResourceId;
	List<Pizza> data;

	public MenuAdapter(Context context, int layoutResourceId, List<Pizza> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		PizzaHolder holder;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new PizzaHolder();
			assert row != null;
			holder.txtTitle = (TextView) row.findViewById(R.id.pizzaItemName);
			holder.txtPrice = (TextView) row.findViewById(R.id.pizzaItemPrice);
			holder.txtRating = (RatingBar) row.findViewById(R.id.pizzaItemRating);
			holder.txtIngredients = (TextView) row.findViewById(R.id.pizzaItemIngredients);

			row.setTag(holder);
		} else {
			holder = (PizzaHolder) row.getTag();
		}
		Pizza pizza = data.get(position);

		//holder.imgIcon;
		holder.txtTitle.setText(pizza.name);
		holder.txtPrice.setText(pizza.price + ":-");
		holder.txtRating.setRating(pizza.rating);

		holder.txtIngredients.setText(pizza.ingredientsToString());
		return row;
	}


	static class PizzaHolder {
		TextView txtTitle;
		TextView txtPrice;
		RatingBar txtRating;
		TextView txtIngredients;
	}
}
