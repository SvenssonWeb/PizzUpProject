package com.project.pizzup;

import java.util.ArrayList;

/**
 * Created by Artwar on 11/13/13.
 */
public class Pizza {
	public int id;
	public String name;
	public double price;
	public int rating;
	public ArrayList<String> ingredients;

	public final static String TABLE = "pizza";
	public final static String ID = "_id";
	public final static String NAME = "name";
	public final static String PRICE = "price";
	public final static String RATING = "rating";
}
