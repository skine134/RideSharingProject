package com.example.tekbae;

public class SignCheck {
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
}
