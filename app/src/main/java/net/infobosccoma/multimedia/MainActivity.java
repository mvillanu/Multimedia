package net.infobosccoma.multimedia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import net.infobosccoma.multimedia.Model.User;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity{

    static final String AUDIO_PATH="http://www.susanpiver.com/audio/Dancing in the Dark.mp3";
    private MediaPlayer player;
    private Button btnAccept;
    private int position;
    public static Bitmap captureBmp;
    private EditText textName;
    private EditText textSurname;
    private static final int CAMERA_CODE=100;
    String mCurrentPhotoPath;
    User user;
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

                dispatchTakePictureIntent();
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



    //create a temporary file in sd card path.

    private File getFile(Context context){
        final File path = new File( Environment.getExternalStorageDirectory(), context.getPackageName() );
        if(!path.exists()){
            path.mkdir();
        }
        return new File(path, "selfie.png");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
/*
        Intent i = new Intent(getApplicationContext(),DrawerMenuActivity.class);

        Bitmap bitmap = null;
        InputStream stream = null;

        if (requestCode == CAMERA_CODE && resultCode == Activity.RESULT_OK)
            try {
                // recyle unused bitmaps
                if (bitmap != null) {
                    bitmap.recycle();
                }
                stream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(stream);

                //imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
            if (stream != null)
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        Uri uri = null;
        if(requestCode==CAMERA_CODE)
        {
            user = null;
            //captureBmp = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(getFile(getBaseContext())));
            uri = Uri.fromFile(getFile(getBaseContext()));
            user = new User(textName.getText().toString(),textSurname.getText().toString()/*,captureBmp);
            Log.i("user:",user.getName());

            //Intent intent = new Intent(getApplicationContext(),katy_activity.class);
            //intent.putExtra("user",user);
            //startActivity(intent);

            //Bundle b = user.makeBundle("user");
            i.putExtra("user",user);
            //i.putExtra("image",makeByteArray(captureBmp));
            //i.putExtra("user",b);
            startActivity(i);
        }*/

        Intent i = new Intent(this, DrawerMenuActivity.class);
        User user = new User(textName.getText().toString(),textSurname.getText().toString(),mCurrentPhotoPath);
        i.putExtra("user",user);
        Log.d("Photo path: ",mCurrentPhotoPath);
        startActivity(i);










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



    public byte[] makeByteArray(Bitmap bitmap){

        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] byteArray = bStream.toByteArray();

        return byteArray;
        /*Intent anotherIntent = new Intent(this, anotherActivity.class);
        anotherIntent.putExtra("image", byteArray);
        startActivity(anotherIntent);
        finish();*/

    }

    private File createImageFile() throws IOException {
        // Crea un fitxer d'imatge
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath(); //"file:" +
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, CAMERA_CODE);
            }
        }
    }




}
