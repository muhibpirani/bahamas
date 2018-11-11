package com.island.islandhistory;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class CustomDialogClass extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button yes, no;
    DialogClick dialogClick;
    String yesText,noText,diaText;
    TextView txt_dia;

    public CustomDialogClass(Activity a,DialogClick dialogClick,String yes,String no,String diaTextt) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
        this.dialogClick=dialogClick;
        yesText=yes;
        noText=no;
        diaText=diaTextt;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        txt_dia=(TextView)findViewById(R.id.txt_dia);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);
        yes.setText(yesText);
        no.setText(noText);
        txt_dia.setText(diaText);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                dialogClick.onPOsitive(yes.getText().toString());
                break;
            case R.id.btn_no:
                dialogClick.onNegative(no.getText().toString());
                break;
            default:
                break;
        }
        dismiss();
    }
}