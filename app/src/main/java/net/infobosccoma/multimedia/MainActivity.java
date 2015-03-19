package net.infobosccoma.multimedia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    static final String AUDIO_PATH="http://www.susanpiver.com/audio/Dancing in the Dark.mp3";
    private MediaPlayer player;
    private Button btnAccept;
    private int position;
    private Bitmap bitmap;
    private EditText textName;
    private EditText textSurname;
    private static final int CAMERA_CODE=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        player = new MediaPlayer();

        btnAccept = (Button) findViewById(R.id.button_accept);
        textName = (EditText) findViewById(R.id.editTextName);
        textSurname = (EditText)findViewById(R.id.editTextSurname);

        prepareBtn();
    }



    private void prepareEditText(){
        textName.setOnEditorActionListener(new EditText.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                return false;
            }
        });
    }

    private void prepareBtn() {
        btnAccept = (Button) findViewById(R.id.button_accept);
        btnAccept.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_CODE);
            }
        });


    }





    private void createVideo(VideoView video, String path){
        MediaController controller = new MediaController(this);
        video.setMediaController(controller);
        video.setVideoPath(path);
        video.start();
        video.requestFocus();


    }


    private void ResumeMedia(VideoView video, int pos){
        if(!video.isPlaying()){
            video.seekTo(pos);
            video.start();
        }

    }


    private void PauseMedia(VideoView video){
        if(video.isPlaying()){
            //pos = video.getCurrentPosition();
            video.pause();
        }

    }



    private void startPlaying(String path) throws IOException {
       player.setDataSource(path);
       player.prepare();
       player.start();
    }

    private void resume(String path, int position) throws IOException {
        player.setDataSource(path);
        player.prepare();
        player.seekTo(position);
        player.start();
    }


    private void stopPlaying(){
        if( player.isPlaying()){
            player.stop();
            player.release();
        }
    }

    private void pause(){
        position=player.getCurrentPosition();
        player.pause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
