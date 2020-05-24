package com.example.tekbae;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class total_management_menu extends AppCompatActivity {

    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.total_management_menu);
        LinearLayout parentLayout = (LinearLayout) findViewById(R.id.total_layout);
        Button editbtn=(Button) findViewById(R.id.edit);
        for(int i=0;i<3;i++) {
            Button commuter=new Button(this);
            commuter.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            commuter.setBackgroundResource(android.R.color.black);
            parentLayout.addView(commuter);
        }
        editbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(total_management_menu.this,management_menu_edit.class);
                startActivity(intent);
            }
        });
    }
}