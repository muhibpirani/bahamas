package com.island.islandhistory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.island.islandhistory.AppPreference;
import com.island.islandhistory.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txt_information, txt_sound;
    private ImageView img_sound;
    private LinearLayout ll_text, ll_visual, ll_map, ll_info, ll_sound;
    private AppPreference appPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        //findViews
        txt_information = (TextView) findViewById(R.id.txt_information);
        ll_text = (LinearLayout) findViewById(R.id.ll_text);
        ll_visual = (LinearLayout) findViewById(R.id.ll_visual);
        ll_map = (LinearLayout) findViewById(R.id.ll_map);
        ll_info = (LinearLayout) findViewById(R.id.ll_info);
        ll_sound = (LinearLayout) findViewById(R.id.ll_sound);
        txt_sound = (TextView) findViewById(R.id.txt_sound);
        img_sound = (ImageView) findViewById(R.id.img_sound);


        //onClick
        ll_text.setOnClickListener(this);
        ll_info.setOnClickListener(this);
        ll_map.setOnClickListener(this);
        ll_visual.setOnClickListener(this);
        ll_sound.setOnClickListener(this);

        appPreference = AppPreference.getInstance(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_info:
                Intent intent = new Intent(this, InformationActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_text:
                Intent intent1 = new Intent(this, TextQuizActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_map:
                Intent intent3 = new Intent(this, MapQuizActivity.class);
                startActivity(intent3);
                break;
            case R.id.ll_visual:
                Intent intent2 = new Intent(this, VisualQuizActivity.class);
                startActivity(intent2);
                break;
            case R.id.ll_sound:
                if (appPreference.getSound()) {
                    txt_sound.setText("Sound: OFF");
                    img_sound.setImageResource(R.drawable.ic_volume_off_24dp);
                    appPreference.setSound(false);
                } else {
                    txt_sound.setText("Sound: ON");
                    img_sound.setImageResource(R.drawable.ic_volume_up_24dp);
                    appPreference.setSound(true);

                }
                break;
        }
    }
}

