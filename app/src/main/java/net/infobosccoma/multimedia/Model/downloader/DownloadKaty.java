package net.infobosccoma.multimedia.Model.downloader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Maxi on 19/03/2015.
 */
public class DownloadKaty {

    public DownloadKaty(){

    }

    public InputStream getInputStreamFromUrl(String url) {
        InputStream content = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(new HttpGet(url));
            content = response.getEntity().getContent();
        } catch (Exception e) {
            //Log.("[GET REQUEST]", "Network exception", e);
        }
        return content;
    }

    public void parseContent(InputStream content) throws IOException {

        ArrayList<String> urls = new ArrayList<String>();

        BufferedReader in = new BufferedReader(content);
        String inputLine;


        String patternString = "<img alt=\"Katy Perry\" src=\"(.*?)\"";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher;

        while ((inputLine = in.readLine()) != null) {
             matcher = pattern.matcher(inputLine);

            matcher.toString();
        }

        in.close();
    }
}
