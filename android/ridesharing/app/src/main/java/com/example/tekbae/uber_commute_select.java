package com.example.tekbae;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class uber_commute_select extends AppCompatActivity {
    ArrayList<String> dataList;
    Button submit;
    TextView select_dates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uber_commute_select);
        dataList=new ArrayList<String>();                       //Date List;
        select_dates=findViewById(R.id.selected_Dates);
        submit=findViewById(R.id.submit);
        MaterialCalendarView calendar=findViewById(R.id.calendarView);  //Calender View

        // select date Event
        calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String y=""+date.getYear();
                String m=null;
                String d=null;
                if(date.getMonth()+1<10)
                    m="0"+(date.getMonth()+1);
                else
                    m=""+(date.getMonth()+1);
                if(date.getDay()<10)
                    d="0"+date.getDay();
                else
                    d=""+date.getDay();
                String data=y+m+d;
                Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
                dataList.add(data);
                if(select_dates.getText().equals("출근할 날짜를 선택 해주세요"))
                    select_dates.setText(data);
                else
                    select_dates.setText(select_dates.getText()+","+data);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkselect(dataList);
            }
        });
    }

    //Dialog checkselect
    void checkselect(final ArrayList<String> dataset){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("주의 사항");
        //add against text
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //typing add List to DB
                        for(int i=0;i<dataset.size();i++){
                            //$ReceiverName,$ReceiverNumber,$ReceiverAddress,$Item,$SenderAddress,$SenderNumber,$DeliverCheck,$UberId,$UberName,$postCheck,#Date
                            try {
                               String result=new Connection("Uber","Insert",",0,,,,0,0,"+
                                       LoginActivity.map.get("UberId")+","+LoginActivity.map.get("UberName")+
                                       ",0,"+dataset.get(i),null,null,null).execute("http://prawnguns.dothome.co.kr/regosterUser.php?").get();

                                if(result.equals("1"))
                                    Toast.makeText(getApplicationContext(),"일정을 추가했습니다.",Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(getApplicationContext(),"error : "+result,Toast.LENGTH_LONG).show();

                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        select_dates.setText("출근할 날짜를 선택 해주세요");
                        dataset.clear();
                    }
                });
        builder.setNegativeButton("취소",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"일정을 취소했습니다.",Toast.LENGTH_LONG).show();
                        select_dates.setText("출근할 날짜를 선택 해주세요");
                        dataset.clear();
                    }
                });
        builder.show();

    }
}
