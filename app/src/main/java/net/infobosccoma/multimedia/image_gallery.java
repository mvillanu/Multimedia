package net.infobosccoma.multimedia;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class image_gallery extends ActionBarActivity {

    private ProgressDialog myProgressDialog;
    private GridView gridview;
    private ArrayList<String> images;
    private ArrayList<String> urls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);

        images = new ArrayList<String>();

        urls = new ArrayList<String>();
        urls.add("http://www.katyperry.com/image-galleries/roar-video-shoot/");
        urls.add("http://www.katyperry.com/image-galleries/california-gurls/");
        urls.add("http://www.katyperry.com/image-galleries/firework/");
        urls.add("http://www.katyperry.com/image-galleries/one-boys/");
        urls.add("http://www.katyperry.com/image-galleries/waking-vegas/");
        urls.add("http://www.katyperry.com/image-galleries/mtv-unplugged/");

        images = new ArrayList<String>();
        gridview = (GridView) findViewById(R.id.gridView);
        for(String url : urls){
            new Downloader().execute(url);
        }

        gridview.setAdapter(new ImageAdapter());


    }

    private void waitDialog(){


            myProgressDialog = new ProgressDialog(image_gallery.this);
            myProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            myProgressDialog.setMessage(getResources().getString(R.string.loading));
            myProgressDialog.show();
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


        @Override
        public int getCount() {
            return 0;
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
            View v = convertView;
            LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.image_layout, null);

            try {

                ImageView imageView = (ImageView) v.findViewById(R.id.imageViewItem);

                    Picasso.with(getApplicationContext()).load(images.get(position)).into(imageView);


            } catch (Exception e) {

            }
            return v;
        }


    }


    private class Downloader extends AsyncTask<String, Void, Void> {

        private ArrayList<String> list;

        @Override
        protected Void doInBackground(String... params) {
            ArrayList<String> result = getImageUrls(params[0]);
            list = result;
            images.addAll(list);
            return null;
        }



        protected ArrayList<String> OnPostExecute(String... params) {

            //images.addAll(list);
            return null;
        }








        /**
         * get all the images in the given url
         * @param url
         */
        public ArrayList<String>getImageUrls(String url){
            ArrayList<String> list = new ArrayList<String>();


            String patternString = "<img src=\"(.*?)\"";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher;
            matcher = pattern.matcher(url);

            while (matcher.find()) {
                String result = matcher.group();
                list.add(result.substring(result.indexOf("http"), result.lastIndexOf("\"")));
            }
            return list;
        }



    }
}
