package com.island.islandhistory.activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.island.islandhistory.AppPreference;
import com.island.islandhistory.CustomDialogClass;
import com.island.islandhistory.DialogClick;
import com.island.islandhistory.R;
import com.island.islandhistory.model.Questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextQuizActivity extends AppCompatActivity implements View.OnClickListener, DialogClick {
    String[] questions;
    List<String> question;
    List<String> optionA, optionB, optionC, optionD, correctOption;
    private List<Questions> questionsList;
    private TextView txt_question;
    ArrayList<Integer> number;
    int currentQuest = 0;
    private TextView txt_queA, txt_queB, txt_queC, txt_queD, txt_quit, txt_no;
    int score = 0;
    private TextView txt_score;
    private boolean toClick = true;
    private MediaPlayer mp;
    private AppPreference appPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_quiz);
        number = new ArrayList<Integer>();
        for (int i = 0; i < 30; ++i) number.add(i);
        Collections.shuffle(number);
        initialize();
        loadQuestion();
        setQuestion();

    }

    private void initialize() {
        question = new ArrayList<>();
        optionA = new ArrayList<>();
        optionB = new ArrayList<>();
        optionC = new ArrayList<>();
        optionD = new ArrayList<>();
        correctOption = new ArrayList<>();
        questionsList = new ArrayList<>();
        txt_question = (TextView) findViewById(R.id.txt_question);
        txt_queA = (TextView) findViewById(R.id.txt_queA);
        txt_queB = (TextView) findViewById(R.id.txt_queB);
        txt_queC = (TextView) findViewById(R.id.txt_queC);
        txt_queD = (TextView) findViewById(R.id.txt_queD);
        txt_quit = (TextView) findViewById(R.id.txt_quit);
        txt_score = (TextView) findViewById(R.id.txt_score);
        txt_no = (TextView) findViewById(R.id.txt_no);
        txt_score.setText(String.format(getResources().getString(R.string.score), score));
        txt_no.setText(String.format(getResources().getString(R.string.question_1_of_30), currentQuest + 1));
        txt_queA.setOnClickListener(this);
        txt_queB.setOnClickListener(this);
        txt_queC.setOnClickListener(this);
        txt_queD.setOnClickListener(this);
        txt_quit.setOnClickListener(this);
        appPreference=AppPreference.getInstance(this);
    }

    private void loadQuestion() {
        Collections.addAll(question, getResources().getStringArray(R.array.questionsText));
        Collections.addAll(optionA, getResources().getStringArray(R.array.optionsA));
        Collections.addAll(optionB, getResources().getStringArray(R.array.optionsB));
        Collections.addAll(optionC, getResources().getStringArray(R.array.optionsC));
        Collections.addAll(optionD, getResources().getStringArray(R.array.optionsD));
        Collections.addAll(correctOption, getResources().getStringArray(R.array.correctText));
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

    private void setQuestion() {
        if (currentQuest < 30) {
            txt_question.setText(questionsList.get(number.get(currentQuest)).getQuestionText());
            txt_queA.setText(questionsList.get(number.get(currentQuest)).getOptA());
            txt_queB.setText(questionsList.get(number.get(currentQuest)).getOptB());
            txt_queC.setText(questionsList.get(number.get(currentQuest)).getOptC());
            txt_queD.setText(questionsList.get(number.get(currentQuest)).getOptD());
            txt_no.setText(String.format(getResources().getString(R.string.question_1_of_30), currentQuest + 1));
        } else {
            //Todo alert dialog with score / replay and share options.
            CustomDialogClass customDialogClass = new CustomDialogClass(this,this,"Main Menu","Retry",
                    String.format(getResources().getString(R.string.score), score));
            customDialogClass.setCancelable(false);
            customDialogClass.show();
        }
    }

    @Override
    public void onClick(View view) {
        if (toClick) {
            toClick = false;
            switch (view.getId()) {
                case R.id.txt_queA:
                    if (correctOption.get(number.get(currentQuest)).equals("a"))
                        correctAnswer("a");
                    else {
                        txt_queA.setBackgroundResource(R.drawable.incorrect_button);
                        incorrectanswer(correctOption.get(number.get(currentQuest)));
                    }
                    break;
                case R.id.txt_queB:
                    if (correctOption.get(number.get(currentQuest)).equals("b"))
                        correctAnswer("b");
                    else {
                        txt_queB.setBackgroundResource(R.drawable.incorrect_button);
                        incorrectanswer(correctOption.get(number.get(currentQuest)));
                    }
                    break;
                case R.id.txt_queC:
                    if (correctOption.get(number.get(currentQuest)).equals("c"))
                        correctAnswer("c");
                    else {
                        txt_queC.setBackgroundResource(R.drawable.incorrect_button);
                        incorrectanswer(correctOption.get(number.get(currentQuest)));
                    }
                    break;
                case R.id.txt_queD:
                    if (correctOption.get(number.get(currentQuest)).equals("d"))
                        correctAnswer("d");
                    else {
                        txt_queD.setBackgroundResource(R.drawable.incorrect_button);
                        incorrectanswer(correctOption.get(number.get(currentQuest)));
                    }

                    break;
                case R.id.txt_quit:
                    CustomDialogClass customDialogClass = new CustomDialogClass(this,this,"Yes","NO",
                            "Are you sure you want to quit?");
                    customDialogClass.setCancelable(false);
                    customDialogClass.show();
                    break;
            }
        }

    }

    private void incorrectanswer(String correctOption) {
        if (currentQuest < 30) {
            playSong(R.raw.incorrect);
            currentQuest++;
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
        else {
        }
    }

    private void correctAnswer(String correct) {
        if (currentQuest < 30) {
            currentQuest++;
            playSong(R.raw.correct);
            score++;
            txt_score.setText(String.format(getResources().getString(R.string.score), score));
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
        else
        {

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
        if(mp!=null)
        mp.release();
    }

    @Override
    public void onPOsitive(String s) {
        finish();

    }

    @Override
    public void onNegative(String s) {
        if (s.equals("Retry"))
        {
            currentQuest=0;
            setQuestion();
        }
        toClick=true;


    }

    @Override
    public void onBackPressed() {
        CustomDialogClass customDialogClass = new CustomDialogClass(this,this,"Yes","NO",
                "Are you sure you want to quit?");
        customDialogClass.setCancelable(false);
        customDialogClass.show();
    }
}
