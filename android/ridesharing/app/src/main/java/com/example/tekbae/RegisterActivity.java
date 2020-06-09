package com.example.tekbae;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class RegisterActivity extends AppCompatActivity {

    private EditText idText,passwordText,nameText,ageText,addressText,numberText,emailText;
    private Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        idText = (EditText) findViewById(R.id.idMain);
        passwordText = (EditText) findViewById(R.id.passMain);
        nameText = (EditText) findViewById(R.id.nameText);
        ageText = (EditText) findViewById(R.id.ageText);
        addressText = (EditText) findViewById(R.id.addressText);
        numberText = (EditText) findViewById(R.id.numberText);
        emailText = (EditText) findViewById(R.id.emailText);

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();
                String userName = nameText.getText().toString();
                String userAge = ageText.getText().toString();
                String userAddress = addressText.getText().toString();
                String userNumber = numberText.getText().toString();
                String userEmail = emailText.getText().toString();

                ArrayList<String> userArr = new ArrayList<String>();
                userArr.add(userID);
                userArr.add(userPassword);
                userArr.add(userName);
                userArr.add(userAge);
                userArr.add(userAddress);
                userArr.add(userNumber);
                userArr.add(userEmail);
                userArr.add("0");


                String inputString = "";

                for(int i =0; i<=7;i++){
                    inputString += userArr.get(i);
                    if(i!=userArr.size()-1) {
                        inputString +=",";
                        }
            }


                try {
                    String checkda = new Connection("User","select",idText.getText().toString(),null,null,null).execute("http://prawnguns.dothome.co.kr/regosterUser.php?").get();
                    if(checkda.equals("null")) {

                        new Connection("User", "Insert", inputString, null, null, null).execute("http://prawnguns.dothome.co.kr/regosterUser.php?");
                        Toast toast = Toast.makeText(RegisterActivity.this, "회원가입 성공", Toast.LENGTH_LONG);
                        toast.show();


                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);

                    }else{
                        Toast toast=Toast.makeText(RegisterActivity.this,"Disable registerID: "+idText.getText().toString(),Toast.LENGTH_SHORT);
                        toast.show();
                    }


                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}