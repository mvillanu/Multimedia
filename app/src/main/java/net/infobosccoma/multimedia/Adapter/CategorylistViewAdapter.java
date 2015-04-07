package net.infobosccoma.multimedia.Adapter;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import net.infobosccoma.multimedia.Model.Category;
import net.infobosccoma.multimedia.R;

/**
 * Created by Maxi on 19/03/2015.
 */
public class CategorylistViewAdapter extends ArrayAdapter<Category> {
    private Category[] dades;

    public CategorylistViewAdapter(Activity context, Category[] dades) {
        super(context, R.layout.sections_layout, dades);
        this.dades = dades;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View element = convertView;
        Vista vista;

        LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
        element = inflater.inflate(R.layout.sections_layout, null);

        vista = new Vista();
        vista.titol = (TextView) element.findViewById(R.id.textView_title);
        //Log.d("valor del titol: ",textView_title.toString());
        vista.titol.setBackgroundResource(dades[position].getId());
        vista.titol.setText(dades[position].getName());


        return element;
    }


    private class Vista {

        public TextView titol;

    }



}
