package com.example.tekbae;

import android.content.DialogInterface;
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

public class uber_commute_select extends AppCompatActivity {
    List<String> dataList;
    Button submit;
    TextView select_dates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uber_commute_select);
        dataList=new ArrayList<String>();
        select_dates=findViewById(R.id.selected_Dates);
        submit=findViewById(R.id.submit);
        MaterialCalendarView calendar=findViewById(R.id.calendarView);
        calendar.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                String data=date.getYear()+"년"+(date.getMonth()+1)+"월"+date.getDay()+"일";
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
    void checkselect(final List<String> dataset){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("주의 사항");
        //add against text
        builder.setPositiveButton("확인",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //typing add List to DBt
                        Toast.makeText(getApplicationContext(),"일정을 추가했습니다.",Toast.LENGTH_LONG).show();
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
