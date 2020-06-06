package com.example.tekbae;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class seePostActivity extends AppCompatActivity {

    private ListView listView;
    private postListAdapter adapter;

    private List<Post> postList;
    private CheckBox postCheck;
    private Button mapButton;
    private EditText findtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_post);
        postCheck = (CheckBox) findViewById(R.id.postcheck);
        listView = (ListView)findViewById(R.id.Items);
        findtext=(EditText)findViewById(R.id.search);
        mapButton = findViewById(R.id.mapButton);

        postList = new ArrayList<Post>();

        //ReceiverName,ReceiverNumber,ReceiverAddress,Item,SenderAddress,SenderNumber,DeliverCheck,UberId,UberName,postCheck,Date,No
        //"배송자 이름","배송자 번호", "배송자 주소","물건", "수령자 주소","수령자 번호",false,false,"날짜"
        try {
           String output=new Connection("Uber","select",LoginActivity.map.get("UberName"),null,null,null).execute("http://prawnguns.dothome.co.kr/regosterUser.php?").get();
            String[] arr=output.split("/");
            for (int i=0;i<arr.length;i++) {
                String[] arr2 = arr[i].split(",");
                postList.add(new Post(arr2[0], Integer.parseInt(arr2[1]), arr2[2], arr2[3], arr2[4], Integer.parseInt(arr2[5]), false, false, arr2[9]));
            }
            } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }






        adapter = new postListAdapter(getApplicationContext(), postList);
        listView.setAdapter(adapter);






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
