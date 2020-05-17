package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class HelpActivity extends AppCompatActivity {

    private ImageView backBtn;
    private ImageView developersBtn;
    private ImageView contactBtn;
    private  ImageView bugBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        backBtn       = (ImageView) findViewById(R.id.backBtn);
        developersBtn = (ImageView) findViewById(R.id.developersBtn);
        contactBtn    = (ImageView) findViewById(R.id.contactBtn);
        bugBtn        = (ImageView) findViewById(R.id.bugBtn);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HelpActivity.this, SettingMenu.class));
            }
        });

        developersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HelpActivity.this,
                        "This app was developed by: Alexandru Cremeneanu, Robert Dinica, Cezar Dimoiu", Toast.LENGTH_LONG).show();
            }
        });

        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HelpActivity.this,
                        "If you want to contact us please send a mail to internethexplores@gmail.com", Toast.LENGTH_LONG).show();
            }
        });

        bugBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HelpActivity.this,
                        "If you find a bug please send a mail to internethexplores@gmail.com", Toast.LENGTH_LONG).show();
            }
        });

    }
}
