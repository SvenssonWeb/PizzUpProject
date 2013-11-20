package com.project.pizzup.Objects;

import java.util.ArrayList;

/**
 * Created by Artwar on 11/13/13.
 */
public class Pizzeria {
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
}
