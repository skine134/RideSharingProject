package com.example.tekbae;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class management_menu_edit extends AppCompatActivity {

    Button btn1, btn2;
    EditText txt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_menu_edit);
        List<Uber> selected=total_management_menu.selected_list.get("selected");
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = txt1.getText().toString();
                Intent intent = new Intent(management_menu_edit.this, uber_edit.class);
                intent.putExtra("textField", value);
                startActivity(intent);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str=null;
                for (int j = 0; j < selected.size(); j++) {
                    try {
                        str = new Connection("Uber", "delete",selected.get(j).getUberId()+","+selected.get(j).getDate(),
                                null,null,null).execute("http://prawnguns.dothome.co.kr/regosterUser.php?").get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(str.equals("1"))
                    Toast.makeText(getApplicationContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "작업 중 에러 발생", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
