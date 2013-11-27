package com.project.pizzup.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Artwar on 11/13/13.
 */
public class Pizza implements Parcelable{
	public int id;
	public String name;
	public double price;
	public int rating;
	public List<Ingredient> ingredients;

	public Pizza(){}
	@Override
	public String toString() {
		return name + " " + price + ":- " + rating + "/5";
	}


	public final static String TABLE = "pizza";
	public final static String ID = "_id";
	public final static String NAME = "name";
	public final static String PRICE = "price";
	public final static String RATING = "rating";

	public String ingredientsToString(){
		String temp = "";
		if (ingredients != null) {
			for (Ingredient ingredient : this.ingredients){
				if (!temp.equals("")){
					temp += ", ";
				}
				temp += ingredient.name;
			}
		}
		return temp;
	}

	// Parcelling part
	public Pizza(Parcel in){
		this.id = in.readInt();
		this.name = in.readString();
		this.price = in.readDouble();
		this.rating = in.readInt();
//		in.readTypedList(ingredients, Ingredient.CREATOR);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.name);
		dest.writeDouble(this.price);
		dest.writeInt(this.rating);
//		dest.writeTypedList(this.ingredients);
	}
	public static final Parcelable.Creator<Pizza> CREATOR = new Parcelable.Creator<Pizza>() {
		public Pizza createFromParcel(Parcel in) {
			return new Pizza(in);
		}

		public Pizza[] newArray(int size) {
			return new Pizza[size];
		}
	};
}
