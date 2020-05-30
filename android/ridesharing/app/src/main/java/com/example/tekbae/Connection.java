package com.example.tekbae;


import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class Connection extends AsyncTask<String,String,String> {
    String table;
    String command;
    String key;
    public Connection(String table,String command,String key){
        this.table=table;
        this.command=command;
        this.key=key;
    }
    @Override
    protected String doInBackground(String... objects) {
        String output="";             //store output data;
        try{
            String line=null;           //read Line variable
            URL url=new URL(objects[0]);    //GET URL INFO
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();  //  HTTPURLCONNECTION extends to URLCONNECTION;
            if(connection!=null){           //android connected to url
                connection.setConnectTimeout(5000); //setTimeout one second;
                connection.setDefaultUseCaches(false);
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setUseCaches(false);
                connection.setRequestMethod("POST");
                StringBuffer buffer = new StringBuffer();
                buffer.append("Table").append("=").append(table).append("&");                 // php 변수에 값 대입 php 변수 앞에 '$' 붙이지 않는다 변수 구분은 '&' 사용
                buffer.append("Command").append("=").append(command).append("&");
                buffer.append("Content").append("=").append(key).append("&");

                OutputStreamWriter outStream = new OutputStreamWriter(connection.getOutputStream(), "EUC-KR");
                PrintWriter writer = new PrintWriter(outStream);
                writer.write(buffer.toString());
                writer.flush();

                if(connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                    BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    while(true){
                        line=br.readLine();
                        if(line==null){
                            break;
                        }
                        output+=line;
                    }
                    br.close();
                    connection.disconnect();
                }else{
                    System.out.println("connectiong failed");
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return output;
    }
}
