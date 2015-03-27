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

        if (element == null) {
            LayoutInflater inflater = ((Activity) getContext()).getLayoutInflater();
            element = inflater.inflate(R.layout.sections_layout, null);

            TextView textView_title = (TextView) element.findViewById(R.id.textView_title);
            //textView_title.setBackgroundResource(R.drawable.flower);

            //textView_title.setText(dades[position].getName());

        }

        return element;
    }


}
