package com.example.tekbae;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class uber_edit extends Activity {
    Button  back;
    ScrollView scrollview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uber_edit);
        List<Uber> uberList=new ArrayList<Uber>();
        List<Uber> selected=total_management_menu.selected_list.get("selected");
        String outUber=null;
        try {
            outUber = new Connection("Uber", "select", null, "All", null, null).execute(BuildConfig.SERVER_HOST+"/regosterUser.php?").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] arr = outUber.split("/");
        back = (Button) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        LinearLayout layout1 = (LinearLayout) findViewById(R.id.manList);
        layout1.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < arr.length; i++) {
            String[] arr2 = arr[i].split(",");
            //ReceiverName,ReceiverNumber,ReceiverAddress,Item,SenderAddress,SenderNumber,DeliverCheck,UberId,UberName,postCheck,Date,No
            ArrayList<String>idList=new ArrayList<String>();
            for(int j=0;j<uberList.size();j++){
                idList.add(uberList.get(j).getUberId());
            }
            if(!idList.contains(arr2[7])){
                uberList.add(0, new Uber(arr2[0], Integer.parseInt(arr2[1]), arr2[2], arr2[3], arr2[4], Integer.parseInt(arr2[5]), false, arr2[7], arr2[8], false, arr2[10], arr2[11]));
                Button personbtn = new Button(this);
                personbtn.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                personbtn.setText(uberList.get(0).getUberName());
                personbtn.setTextSize(40);
                if (selected != null) {
                    if (selected.size() > 0) {
                        personbtn.setOnClickListener(View -> {
                            String str = null;
                            for (int j = 0; j < selected.size(); j++) {
                                try {
                                    System.out.println(arr2[7] + "," + arr2[8]);
                                    str = new Connection("Uber", "update", arr2[8] + "," + arr2[7],
                                            "Uber", selected.get(j).getUberName(),
                                            selected.get(j).getDate()).execute(BuildConfig.SERVER_HOST+"/regosterUser.php?").get();
                                    System.out.println(str);
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (str.equals("1")) {
                                Toast.makeText(getApplicationContext(), "정상적으로 교체 되었습니다.", Toast.LENGTH_SHORT);
                            } else {
                                Toast.makeText(getApplicationContext(), "작업 중 에러가 발생하였습니다." + str, Toast.LENGTH_SHORT);
                            }
                            finish();
                        });
                    }
                }
                layout1.addView(personbtn);
        }
        }
    }
}
