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
			holder.txtPriceCent = (TextView) row.findViewById(R.id.pizzaItemPriceCent);
			holder.txtRating = (RatingBar) row.findViewById(R.id.pizzaItemRating);
			holder.txtIngredients = (TextView) row.findViewById(R.id.pizzaItemIngredients);

			row.setTag(holder);
		} else {
			holder = (PizzaHolder) row.getTag();
		}
		Pizza pizza = data.get(position);

		//holder.imgIcon;
		holder.txtTitle.setText(pizza.name);
		//holder.txtPrice.setText(pizza.price + ":-");
		setPizzaPrice(holder.txtPrice, holder.txtPriceCent, pizza.price);
		holder.txtRating.setRating(pizza.rating);

		holder.txtIngredients.setText(pizza.ingredientsToString());
		return row;
	}
	private void setPizzaPrice(TextView crowns, TextView cent, double price){
		String[] priceString = (price + "").split("\\.");
		crowns.setText(priceString[0]);
		if (priceString[1].length() < 2) priceString[1] += "0";
		cent.setText(priceString[1]);

	}

	static class PizzaHolder {
		TextView txtTitle;
		TextView txtPrice;
		RatingBar txtRating;
		TextView txtIngredients;
		TextView txtPriceCent;
	}
}
