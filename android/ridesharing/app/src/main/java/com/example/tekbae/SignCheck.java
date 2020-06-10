package com.example.tekbae;

import android.os.AsyncTask;

public class SignCheck extends AsyncTask<String, String, Void> {
    public int flag;

    public void setOK(){
        this.flag = 1;
    }

    public void setNo(){
        this.flag = 0;
    }

    public int getFlag(){
        return this.flag;
    }

    @Override
    protected Void doInBackground(String... strings) {
        return null;
    }
}
