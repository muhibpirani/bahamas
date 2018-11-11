package com.island.islandhistory.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.island.islandhistory.AppPreference;
import com.island.islandhistory.CustomDialogClass;
import com.island.islandhistory.DialogClick;
import com.island.islandhistory.R;
import com.island.islandhistory.model.Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VisualQuizActivity extends AppCompatActivity implements View.OnClickListener, DialogClick {
    int[] images;
    List<String> question;
    List<String> optionA, optionB, optionC, optionD, correctOption;
    private List<Questions> questionsList;
    private TextView txt_question;
    int currentQuest = 0;
    private TextView txt_queA, txt_queB, txt_queC, txt_queD, txt_quit, visual_txt_score, visual_txt_no;
    private ImageView visual_image;
    private int score = 0;
    private boolean toClick = true;
    private MediaPlayer mp;
    private AppPreference appPreference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visual_quiz);
        intialise();
        loadQuestion();
        setQuestion();

    }

    private void setQuestion() {
        if (currentQuest < questionsList.size()) {
            visual_image.setImageResource(images[currentQuest]);
            txt_question.setText(questionsList.get(currentQuest).getQuestionText());
            txt_queA.setText(questionsList.get(currentQuest).getOptA());
            txt_queB.setText(questionsList.get(currentQuest).getOptB());
            txt_queC.setText(questionsList.get(currentQuest).getOptC());
            txt_queD.setText(questionsList.get(currentQuest).getOptD());
            visual_txt_no.setText(String.format(getResources().getString(R.string.quetion_1_o_33), currentQuest + 1));
        } else {
            //Todo alert dialog with score / replay and share options.
            CustomDialogClass customDialogClass = new CustomDialogClass(this, this, "Main Menu", "Retry",
                    String.format(getResources().getString(R.string.score), score));
            customDialogClass.setCancelable(false);
            customDialogClass.show();
        }
    }

    private void loadQuestion() {
        Collections.addAll(question, getResources().getStringArray(R.array.visualQuizQuestion));
        Collections.addAll(optionA, getResources().getStringArray(R.array.visualA));
        Collections.addAll(optionB, getResources().getStringArray(R.array.visualB));
        Collections.addAll(optionC, getResources().getStringArray(R.array.visualC));
        Collections.addAll(optionD, getResources().getStringArray(R.array.visualD));
        Collections.addAll(correctOption, getResources().getStringArray(R.array.visualCorrect));
        for (int i = 0; i < question.size(); i++) {
            Questions questions = new Questions();
            questions.setQuestionText(question.get(i));
            questions.setOptA(optionA.get(i));
            questions.setOptB(optionB.get(i));
            questions.setOptC(optionC.get(i));
            questions.setOptD(optionD.get(i));
            questions.setCorrecOpt(correctOption.get(i));
            questionsList.add(questions);
        }

    }

    private void intialise() {
        question = new ArrayList<>();
        optionA = new ArrayList<>();
        optionB = new ArrayList<>();
        optionC = new ArrayList<>();
        optionD = new ArrayList<>();
        correctOption = new ArrayList<>();
        questionsList = new ArrayList<>();
        images = new int[]{R.drawable.ar_ancient_man, R.drawable.ar_avvy, R.drawable.ar_baha_men, R.drawable.ar_ira_storr, R.drawable.ar_kb, R.drawable.ar_landlord,
                R.drawable.ar_phil_stubbs, R.drawable.ar_priscilla_rollins, R.drawable.ar_ronnie, R.drawable.ar_veronica_bishop,
                R.drawable.loc_atlantis, R.drawable.loc_fort_charlotte, R.drawable.loc_fort_fincastle, R.drawable.loc_fort_nassau, R.drawable.loc_pompey_museum, R.drawable.loc_queen_victoria,
                R.drawable.loc_queens_staircase, R.drawable.loc_sir_milo_butler, R.drawable.pol_clarence_bain, R.drawable.pol_doris_johnson, R.drawable.pol_ivy_dumont, R.drawable.pol_janet_bostwick,
                R.drawable.pol_marguerite_pindling, R.drawable.pol_paul_adderley, R.drawable.pol_sir_lyden_pindling, R.drawable.pol_sir_milo_butler, R.drawable.pol_sir_randol_fawkes,
                R.drawable.ns_coat_of_arms, R.drawable.ns_flag, R.drawable.ns_blue_marlin, R.drawable.ns_flamingos, R.drawable.ns_lignum_vitae, R.drawable.ns_yellow_elder};

        txt_question = (TextView) findViewById(R.id.visual_txt_question);
        txt_queA = (TextView) findViewById(R.id.visual_txt_queA);
        txt_queB = (TextView) findViewById(R.id.visual_txt_queB);
        txt_queC = (TextView) findViewById(R.id.visual_txt_queC);
        txt_queD = (TextView) findViewById(R.id.visual_txt_queD);
        txt_quit = (TextView) findViewById(R.id.visual_txt_quit);
        visual_txt_score = (TextView) findViewById(R.id.visual_txt_score);
        visual_txt_no = (TextView) findViewById(R.id.visual_txt_no);
        visual_txt_score.setText(String.format(getResources().getString(R.string.score), score));
        visual_txt_no.setText(String.format(getResources().getString(R.string.quetion_1_o_33), currentQuest + 1));
        visual_image = (ImageView) findViewById(R.id.visual_image);
        txt_queA.setOnClickListener(this);
        txt_queB.setOnClickListener(this);
        txt_queC.setOnClickListener(this);
        txt_queD.setOnClickListener(this);
        txt_quit.setOnClickListener(this);
        appPreference=AppPreference.getInstance(this);
    }

    @Override
    public void onClick(View view) {
        if (toClick) {
            toClick = false;
            switch (view.getId()) {
                case R.id.visual_txt_queA:
                    if (correctOption.get(currentQuest).equals("a"))
                        correctAnswer("a");
                    else {
                        txt_queA.setBackgroundResource(R.drawable.incorrect_button);
                        incorrectanswer(correctOption.get(currentQuest));
                    }
                    break;
                case R.id.visual_txt_queB:
                    if (correctOption.get(currentQuest).equals("b"))
                        correctAnswer("b");
                    else {
                        txt_queB.setBackgroundResource(R.drawable.incorrect_button);
                        incorrectanswer(correctOption.get(currentQuest));
                    }
                    break;
                case R.id.visual_txt_queC:
                    if (correctOption.get(currentQuest).equals("c"))
                        correctAnswer("c");
                    else {
                        txt_queC.setBackgroundResource(R.drawable.incorrect_button);
                        incorrectanswer(correctOption.get(currentQuest));
                    }
                    break;
                case R.id.visual_txt_queD:
                    if (correctOption.get(currentQuest).equals("d"))
                        correctAnswer("d");
                    else {
                        txt_queD.setBackgroundResource(R.drawable.incorrect_button);
                        incorrectanswer(correctOption.get(currentQuest));
                    }

                    break;
                case R.id.visual_txt_quit:
                    CustomDialogClass customDialogClass = new CustomDialogClass(this,this,"Yes","NO",
                            "Are you sure you want to quit?");
                    customDialogClass.setCancelable(false);
                    customDialogClass.show();
                    break;
            }
        }
    }

    private void incorrectanswer(String correctOption) {
        if (currentQuest < questionsList.size()) {
            currentQuest++;
            playSong(R.raw.incorrect);
            switch (correctOption) {
                case "a":
                    txt_queA.setBackgroundResource(R.drawable.correct_button);
                    break;
                case "b":
                    txt_queB.setBackgroundResource(R.drawable.correct_button);
                    break;
                case "c":
                    txt_queC.setBackgroundResource(R.drawable.correct_button);
                    break;
                case "d":
                    txt_queD.setBackgroundResource(R.drawable.correct_button);
                    break;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    txt_queA.setBackgroundResource(R.drawable.norma_button);
                    txt_queB.setBackgroundResource(R.drawable.norma_button);
                    txt_queC.setBackgroundResource(R.drawable.norma_button);
                    txt_queD.setBackgroundResource(R.drawable.norma_button);
                    toClick = true;
                    setQuestion();
                }
            }, 400);
        }
    }

    private void correctAnswer(String correct) {
        if (currentQuest < questionsList.size()) {
            currentQuest++;
            playSong(R.raw.correct);
            score++;
            visual_txt_score.setText(String.format(getResources().getString(R.string.score), score));
            switch (correct) {
                case "a":
                    txt_queA.setBackground(getResources().getDrawable(R.drawable.correct_button));
                    break;
                case "b":
                    txt_queB.setBackground(getResources().getDrawable(R.drawable.correct_button));
                    break;
                case "c":
                    txt_queC.setBackground(getResources().getDrawable(R.drawable.correct_button));
                    break;
                case "d":
                    txt_queD.setBackground(getResources().getDrawable(R.drawable.correct_button));
                    break;
            }
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    txt_queA.setBackgroundResource(R.drawable.norma_button);
                    txt_queB.setBackgroundResource(R.drawable.norma_button);
                    txt_queC.setBackgroundResource(R.drawable.norma_button);
                    txt_queD.setBackgroundResource(R.drawable.norma_button);
                    toClick = true;
                    setQuestion();
                }
            }, 400);
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

    @Override
    public void onPOsitive(String s) {
        finish();
    }

    @Override
    public void onNegative(String s) {
        if (s.equals("Retry")) {
            currentQuest = 0;
            setQuestion();
        }
        toClick = true;

    }

    @Override
    public void onBackPressed() {
        CustomDialogClass customDialogClass = new CustomDialogClass(this,this,"Yes","NO",
                "Are you sure you want to quit?");
        customDialogClass.setCancelable(false);
        customDialogClass.show();
    }
}
