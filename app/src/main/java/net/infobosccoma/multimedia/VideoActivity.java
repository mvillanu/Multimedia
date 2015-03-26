package net.infobosccoma.multimedia;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;


public class VideoActivity extends ActionBarActivity {

    private VideoView video;
    private int pos;
    private String path;
    private MediaController controller;
    private final String ROAR_PATH="http://www.funmaza.eu/videos/2014/Katy%20Perry%201080p%20-%20Roar%20[Funmaza.com].mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_activity);

        video = (VideoView)findViewById(R.id.videoView);

        path = getIntent().getStringExtra("path");
        createVideo(path);

    }



    private void createVideo(String path){
         controller = new MediaController(this);
        video.setMediaController(controller);
        video.setVideoPath(path);
        video.start();
        video.requestFocus();


    }


    private void ResumeMedia(int pos){
        if(!video.isPlaying()){
            video.seekTo(pos);
            video.start();
        }

    }


    private void PauseMedia(){
        if(video.isPlaying()){
            pos = video.getCurrentPosition();
            video.pause();
        }

    }

    private void stopMedia(){

        video.stopPlayback();
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video, menu);
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




}
