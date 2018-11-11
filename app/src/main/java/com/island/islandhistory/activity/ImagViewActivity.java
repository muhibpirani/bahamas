package com.island.islandhistory.activity;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.island.islandhistory.R;
import com.island.islandhistory.ZoomableImageView;

public class ImagViewActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txt_close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imag_view);
        txt_close=(TextView)findViewById(R.id.txt_close);
        txt_close.setOnClickListener(this);
        ZoomableImageView touch = (ZoomableImageView)findViewById(R.id.img_view_map);
        touch.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.bahamas_map));
    }

    @Override
    public void onClick(View view) {
        onBackPressed();
    }
}
