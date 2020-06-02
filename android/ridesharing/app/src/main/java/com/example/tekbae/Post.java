package com.example.tekbae;

public class Post {
    String postName;
    int postNumber;
    String postAddress;
    String Thing;
    String ReceiverAddress;
    int ReceiverNumber;
    boolean deliverCheck;
    boolean postcheck;
    String date;
    //"배송자 이름","배송자 번호", "배송자 주소","물건", "수령자 주소","수령자 번호",false,false,"날짜"
    public Post(String postName,int postNumber,String postAddress,String Thing,String ReceiverAddress,
                int ReceiverNumber,boolean deliverCheck,boolean postcheck,String date) {

        this.postName=postName;
        this.postNumber=postNumber;
        this.postAddress=postAddress;
        this.Thing=Thing;
        this.ReceiverAddress=ReceiverAddress;
        this.ReceiverNumber=ReceiverNumber;
        this.deliverCheck=deliverCheck;
        this.postcheck=postcheck;
        this.date=date;
    }

    public String getPostName() {
        return postName;
    }

    public int getPostNumber() {
        return postNumber;
    }

    public String getPostAddress() {
        return postAddress;
    }

    public String getThing() {
        return Thing;
    }

    public String getReceiverAddress() {
        return ReceiverAddress;
    }

    public int getReceiverNumber() {
        return ReceiverNumber;
    }

    public boolean isDeliverCheck() {
        return deliverCheck;
    }

    public boolean isPostcheck() {
        return postcheck;
    }

    public String getDate() {
        return date;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public void setPostNumber(int postNumber) {
        this.postNumber = postNumber;
    }

    public void setPostAddress(String postAddress) {
        this.postAddress = postAddress;
    }

    public void setThing(String thing) {
        Thing = thing;
    }

    public void setReceiverAddress(String receiverAddress) {
        ReceiverAddress = receiverAddress;
    }

    public void setReceiverNumber(int receiverNumber) {
        ReceiverNumber = receiverNumber;
    }

    public void setDekiverCheck(boolean dekiverCheck) {
        this.deliverCheck = dekiverCheck;
    }

    public void setPostcheck(boolean postcheck) {
        this.postcheck = postcheck;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
