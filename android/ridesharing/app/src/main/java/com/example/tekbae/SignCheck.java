package com.example.tekbae;

import java.io.Serializable;
@SuppressWarnings("serial")
public class SignCheck implements Serializable {
    public int flag;
    public SignCheck(){
        this.flag = 0;
    }

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
