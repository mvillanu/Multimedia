package net.infobosccoma.multimedia;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import net.infobosccoma.multimedia.Adapter.GridAdapter;

import java.util.ArrayList;

//import net.infobosccoma.multimedia.Adapter.GridAdapter;


public class image_gallery extends ActionBarActivity {

    private ProgressDialog myProgressDialog;
    private GridView gridview;
    private ArrayList<String> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);

        gridview = (GridView) findViewById(R.id.gridView);
        images = new ArrayList<String>();
        images = getIntent().getStringArrayListExtra("images");
        gridview.setAdapter(new GridAdapter(this.getApplicationContext(),images));
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /**
     * This class loads the image gallery in grid view.
     *
     */
    public class ImageAdapter extends BaseAdapter {

        private ArrayList<String> images;
        public ImageAdapter(ArrayList<String> result){
            images=result;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View element = convertView;
            ImageView imageView;

            if (element == null) {
                LayoutInflater inflater = ((Activity) getApplicationContext()).getLayoutInflater();
                element = inflater.inflate(R.layout.image_layout, null);

                imageView = (ImageView) element.findViewById(R.id.imageViewItem);


                element.setTag(imageView);
            } else {
                imageView = (ImageView) element.getTag();
            }



            imageView.setImageResource(R.drawable.flower);




            return element;
        }


    }



}
