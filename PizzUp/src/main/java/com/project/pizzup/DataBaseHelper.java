package com.project.pizzup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.project.pizzup.Objects.Ingredient;
import com.project.pizzup.Objects.Pizza;
import com.project.pizzup.Objects.Pizzeria;
import com.project.pizzup.Objects.Sorting;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Artwar on 11/13/13.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

	//The Android's default system path of your application database.
	private static String DB_PATH = "/data/data/com.project.pizzup/databases/";

	private static String DB_NAME = "pizzup.db";

	private SQLiteDatabase myDataBase;

	private final Context myContext;

	/**
	 * Constructor
	 * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
	 * @param context
	 */
	public DataBaseHelper(Context context) {

		super(context, DB_NAME, null, 1);
		this.myContext = context;
	}

	/**
	 * Creates a empty database on the system and rewrites it with your own database.
	 * */
	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();

		if(dbExist){
			//do nothing - database already exist
		}else{
			//By calling this method and empty database will be created into the default system path
			//of your application so we are gonna be able to overwrite that database with our database.
			this.getReadableDatabase();
			try {
				copyDataBase();
			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}

	/**
	 * Check if the database already exist to avoid re-copying the file each time you open the application.
	 * @return true if it exists, false if it doesn't
	 */
	private boolean checkDataBase(){
		SQLiteDatabase checkDB = null;
		try{
			myContext.getDatabasePath(DB_NAME);

			String myPath = myContext.getDatabasePath(DB_NAME).getPath();
			checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e){
			//database doesn't exist yet.
		}
		if(checkDB != null){
			checkDB.close();
		}
		return checkDB != null;
	}

	/**
	 * Copies your database from your local assets-folder to the just created empty database in the
	 * system folder, from where it can be accessed and handled.
	 * This is done by transfering bytestream.
	 * */
	private void copyDataBase() throws IOException{

		//Open your local db as the input stream
		InputStream myInput = //myContext.getAssets().open(DB_NAME);
		myContext.getResources().openRawResource(R.raw.pizzup);

		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;

		//Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);

		//transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer))>0){
			myOutput.write(buffer, 0, length);
		}

		//Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {

		//Open the database
		String myPath = DB_PATH + DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        myDataBase = getWritableDatabase();

	}

	@Override
	public synchronized void close() {

		if(myDataBase != null)
			myDataBase.close();

		super.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	// Add your public helper methods to access and get content from the database.
	// You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
	// to you to create adapters for your views.

//	String      dbName	    The table name to compile the query against.
//	String[]    columnNames	A list of which table columns to return. Passing "null" will return all columns.
//	String      whereClause	Where-clause, i.e. filter for the selection of data, null will select all data.
//	String[]    selectionArgs	You may include ?s in the "whereClause"". These placeholders will get replaced by the values from the selectionArgs array.
//	String[]    groupBy	    A filter declaring how to group rows, null will cause the rows to not be grouped.
//	String[]    having	    Filter for the groups, null means no filter.
//	String[]    orderBy	    Table columns which will be used to order the data, null means no ordering.


	public Cursor getRestaurantCursor(){
		return myDataBase.query(Pizzeria.TABLE,
				null, null, null, null, null, null);

	}
	public Cursor getPizzaCursor(int restaurantId, Sorting orderBy){
        String query;
        if (orderBy == null)
            orderBy = Sorting.NAME;

        switch (orderBy) {
            case RATING:
                query = "SELECT p._id, p.name, p.price, p.rating FROM pizza AS p LEFT JOIN restaurant AS r ON r._id = p.r_id WHERE r._id = ? ORDER BY p." + orderBy + " DESC";
                break;
            case NAME:
            case PRICE:
                query = "SELECT p._id, p.name, p.price, p.rating FROM pizza AS p LEFT JOIN restaurant AS r ON r._id = p.r_id WHERE r._id = ? ORDER BY p." + orderBy + " ASC";
            break;
            default:
                query = "SELECT p._id, p.name, p.price, p.rating FROM pizza AS p LEFT JOIN restaurant AS r ON r._id = p.r_id WHERE r._id = ?"; // ORDER BY p.price
            break;
        }
        String[] args = {""+restaurantId};

		return myDataBase.rawQuery(query, args);
	}
	public Cursor getIngredientCursor(int pizzaId){
		String query = "SELECT * FROM ingredient AS i LEFT JOIN p_i ON p_i.i_id = i._id WHERE p_id = ?";
		String[] args = {""+pizzaId};

		return myDataBase.rawQuery(query, args);
	}

    public long createIngredients(String ingredient){
        ContentValues values = new ContentValues();
        values.put("name", ingredient);

        return myDataBase.insert("ingredient", null, values);
    }

    public long createRestaurant(String pizzeria, String address, String phone){
        ContentValues values = new ContentValues();
        values.put("name", pizzeria);
        values.put("address", address);
        values.put("phone", phone);

        return myDataBase.insert("restaurant", null, values);
    }

	public Pizzeria getRestaurant(int restaurantID){
		String query = "SELECT * FROM restaurant AS r WHERE r._id = ?";
		String[] args = {""+restaurantID};
		Cursor cursor = myDataBase.rawQuery(query, args);

		int id = cursor.getColumnIndex(Pizzeria.ID);
		int name = cursor.getColumnIndex(Pizzeria.NAME);
		int address = cursor.getColumnIndex(Pizzeria.ADDRESS);
		int phone = cursor.getColumnIndex(Pizzeria.PHONE);
		int rating = cursor.getColumnIndex(Pizzeria.RATING);

		if (cursor.moveToNext()){
			Pizzeria pizzeria = new Pizzeria();
			pizzeria.id = cursor.getInt(id);
			pizzeria.name = cursor.getString(name);
			pizzeria.address = cursor.getString(address);
			pizzeria.phone = cursor.getString(phone);
			pizzeria.rating = cursor.getInt(rating);
			return pizzeria;
		}
		return null;
	}

	public List<Pizzeria> getAllRestaurants(){
		List<Pizzeria> pizzerias = new ArrayList<Pizzeria>();

		Cursor cursor = getRestaurantCursor();

		int id = cursor.getColumnIndex(Pizzeria.ID);
		int name = cursor.getColumnIndex(Pizzeria.NAME);
		int address = cursor.getColumnIndex(Pizzeria.ADDRESS);
		int phone = cursor.getColumnIndex(Pizzeria.PHONE);
		int rating = cursor.getColumnIndex(Pizzeria.RATING);

		while (cursor.moveToNext()){
			Pizzeria pizzeria = new Pizzeria();
			pizzeria.id = cursor.getInt(id);
			pizzeria.name = cursor.getString(name);
			pizzeria.address = cursor.getString(address);
			pizzeria.phone = cursor.getString(phone);
			pizzeria.rating = cursor.getInt(rating);
			pizzerias.add(pizzeria);
		}
		return pizzerias;
	}

	public List<Pizza> getAllPizzas(int restaurantId, Sorting orderBy){
		List<Pizza> pizzas = new ArrayList<Pizza>();
        Log.d("getAllPizzas", "Sorting by: " + orderBy);

		Cursor cursor = getPizzaCursor(restaurantId, orderBy);

		int id = cursor.getColumnIndex(Pizza.ID);
		int name = cursor.getColumnIndex(Pizza.NAME);
		int price = cursor.getColumnIndex(Pizza.PRICE);
		int rating = cursor.getColumnIndex(Pizza.RATING);

		while (cursor.moveToNext()){
			Pizza pizza = new Pizza();
			pizza.id = cursor.getInt(id);
			pizza.name = cursor.getString(name);
			pizza.price = cursor.getDouble(price);
			pizza.rating = cursor.getInt(rating);
			pizza.ingredients = getPizzaIngredients(pizza.id);
			Log.i("pizz", cursor.getPosition() + pizza.id + ":" + pizza.name + ":" + pizza.price + ":" + pizza.rating);
			pizzas.add(pizza);
		}

		return pizzas;
	}

	public List<Ingredient> getPizzaIngredients(int pizzaId){
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		Cursor cursor = getIngredientCursor(pizzaId);

		int id = cursor.getColumnIndex(Pizza.ID);
		int name = cursor.getColumnIndex(Pizza.NAME);

		while (cursor.moveToNext()){
			Ingredient ingredient = new Ingredient();
			ingredient.id = cursor.getInt(id);
			ingredient.name = cursor.getString(name);
			Log.i("pizz - Ingredients", cursor.getPosition() + ingredient.id + ":" + ingredient.name);
			ingredients.add(ingredient);
		}
		return ingredients;
	}
	public int setRestaurantRating(int restaurantId, float rating){
		myDataBase = getWritableDatabase();
		int ratingInt = (int) rating;

		ContentValues values = new ContentValues();
		values.put(Pizzeria.RATING, ratingInt);
		String where = Pizzeria.ID + " = ?";
		String[] args = {restaurantId+""};

		return myDataBase.update(Pizzeria.TABLE, values, where, args);
	}
	public int setPizzaRating(int pizzaId, float rating){
		myDataBase = getWritableDatabase();
		int ratingInt = (int) rating;

		ContentValues values = new ContentValues();
		values.put(Pizza.RATING, ratingInt);
		String where = Pizza.ID + " = ?";
		String[] args = {pizzaId+""};

		return myDataBase.update(Pizza.TABLE, values, where, args);
	}
}