package com.example.tekbae;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

        public class RegisterRequest extends StringRequest {
            //서버 URL설정(php파일 연동)
            final static private String URL = "http://prawnguns.dothome.co.kr/Register.php";
            private Map<String,String> map;

            //아이디 비번 이름 나이 주소 전화번호 이메일
            public RegisterRequest(String idText, String passwordText, String nameText, int ageText,String addressText, int numberText,String emailText,Response.Listener<String> listener){
                super(Method.POST, URL, listener, null);

                map = new HashMap<>();
                map.put("userID",idText);
                map.put("userPassword",passwordText);
                map.put("userName",nameText);
                map.put("userAge",ageText +"");
                map.put("userAddress",addressText);
                map.put("userNumber",numberText+ "");
                map.put("userEmail",emailText+" ");
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError{
        return map;
    }


}
