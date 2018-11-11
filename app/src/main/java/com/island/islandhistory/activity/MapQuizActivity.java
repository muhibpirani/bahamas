package com.island.islandhistory.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.island.islandhistory.AppPreference;
import com.island.islandhistory.CustomDialogClass;
import com.island.islandhistory.DialogClick;
import com.island.islandhistory.ItemClick;
import com.island.islandhistory.MapQuizAdapter;
import com.island.islandhistory.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MapQuizActivity extends AppCompatActivity implements ItemClick, View.OnClickListener, DialogClick {
    private RecyclerView recycler_options;
    List<String> options, options1, options2, options3, options4, options5, options6, options7, options8, options9, options10, options11, options12;
    List<String> options13, options14, options15;
    List<String> answers;
    private HashMap<Integer, List<String>> questionOptions;
    private MapQuizAdapter mapQuizAdapter;
    private GridLayoutManager gridLayoutManager;
    String userAnswer = "";
    private TextView txt_map_answer;
    private TextView txt_map_score, txt_map_enter, txt_map_quit;
    private ImageButton img_map_back;
    int score = 0, currntQuestion = 0;
    List<Integer> usedButtons;
    int[] images;
    private ImageView img_question_map;
    private MediaPlayer mp;
    private AppPreference appPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_quiz);
        intialize();
        populate();
        /*for(int i=0;i<16;i++)
            strings.add("a");*/
        mapQuizAdapter = new MapQuizAdapter(this, options, this, usedButtons);
        recycler_options = (RecyclerView) findViewById(R.id.recycler_options);
        txt_map_answer = (TextView) findViewById(R.id.txt_map_answer);
        txt_map_answer.setText("");
        txt_map_enter = (TextView) findViewById(R.id.txt_map_enter);
        txt_map_quit = (TextView) findViewById(R.id.txt_map_quit);
        img_map_back = (ImageButton) findViewById(R.id.imag_map_back);
        txt_map_quit.setOnClickListener(this);
        txt_map_enter.setOnClickListener(this);
        img_map_back.setOnClickListener(this);
        /*
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager();
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);*/
        gridLayoutManager = new GridLayoutManager(this, 5);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (options.size()) {
                    case 10:
                        return 1;
                    default:
                        return 1;
                }
            }
        });
        recycler_options.setLayoutManager(gridLayoutManager);
        recycler_options.setAdapter(mapQuizAdapter);
        mapQuizAdapter.notifyDataSetChanged();


    }

    private void intialize() {
        answers = new ArrayList<>();
        options = new ArrayList<>();
        options1 = new ArrayList<>();
        options2 = new ArrayList<>();
        options3 = new ArrayList<>();
        options4 = new ArrayList<>();
        options5 = new ArrayList<>();
        options6 = new ArrayList<>();
        options7 = new ArrayList<>();
        options8 = new ArrayList<>();
        options9 = new ArrayList<>();
        options10 = new ArrayList<>();
        options11 = new ArrayList<>();
        options12 = new ArrayList<>();
        options13 = new ArrayList<>();
        options14 = new ArrayList<>();
        options15 = new ArrayList<>();
        usedButtons = new ArrayList<>();
        appPreference=AppPreference.getInstance(this);
        txt_map_score = (TextView) findViewById(R.id.txt_map_score);
        txt_map_score.setText(String.format(getResources().getString(R.string.score), score));
        questionOptions = new HashMap<>();
        img_question_map = (ImageView) findViewById(R.id.img_question_map);
        images = new int[]{R.drawable.map_abacos, R.drawable.map_acklins_crooked, R.drawable.map_andros, R.drawable.map_berryislands,
                R.drawable.map_bimini, R.drawable.map_catisland, R.drawable.map_eleutheraharbour, R.drawable.map_exumas,
                R.drawable.map_grandbahama, R.drawable.map_inagua, R.drawable.map_longisland, R.drawable.map_mayaguana,
                R.drawable.map_nassau, R.drawable.map_rumcay, R.drawable.map_sansalvador};


    }

    private void populate() {
        Collections.addAll(options, getResources().getStringArray(R.array.mapQuizOptions));
        Collections.addAll(options1, getResources().getStringArray(R.array.mapQuizOptions1));
        Collections.addAll(options2, getResources().getStringArray(R.array.mapQuizOptions2));
        Collections.addAll(options3, getResources().getStringArray(R.array.mapQuizOptions3));
        Collections.addAll(options4, getResources().getStringArray(R.array.mapQuizOptions4));
        Collections.addAll(options5, getResources().getStringArray(R.array.mapQuizOptions5));
        Collections.addAll(options6, getResources().getStringArray(R.array.mapQuizOptions6));
        Collections.addAll(options7, getResources().getStringArray(R.array.mapQuizOptions7));
        Collections.addAll(options8, getResources().getStringArray(R.array.mapQuizOptions8));
        Collections.addAll(options9, getResources().getStringArray(R.array.mapQuizOptions9));
        Collections.addAll(options10, getResources().getStringArray(R.array.mapQuizOptions10));
        Collections.addAll(options11, getResources().getStringArray(R.array.mapQuizOptions11));
        Collections.addAll(options12, getResources().getStringArray(R.array.mapQuizOptions12));
        Collections.addAll(options13, getResources().getStringArray(R.array.mapQuizOptions13));
        Collections.addAll(options14, getResources().getStringArray(R.array.mapQuizOptions14));

        questionOptions.put(0, options);
        questionOptions.put(1, options1);
        questionOptions.put(2, options2);
        questionOptions.put(3, options3);
        questionOptions.put(4, options4);
        questionOptions.put(5, options5);
        questionOptions.put(6, options6);
        questionOptions.put(7, options7);
        questionOptions.put(8, options8);
        questionOptions.put(9, options9);
        questionOptions.put(10, options10);
        questionOptions.put(11, options11);
        questionOptions.put(12, options12);
        questionOptions.put(13, options13);
        questionOptions.put(14, options14);
        Collections.addAll(answers, getResources().getStringArray(R.array.mapQuizAnswers));
    }

    @Override
    public void onClick(String a, int adapterPosition) {
        usedButtons.add(adapterPosition);
        mapQuizAdapter.notifyDataSetChanged();
        txt_map_answer.setText(txt_map_answer.getText().toString() + a);

    }

    public String backSpace(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
            if (usedButtons != null && usedButtons.size() > 0) {
                usedButtons.remove(usedButtons.size() - 1);
                mapQuizAdapter.notifyDataSetChanged();
            }
        }
        return str;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_map_quit:
                CustomDialogClass customDialogClass = new CustomDialogClass(this,this,"Yes","NO",
                        "Are you sure you want to quit?");
                customDialogClass.setCancelable(false);
                customDialogClass.show();
                break;
            case R.id.txt_map_enter:
                if (answers.get(currntQuestion).equals(txt_map_answer.getText().toString())) {
                    score++;
                    currntQuestion++;
                    txt_map_score.setText(String.format(getResources().getString(R.string.score), score));
                    txt_map_answer.setTextColor(ContextCompat.getColor(this, R.color.greenButton));
                    playSong(R.raw.correct);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            txt_map_answer.setTextColor(ContextCompat.getColor(MapQuizActivity.this, android.R.color.white));
                            loadNext();

                        }
                    }, 300);
                } else {
                    txt_map_answer.setTextColor(ContextCompat.getColor(this, R.color.redButton));
                    playSong(R.raw.incorrect);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            txt_map_answer.setTextColor(ContextCompat.getColor(MapQuizActivity.this, android.R.color.white));

                        }
                    }, 300);

                }
                break;
            case R.id.imag_map_back:
                txt_map_answer.setText(backSpace(txt_map_answer.getText().toString()));
                break;
        }
    }

    private void loadNext() {
        if(currntQuestion<questionOptions.size()) {
            options.clear();
            txt_map_answer.setTextColor(ContextCompat.getColor(this, android.R.color.white));
            img_question_map.setImageResource(images[currntQuestion]);
            usedButtons.clear();
            txt_map_answer.setText("");
            options.addAll(questionOptions.get(currntQuestion));
            mapQuizAdapter.notifyDataSetChanged();
        }
        else
        {
            CustomDialogClass customDialogClass = new CustomDialogClass(this, this, "Main Menu", "Retry",
                    String.format(getResources().getString(R.string.score), score));
            customDialogClass.setCancelable(false);
            customDialogClass.show();
        }

    }

    @Override
    public void onBackPressed() {
        CustomDialogClass customDialogClass = new CustomDialogClass(this, this, "Yes", "NO",
                "Are you sure you want to quit?");
        customDialogClass.setCancelable(false);
        customDialogClass.show();
    }

    @Override
    public void onPOsitive(String s) {
        finish();
    }

    @Override
    public void onNegative(String s) {
        if (s.equals("Retry")) {
            currntQuestion = 0;
            loadNext();
        }

    }
    public void playSong(int songIndex) {
        // Play song
        if (appPreference.getSound()) {
            if (mp != null)
                mp.reset();// stops any current playing song
            mp = MediaPlayer.create(getApplicationContext(), songIndex);// create's
            // new
            // mediaplayer
            // with
            // song.

            mp.start(); // starting mediaplayer
        }

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mp != null)
            mp.release();
    }
}
