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
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class total_management_menu extends AppCompatActivity {
    public static HashMap<String,List<Uber>> selected_list=new HashMap<String, List<Uber>>();
    private ListView listView;
    private UberListAdapter uberadapter;
    private List<Uber> uberList;
    private CheckBox postCheck;
    private Button all_select;
    private Button searchBtn;
    private Spinner uberListSpinner;
    private int spinnerInt;
    private EditText searchTEXT;
    private String searchString;
    ArrayList<String> uberListArray;
    ArrayAdapter<String> uberListArrayAdapter;



    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.total_management_menu);
        LinearLayout parentLayout = (LinearLayout) findViewById(R.id.total_layout);
        Button editbtn = (Button) findViewById(R.id.edit);
        Button today_commuter=(Button)findViewById(R.id.commuter);
        uberList = new ArrayList<Uber>();
        listView = (ListView) findViewById(R.id.uberlist);
        searchBtn = (Button) findViewById(R.id.commuter4);
        all_select=(Button)findViewById(R.id.all_select);

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
        editbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(total_management_menu.this, management_menu_edit.class);
                List<Uber> selected_list=new ArrayList<Uber>();
                List<Uber> tmp=uberadapter.getList();
                for(int i=0;i<tmp.size();i++){
                    if(tmp.get(i).isSelected())
                        selected_list.add(tmp.get(i));
                }
                total_management_menu.selected_list.put("selected",selected_list);
                startActivity(intent);
            }
        });
        today_commuter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(total_management_menu.this, uber_edit.class);
                startActivity(intent);
            }
        });

        String outUber = null;
        try {
            outUber = new Connection("Uber", "select", LoginActivity.map.get(""), "All", null, null).execute(BuildConfig.SERVER_HOST+"/regosterUser.php?").get();
            String[] arr = outUber.split("/");


            for (int i = 0; i < arr.length; i++) {
                String[] arr2 = arr[i].split(",");
                if(arr2.length>1)
                //ReceiverName,ReceiverNumber,ReceiverAddress,Item,SenderAddress,SenderNumber,DeliverCheck,UberId,UberName,postCheck,Date,No
                //"배송자 이름","배송자 번호", "배송자 주소","물건", "수령자 주소","수령자 번호",false,false,"날짜"
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
                searchTEXT = (EditText) findViewById(R.id.searchEdit);
                searchString = searchTEXT.getText().toString();
                List<Uber> uberList2=new ArrayList<Uber>();
                Toast.makeText(getApplicationContext(), searchString+uberListArray.get(spinnerInt) + "가 선택되었습니다.",Toast.LENGTH_SHORT).show();
                switch(spinnerInt){
                    case 0:                 //전체
                        uberList2=uberList;
                        break;
                    case 1:                 //수령자 이름
                        for(int i=0;i<uberList.size();i++){
                            if(uberList.get(i).getReceiverName().equals(searchString))
                                uberList2.add(uberList.get(i));
                        }
                        break;
                    case 2:                 //수령자 번호
                        for(int i=0;i<uberList.size();i++){
                            if(("0"+uberList.get(i).getReceiverNumber()).equals(searchString))
                                uberList2.add(uberList.get(i));
                        }
                        break;
                    case 3:                 //택배물
                        for(int i=0;i<uberList.size();i++){
                            if(uberList.get(i).getItem().equals(searchString))
                                uberList2.add(uberList.get(i));
                        }
                        break;
                    case 4:                 //배송자 번호
                        for(int i=0;i<uberList.size();i++){
                            if(("0"+uberList.get(i).getSenderNumber()).equals(searchString))
                                uberList2.add(uberList.get(i));
                        }
                        break;

                    case 5:                 //Uber ID
                        for(int i=0;i<uberList.size();i++){
                            if(uberList.get(i).getUberId().equals(searchString))
                                uberList2.add(uberList.get(i));
                        }
                        break;
                    case 6:                 //Uber 이름
                        for(int i=0;i<uberList.size();i++){
                            if(uberList.get(i).getUberName().equals(searchString))
                                uberList2.add(uberList.get(i));
                        }
                        break;
                    case 7:                 //배송 날짜
                        for(int i=0;i<uberList.size();i++){
                            if(uberList.get(i).getDate().equals(searchString))
                                uberList2.add(uberList.get(i));
                        }
                        break;
                    case 8:                 //번호
                        for(int i=0;i<uberList.size();i++){
                            if(uberList.get(i).getNo().equals(searchString))
                                uberList2.add(uberList.get(i));
                        }
                        break;
                    case 9:                 //배송 가능
                        for(int i=0;i<uberList.size();i++){
                            if(uberList.get(i).getPostCheck().equals(searchString))
                                uberList2.add(uberList.get(i));
                        }
                        break;
                    case 10:                //수령 가능
                        for(int i=0;i<uberList.size();i++){
                            if(uberList.get(i).getDeliverCheck().equals(searchString))
                                uberList2.add(uberList.get(i));
                        }
                        break;
                }
                uberadapter = new UberListAdapter(getApplicationContext(), uberList2);
                listView.setAdapter(uberadapter);

            }
        });
        all_select.setOnClickListener(View->{
            List<Uber> setubers=uberadapter.getList();
            if(all_select.getText().toString().equals("일괄선택")){
                for(int i=0;i<setubers.size();i++){
                    setubers.get(i).setSelected(true);
                }
                all_select.setText("일괄해제");
            }
            else{
                for(int i=0;i<setubers.size();i++){
                    setubers.get(i).setSelected(false);
                }
                all_select.setText("일괄선택");
            }
        });
    }
}