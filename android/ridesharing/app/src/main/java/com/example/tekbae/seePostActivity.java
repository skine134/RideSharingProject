package com.example.tekbae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class seePostActivity extends AppCompatActivity {

    private ListView listView;
    private postListAdapter adapter;
    private List<Post> postList;
    private CheckBox checkBox;
    private Button mapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_post);

        Intent intent = getIntent();
        checkBox = (CheckBox) findViewById(R.id.postCheck) ;
        listView = (ListView)findViewById(R.id.listView);
        postList = new ArrayList<Post>();


        postList.add(new Post("사용자 아이디","이르음", "주소오"," 전화번호오", "택배물정보오","거리이",false));

        adapter = new postListAdapter(getApplicationContext(), postList);
        listView.setAdapter(adapter);


        mapButton = findViewById(R.id.mapButton);

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(seePostActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }
}
