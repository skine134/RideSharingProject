package com.example.tekbae;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class total_management_menu extends AppCompatActivity {

    private ListView listView;
    private UberListAdapter uberadapter;
    private List<Uber> uberList;
    private CheckBox postCheck;
    private Button mapButton;
    private EditText findtext;
    private Button searchBtn;
    private Spinner uberListSpinner;
    private int spinnerInt;
    ArrayList<String> uberListArray;
    ArrayAdapter<String> uberListArrayAdapter;



    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.total_management_menu);
        LinearLayout parentLayout = (LinearLayout) findViewById(R.id.total_layout);
        Button editbtn = (Button) findViewById(R.id.edit);
        uberList = new ArrayList<Uber>();
        listView = (ListView) findViewById(R.id.uberlist);
        searchBtn = (Button) findViewById(R.id.commuter4);

        uberListArray = new ArrayList<>();
        uberListArray.add("전체");
        uberListArray.add("수령자 이름");
        uberListArray.add("수령자 번호");
        uberListArray.add("택배물");
        uberListArray.add("배송자 번호");
        uberListArray.add("배송자 ID");
        uberListArray.add("배송자 이름");
        uberListArray.add("배송 날짜");
        uberListArray.add("번호");
        uberListArray.add("배송 가능");
        uberListArray.add("수령 가능");



        uberListArrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,
                uberListArray);


        uberListSpinner = (Spinner)findViewById(R.id.uberListSpinner);
        uberListSpinner.setAdapter(uberListArrayAdapter);

        uberListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerInt = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        for (int i = 0; i < 3; i++) {
            Button commuter = new Button(this);
            commuter.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            commuter.setBackgroundResource(android.R.color.black);
            parentLayout.addView(commuter);
        }
        editbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(total_management_menu.this, management_menu_edit.class);
                startActivity(intent);
            }
        });


        String outUber = null;
        try {
            outUber = new Connection("Uber", "select", LoginActivity.map.get(""), "All", null, null).execute("http://prawnguns.dothome.co.kr/regosterUser.php?").get();
            String[] arr = outUber.split("/");


            for (int i = 0; i < arr.length; i++) {
                String[] arr2 = arr[i].split(",");
                uberList.add(new Uber(arr2[0], Integer.parseInt(arr2[1]), arr2[2], arr2[3], arr2[4], Integer.parseInt(arr2[5]), false, arr2[7], arr2[8], false, arr2[10], arr2[11]));
            }
            uberadapter = new UberListAdapter(getApplicationContext(), uberList);
            listView.setAdapter(uberadapter);


        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///////////////
                {

                    uberList.clear();

                    Toast.makeText(getApplicationContext(), uberListArray.get(spinnerInt) + "가 선택되었습니다.",
                            Toast.LENGTH_SHORT).show();


                    String searchUber = null;
                    try {
                        searchUber = new Connection("Uber", "select", LoginActivity.map.get(""), "All", null, null).execute("http://prawnguns.dothome.co.kr/regosterUser.php?").get();
                        String[] arr = searchUber.split("/");


                        for (int i = 0; i < 4; i++) {
                            String[] arr2 = arr[i].split(",");
                            uberList.add(new Uber(arr2[0], Integer.parseInt(arr2[1]), arr2[2], arr2[3], arr2[4], Integer.parseInt(arr2[5]), false, arr2[7], arr2[8], false, arr2[10], arr2[11]));
                        }
                        uberadapter = new UberListAdapter(getApplicationContext(), uberList);
                        listView.setAdapter(uberadapter);


                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                ////////////
            }
        });

    }
}