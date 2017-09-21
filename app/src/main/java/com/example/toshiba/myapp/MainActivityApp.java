package com.example.toshiba.myapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.toshiba.myapp.phones.Phones;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivityApp extends Activity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
    private int fileUri;
    private int fileUri2;

    private MediaPlayer mediaPlayer;

    private Dialog createAlertDialogWithButtons() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Wyjście");
        dialogBuilder.setMessage("Czy napewno?");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("Tak", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                showToast("Wychodzę");
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        dialogBuilder.setNegativeButton("Nie", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {
                showToast("Anulowaleś wyjście");

            }
        });
        return dialogBuilder.create();
    }

    private Dialog createAlertDialogWithList(final View view) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final String[] options = {"Start Bluethroat", "Stop Bluethroat"};
        dialogBuilder.setTitle("Wybierz dźwięk");
        dialogBuilder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                if(position == 0) {
                    playSound(view);
                }
                else {
                    stopSound(view);
                }
            }
        });
        return dialogBuilder.create();
    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(),
                message,
                Toast.LENGTH_LONG).show();
    }

    public void playSound(View view){
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.bluethroat);
        mediaPlayer.start();
    }
    public void stopSound(View view){
        mediaPlayer.stop();
    }

    public void makePhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imagesFolder = new File(Environment.getExternalStorageDirectory(),
                "MyImages");
        imagesFolder.mkdirs();
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File image = new File(imagesFolder, "tutorial_" + timeStamp + ".jpg");
        Uri uriSavedImage = Uri.fromFile(image);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_app);
        final Context context;
        context = getApplicationContext();

        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        Button buttonPhones = (Button) findViewById(R.id.buttonPhones);
        Button btnNewAlertDialogButton = (Button) findViewById(R.id.btnNewAlertDialogButton);
        Button btnNewAlertDialogWithList = (Button) findViewById(R.id.btnNewAlertDialogWithList);
        Button photoButton = (Button) findViewById(R.id.photoButton);
        Button buttonSenors = (Button) findViewById(R.id.buttonSenors);


        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText("Wciśnięto przycisk1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText("Wciśnięto przycisk2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText("Wciśnięto przycisk3");
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView textView = (TextView) findViewById(R.id.textView2);
                EditText firstName = (EditText) findViewById(R.id.firstName);
                EditText lastName = (EditText) findViewById(R.id.lastName);
                textView.setText(firstName.getText() + " " + lastName.getText());
                Intent intent = new Intent(context, Obrazek.class);
                startActivity(intent);
            }
        });

        buttonSenors.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, Sensors.class);
                startActivity(intent);
            }
        });

        buttonPhones.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(context, Phones.class);
                startActivity(intent1);
            }
        });


        btnNewAlertDialogButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createAlertDialogWithButtons().show();
            }
        });

        btnNewAlertDialogWithList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createAlertDialogWithList(v).show();
            }
        });

        photoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                makePhoto(v);
            }
        });
    }
}