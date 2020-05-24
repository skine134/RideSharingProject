package com.example.tekbae;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.annotation.Nullable;

public class uber_edit extends Activity {
    Button btn1, back;
    ScrollView scrollview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uber_edit);
        Intent intent = getIntent();

        btn1 = (Button) findViewById(R.id.button1_1);
        btn1.setText(intent.getStringExtra("textField").toString());
        back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        LinearLayout layout1 = (LinearLayout) findViewById(R.id.manList);

        layout1.setOrientation(LinearLayout.VERTICAL);


        for(int i = 1; i < 9 ; i ++){
            Button personbtn = new Button(this);
            personbtn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            personbtn.setText("button" );
            personbtn.setTextSize(40);
            layout1.addView(personbtn);
        }
    }
}
