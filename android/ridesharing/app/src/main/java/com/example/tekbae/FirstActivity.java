package com.example.tekbae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button adminButton = (Button) findViewById(R.id.adminButton);
        Button uberButton = (Button) findViewById(R.id.uberButton);
        Button smallTekbaeButton = (Button) findViewById(R.id.smallTekbaeButton);


        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(FirstActivity.this,LoginActivity.class);
                FirstActivity.this.startActivity(registerIntent);
            }
        });

        uberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(FirstActivity.this,LoginActivity.class);
                FirstActivity.this.startActivity(registerIntent);
            }
        });
        smallTekbaeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smallIntent =new Intent(FirstActivity.this,RegisterActivity.class);
                startActivity(smallIntent);
            }
        });
    }
}
