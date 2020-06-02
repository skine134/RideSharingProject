package com.example.tekbae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Button adminButton = (Button) findViewById(R.id.adminButton);
        Button uberButton = (Button) findViewById(R.id.uberButton);
        Button smallTekbaeButton = (Button) findViewById(R.id.smallTekbaeButton);
        ArrayList<String> arr=new ArrayList<String>();
        arr.add("he");
        arr.add("b");
        String tmp="";
        for(int  i=0;i<arr.size();i++){
            tmp+=arr.get(i);
            if(i!=arr.size()-1)
                tmp+=",";
        }
        new Connection("User","Insert",tmp,null,null,null);

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
