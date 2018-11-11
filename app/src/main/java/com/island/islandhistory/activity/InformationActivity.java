package com.island.islandhistory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.island.islandhistory.R;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView img_map,img_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_new);
        initialize();
        /*infoModels=new ArrayList<>();
        recycler_information=(RecyclerView)findViewById(R.id.recycler_info);
        informationAdapter=new InformationAdapter(this,infoModels);
        recycler_information.setLayoutManager(new LinearLayoutManager(this));
        recycler_information.setAdapter(informationAdapter);
        populate();*/

    }

    private void initialize() {
        img_map=(ImageView)findViewById(R.id.img_map);
        img_back=(ImageView)findViewById(R.id.img_back);

        img_map.setOnClickListener(this);
        img_back.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_map:
            Intent intent = new Intent(this, ImagViewActivity.class);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    InformationActivity.this,
                    img_map,
                    ViewCompat.getTransitionName(img_map));
            startActivity(intent, options.toBundle());
            break;
            case R.id.img_back:
                onBackPressed();
                break;
        }
    }
}
