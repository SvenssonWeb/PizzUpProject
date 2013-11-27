package com.project.pizzup.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Artwar on 11/13/13.
 */
public class Pizzeria implements Parcelable {
	public int id;
	public String name;
	public String address;
	public int phone;
	public int rating;
	public ArrayList<Pizza> pizzas;

	public Pizzeria(){}
	public Pizzeria(int id, String name){
		this.id = id;
		this.name = name;
	}
	public String toString(){
		return name;
	}
	public final static String TABLE = "restaurant";
	public final static String ID = "_id";
	public final static String NAME = "name";
	public final static String ADDRESS = "address";
	public final static String PHONE = "phone";
	public final static String RATING = "rating";



	// Parcelling part
	public Pizzeria(Parcel in){
		String[] data = new String[5];

		in.readStringArray(data);
		this.id = Integer.parseInt(data[0]);
		this.name = data[1];
		this.address = data[2];
		this.phone = Integer.parseInt(data[3]);
		this.rating = Integer.parseInt(data[4]);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringArray(new String[] {this.id+"",
				this.name,
				this.address,
				this.phone+"",
				this.rating+""});
	}
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Pizzeria createFromParcel(Parcel in) {
			return new Pizzeria(in);
		}

		public Pizzeria[] newArray(int size) {
			return new Pizzeria[size];
		}
	};
}
