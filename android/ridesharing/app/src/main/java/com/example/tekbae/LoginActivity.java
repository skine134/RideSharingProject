package com.example.tekbae;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    private EditText idText, passwordText;
    private Button loginbutton;
    private TextView registerButton;
    public static HashMap<String,String> map=new HashMap<String,String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idText = (EditText) findViewById(R.id.idMain);
        passwordText = (EditText) findViewById(R.id.passMain);

        loginbutton = (Button) findViewById(R.id.loginbutton);
        registerButton = (TextView) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String outputdata=new Connection("User","select",idText.getText().toString()/*id*/,null,null,null).execute("http://prawnguns.dothome.co.kr/regosterUser.php?").get();
                    String[] arr=outputdata.split(",");
                    if(arr[0].equals(idText.getText().toString()) &&arr[1].equals(passwordText.getText().toString())){
                        if(arr[7].equals("1")){             // 1 is administrator
                            Intent intent=new Intent(getApplicationContext(),manager_menu.class);
                            startActivity(intent);
                        }
                        else if(arr[7].equals("0")){            //0 is Uber
                            Intent intent=new Intent(getApplicationContext(),uber_menu.class);
                            map.put("UberId",arr[0]);
                            map.put("UberName",arr[2]);
                            startActivity(intent);
                        }
                        else{
                            Toast toast=Toast.makeText(LoginActivity.this,"not found tag",Toast.LENGTH_SHORT);
                            toast.show();
                        }

                    }else{
                        Toast toast=Toast.makeText(LoginActivity.this,"disable login"+outputdata,Toast.LENGTH_SHORT);
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
