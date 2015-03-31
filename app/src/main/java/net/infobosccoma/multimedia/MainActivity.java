package net.infobosccoma.multimedia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import net.infobosccoma.multimedia.Model.User;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity{


    private Button btnAccept;
    private EditText textName;
    private EditText textSurname;
    private static final int CAMERA_CODE=100;
    private String mCurrentPhotoPath;
    private File photoFile;
    private boolean isNameFirstEdit, isSurnameFirstEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAccept = (Button) findViewById(R.id.button_accept);
        textName = (EditText) findViewById(R.id.editTextName);
        textSurname = (EditText)findViewById(R.id.editTextSurname);

        prepareBtn();
        isNameFirstEdit=true;
        isSurnameFirstEdit=true;
        prepareEditText();
    }




    private void prepareEditText(){

        textName.setOnEditorActionListener(new EditText.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(isNameFirstEdit){
                    isNameFirstEdit=!isNameFirstEdit;
                    textName.setText("");
                }
                return false;
            }
        });

        textSurname.setOnEditorActionListener(new EditText.OnEditorActionListener(){

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(isSurnameFirstEdit){
                    isSurnameFirstEdit=!isSurnameFirstEdit;
                    textName.setText("");
                }
                return false;
            }
        });
    }

    private void prepareBtn() {
        btnAccept = (Button) findViewById(R.id.button_accept);
        btnAccept.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==CAMERA_CODE){
            Intent i = new Intent(this, DrawerMenuActivity.class);
            User user = new User(textName.getText().toString(),textSurname.getText().toString(),mCurrentPhotoPath);
            i.putExtra("user",user);
            startActivity(i);
        }


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


    /**
     * create's the path for the image to be saved
     * @return
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        // Crea un fitxer d'imatge
        String timeStamp = new SimpleDateFormat("yyyy-MMdd-HHmmss").format(new Date());
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


    /**
     * this function is meant to call upon the camera and create a path to store it
     */
    private void dispatchTakePictureIntent() {


        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            photoFile = null;
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
