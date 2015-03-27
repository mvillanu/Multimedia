package net.infobosccoma.multimedia;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Maxi on 26/03/2015.
 */
public class Images{

    private ArrayList<String> urls;
    private static ArrayList<String> images;
    public Images(){}

    public  void download(){
        urls = new ArrayList<String>();
        images = new ArrayList<String>();

        urls.add("http://www.katyperry.com/image-galleries/roar-video-shoot/");
        urls.add("http://www.katyperry.com/image-galleries/california-gurls/");
        urls.add("http://www.katyperry.com/image-galleries/firework/");
        urls.add("http://www.katyperry.com/image-galleries/one-boys/");
        urls.add("http://www.katyperry.com/image-galleries/waking-vegas/");
        urls.add("http://www.katyperry.com/image-galleries/mtv-unplugged/");

        for(String url : urls){
            Log.i("gallery", "url");
            new Downloader().execute(url);
        }
    }

    private class Downloader extends AsyncTask<String, Void, Void> {

        private ArrayList<String> list;

        @Override
        protected Void doInBackground(String... params) {
            Log.i("images", "entra thread");
            ArrayList<String> result = null;

            HttpClient client = new DefaultHttpClient();

            try {
                HttpGet httpGet = new HttpGet(new URI(params[0]));
                HttpResponse resposta = client.execute(httpGet);
                result = getImageUrls(resposta.getEntity().getContent());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            list = result;
            images.addAll(list);
            Log.i("maxi",""+images.size());
            return null;
        }



        protected ArrayList<String> OnPostExecute(String... params) {

            //images.addAll(list);
            return null;
        }


        /**
         * get all the images in the given url
         * @param content
         * @return
         * @throws IOException
         */
        public ArrayList<String>getImageUrls(InputStream content) throws IOException {
            ArrayList<String> list = new ArrayList<String>();

            BufferedReader in = new BufferedReader(new InputStreamReader(content));
            String inputLine;

            String patternString = "<img src=\"(.*?)\"";
            Pattern pattern = Pattern.compile(patternString);
            Matcher matcher;
            while ((inputLine = in.readLine()) != null) {
                matcher = pattern.matcher(inputLine);

                while (matcher.find()) {
                    // Get the matching string
                    //urls.add(matcher.group());
                    String result = matcher.group();
                    Log.i("images", result);
                    //list.add(ImageIO.read(new URL("http://www.katy-perry.nl/images/wallpapers/katy-perry5.jpg")));
                    if(result.contains(".jpg")){
                        list.add(result.substring(result.indexOf("http"), result.lastIndexOf("\"")));
                    }

                }
            }

            return list;
        }



    }

    public ArrayList<String> getList(){
        return this.images;
    }
}
