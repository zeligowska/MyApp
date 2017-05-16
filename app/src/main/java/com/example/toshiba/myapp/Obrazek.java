package com.example.toshiba.myapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import static com.example.toshiba.myapp.R.id.imageView;

public class Obrazek extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.obrazek);
        Context context;
        context = getApplicationContext();
        Intent intent = new Intent(context, Obrazek.class);
        startActivity(intent);

        Button button4 = (Button) findViewById(R.id.button4);


        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ImageView imageView = (ImageView) findViewById(R.id.imageView);
                imageView.setImageResource(R.drawable.android2);
            }
        });

    }


}
