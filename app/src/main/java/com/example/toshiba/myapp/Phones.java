package com.example.toshiba.myapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Phones extends Activity {
    private ListView lv;
    private String[] lang;
    private String[] phrases;

    private void initResources() {
        Resources res = getResources();
        lang = res.getStringArray(R.array.phones);
        phrases = res.getStringArray(R.array.hello_world);
    }

    private void initLanguagesListView() {
        final Context context;
        context = getApplicationContext();

        lv.setAdapter(new ArrayAdapter<String>(getBaseContext(),
                android.R.layout.simple_list_item_1, lang));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
                if(pos == 0) {
                    Intent intent = new Intent(context, IPhone8.class);
                    startActivity(intent);
                }
                else if(pos == 1) {
                    Intent intent = new Intent(context, IPhoneX.class);
                    startActivity(intent);
                }
                else if(pos == 2) {
                    Intent intent = new Intent(context, SamsungS8.class);
                    startActivity(intent);
                }

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phones);
        final Context context;
        context = getApplicationContext();
        Button buttonBack = (Button) findViewById(R.id.buttonBack);

        lv = (ListView) findViewById(R.id.phones);
        initResources();
        initLanguagesListView();

        buttonBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivityApp.class);
                startActivity(intent);
            }
        });
    }
}
