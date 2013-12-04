package com.project.pizzup.Objects;

/**
 * Created by cissi on 2013-12-04.
 */
public enum Sorting {
    PRICE ("price"), RATING("rating"), NAME("name");

    private final String name;

    private Sorting(String s) {
        name = s;
    }

    public boolean equalsName(String otherName){
        return (otherName == null)? false:name.equals(otherName);
    }

    public String toString(){
        return name;
    }
}
