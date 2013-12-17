package com.project.pizzup.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Artwar on 2013-11-20.
 */
public class Ingredient implements Parcelable {
	public int id;
	public String name;

	public final static String TABLE = "ingredient";
	public final static String ID = "_id";
	public final static String NAME = "name";


	// Parcelling part
	public Ingredient(Parcel in){

		this.id = in.readInt();
		this.name = in.readString();
	}

	public Ingredient() {}

    public String toString(){
        return name;
    }

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(this.id);
		dest.writeString(this.name);
	}
	public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>() {

		public Ingredient createFromParcel(Parcel in) {
			return new Ingredient(in);
		}

		public Ingredient[] newArray(int size) {
			return new Ingredient[size];
		}
	};
}
