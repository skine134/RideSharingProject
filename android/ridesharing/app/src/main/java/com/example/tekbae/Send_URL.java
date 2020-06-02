package com.example.tekbae;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Send_URL extends AppCompatActivity {

    EditText txt;
    Button btn;
    String number_list[] = {"01032555555", "01022223333", "01033225566"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_url);

        txt = (EditText) findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UrlText = txt.getText().toString();
                for(int i=0 ; i < number_list.length; i++){
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(number_list[i], null, UrlText, null, null);
                }
                Toast.makeText(getApplicationContext(), "Url 을 전송하였습니다", Toast.LENGTH_SHORT).show();
            }
        });



    }
}