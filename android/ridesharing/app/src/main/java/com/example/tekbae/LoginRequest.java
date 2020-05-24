package com.example.tekbae;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    //서버 URL설정(php파일 연동)
    final static private String URL = "http://prawnguns.dothome.co.kr/Login.php";
    private Map<String,String> map;

    public LoginRequest(String idText, String passwordText, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);

        map = new HashMap<>();
        map.put("userID",idText);
        map.put("userPassword",passwordText);
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError{
        return map;
    }


}
