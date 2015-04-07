package net.infobosccoma.multimedia.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import net.infobosccoma.multimedia.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Albert on 27/03/2015.
 */
public class MyImageAdapter extends BaseAdapter {
    private Context mContext;
    private Bitmap imatgeBaixada;
    private ImageView mostraImatge;
    private ProgressDialog pDialog;

    public MyImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {



        if (convertView == null) {
            // if it's not recycled, initialize some attributes

            mostraImatge = new ImageView(mContext);
            mostraImatge.setLayoutParams(new GridView.LayoutParams(100, 100));
            mostraImatge.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mostraImatge.setPadding(8, 8, 8, 8);
        } else {
            mostraImatge = (ImageView) convertView;
        }

        //new GetImageTask().execute(mThumbIds[position]);
        try {
            new DownloadImageTask(mostraImatge).execute(new URL(mThumbIds[position]));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.d("pagina: ",mThumbIds[position]);
        //downloader.execute(mThumbIds[position]);
        //Log.d("Imatge: ", imatgeBaixada.toString());
        //imageView.setImageBitmap(image);
        //mostraImatge.setImageBitmap(imatgeBaixada);
        mostraImatge.setImageResource(R.drawable.flower);

        return mostraImatge;
    }

    // references to our images
    private String[] mThumbIds = {
            "http://cp91279.biography.com/1000509261001/1000509261001_2051017820001_Bio-Biography-Katy-Perry-SF.jpg",
            "http://www.z90.com/wp-content/uploads/2015/03/Katy-Perry-Widescreen-Wallpaper.jpg",
            "http://fmdos.cl/wp-content/uploads/2014/10/KATY-PERRY-SUPER-BOWL-2015-2.jpg",
            "http://img02.lavanguardia.com/2014/06/04/Katy-Perry-durante-uno-de-los-_54408672233_54028874188_960_639.jpg"
    };

    public class DownloadImageTask extends AsyncTask<URL, Void, Bitmap> {

        private static final String LOG_E_TAG = "DownloadImageTask";
        private final  WeakReference<ImageView> containerImageView;

        public DownloadImageTask(ImageView imageView) {
            this.containerImageView = new WeakReference<ImageView>(imageView);
        }

        @Override
        protected Bitmap doInBackground(URL... params) {
            URL imageURL = params[0];
            Bitmap downloadedBitmap = null;
            try {
                InputStream inputStream = imageURL.openStream();
                downloadedBitmap = BitmapFactory.decodeStream(inputStream);
            } catch (Exception e) {
                Log.e(LOG_E_TAG, e.getMessage());
                e.printStackTrace();
            }
            return downloadedBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            ImageView imageView = this.containerImageView.get();
            if (imageView != null) {
                imageView.setImageBitmap(result);
            }
        }
    }























}











