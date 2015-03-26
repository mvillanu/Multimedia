package net.infobosccoma.multimedia.Adapter;

import android.app.Activity;
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

        if (element == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            element = inflater.inflate(R.layout.sections_layout, null);

            vista = new Vista();
            vista.textView_title = (TextView) element.findViewById(R.id.textView_title);
            //vista.textView_title.setBackgroundResource(R.drawable.flower);


            element.setTag(vista);
        } else {
            vista = (Vista) element.getTag();
        }

        return element;
    }


    private class Vista {

        public TextView textView_title;
    }

}
