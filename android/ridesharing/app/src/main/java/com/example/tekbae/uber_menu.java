package com.example.tekbae;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class uber_menu extends AppCompatActivity {

    private TextView idText, passwordText;
    private Button checkCommuteButton, checkSignButton, seePostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uber_manu);


        //idText = findViewById(R.id.idMain);
        //passwordText = findViewById(R.id.passMain);

        //Intent intent = getIntent();
        //String userID = intent.getStringExtra("userID");
        //String userPassword = intent.getStringExtra("userPassword");

       // idText.setText(userID+"님 환영합니다!");
        //passwordText.setText(userPassword);


        checkCommuteButton = (Button) findViewById(R.id.btn1);
        seePostButton = (Button) findViewById(R.id.btn2);


        seePostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(uber_menu.this,seePostActivity.class);
                startActivity(intent);
            }
        });
        checkCommuteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(uber_menu.this,uber_commute_select.class);
                startActivity(intent);
            }
        });
    }
}