package com.example.tekbae;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class manager_menu extends Activity {
    Button Management_Menu;
    Button URL_menu;
    Button back_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_menu);
        Management_Menu=(Button)findViewById(R.id.management_menu);
        URL_menu=(Button)findViewById(R.id.url_menu);
        back_menu=(Button)findViewById(R.id.main_menu);

        Management_Menu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(manager_menu.this,total_management_menu.class);
                startActivity(intent);
            }
        });

        URL_menu.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(manager_menu.this,Send_URL.class);
                startActivity(intent);
            }
        });

        back_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(manager_menu.this, FirstActivity.class);
                finish();
                startActivity(intent);
            }
        });
    }

}