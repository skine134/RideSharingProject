package com.example.tekbae;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class seePostActivity extends AppCompatActivity {

    private Button mapButton, searchButton;
    private EditText searchTxt;
    private List<Uber> postList;
    private postListAdapter adapter;
    private ListView postListView;




    // 데이터베이스 관련
    private String output, date_info;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_post);


        mapButton = (Button) findViewById(R.id.mapButton);
        searchTxt = (EditText) findViewById(R.id.searchTxt);
        searchButton = (Button) findViewById(R.id.btnSearch);
        postListView = (ListView) findViewById(R.id.postlistView);

        postList = new ArrayList<Uber>();


/*<<<<<<< HEAD
        try {
           String output=new Connection("Uber","select",LoginActivity.map.get("UberName"),null,null,null).execute(BuildConfig.SERVER_HOST+"/regosterUser.php?").get();
           LoginActivity.map.put("list",output);
           String[] arr=output.split("/");
=======
        //ReceiverName,ReceiverNumber,ReceiverAddress,Item,SenderAddress,SenderNumber,DeliverCheck,UberId,UberName,postCheck,Date,No
        //"받는분","받는분 번호", "받는분 주소", "배송품", "보내는분 주소","보내는분 번호", false, 우버이름, 배송체크,  "날짜", "넘버"
>>>>>>> origin/sunghoon_UrlUpdate*/

        //오늘날짜
        Date currentTime = Calendar.getInstance().getTime();
        date_info = new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(currentTime);

        //고객 리스트 DB전체 받아오기
        try {
            output = new Connection("Uber", "select", LoginActivity.map.get("UberId"),null, null, null)
                    .execute(BuildConfig.SERVER_HOST+"/regosterUser.php?").get();
            LoginActivity.map.put("list",output);
            String[] arr=output.split("/");
            for(int i =0;i<arr.length;i++){
                String[] arr2=arr[i].split(",");
                if(arr2.length>1)
                postList.add(new Uber(arr2[0], Integer.parseInt(arr2[1]), arr2[2], arr2[3], arr2[4], Integer.parseInt(arr2[5]), false, arr2[7], arr2[8], false, arr2[10], arr2[11]));
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
/*
<<<<<<< HEAD
        adapter = new postListAdapter(getApplicationContext(), postList);
        listView.setAdapter(adapter);
=======
*/

        adapter = new postListAdapter(getApplicationContext(), postList);
        postListView.setAdapter(adapter);



        // 지도로 보기 버튼 클릭시 이벤트
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(seePostActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

    }
}