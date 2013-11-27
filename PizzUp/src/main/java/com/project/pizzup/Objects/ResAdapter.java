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
 * Created by Cissi on 2013-11-27.
 */
public class ResAdapter extends ArrayAdapter<Pizzeria> {

    Context context;
    int layoutResourceId;
    List<Pizzeria> data;

    public ResAdapter(Context context, int layoutResourceId, List<Pizzeria> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        PizzeriaHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new PizzeriaHolder();
            assert row != null;
            holder.txtName = (TextView) row.findViewById(R.id.pizzeriaName);
            holder.txtAddress = (TextView) row.findViewById(R.id.pizzeriaAddress);
            holder.txtRating = (RatingBar) row.findViewById(R.id.pizzeriaRating);

            row.setTag(holder);
        } else {
            holder = (PizzeriaHolder) row.getTag();
        }
        Pizzeria pizzeria = data.get(position);

        //holder.imgIcon;
        holder.txtName.setText(pizzeria.name);
        holder.txtAddress.setText(pizzeria.address);
        holder.txtRating.setRating(pizzeria.rating);
        return row;
    }


    static class PizzeriaHolder {
        TextView txtName;
        TextView txtAddress;
        RatingBar txtRating;
    }
}
