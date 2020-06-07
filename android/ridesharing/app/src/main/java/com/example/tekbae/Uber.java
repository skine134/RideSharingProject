package com.example.tekbae;

public class Uber {
    String ReceiverName;
    int ReceiverNumber;
    String ReceiverAddress;
    String Item;
    String SenderAddress;
    int SenderNumber;
    Boolean DeliverCheck;
    String UberId;
    String UberName;
    Boolean postCheck;
    String Date;
    String No;

    public Uber(String ReceiverName, int ReceiverNumber, String ReceiverAddress, String Item, String SenderAddress,
        int SenderNumber, Boolean DeliverCheck, String UberId, String UberName, Boolean postCheck, String Date, String No) {
            this.ReceiverName = ReceiverName;
            this.ReceiverNumber = ReceiverNumber;
            this.ReceiverAddress = ReceiverAddress;
            this.Item = Item;
            this.SenderAddress = SenderAddress;
            this.SenderNumber = SenderNumber;
            this.DeliverCheck = DeliverCheck;
            this.UberId = UberId;
            this.UberName = UberName;
            this.postCheck = postCheck;
            this.Date = Date;
            this.No = No;
    }





    public String getReceiverName() {
        return ReceiverName;
    }

    public void setReceiverName(String receiverName) {
        ReceiverName = receiverName;
    }

    public int getReceiverNumber() {
        return ReceiverNumber;
    }

    public void setReceiverNumber(int receiverNumber) {
        ReceiverNumber = receiverNumber;
    }

    public String getReceiverAddress() {
        return ReceiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        ReceiverAddress = receiverAddress;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public String getSenderAddress() {
        return SenderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        SenderAddress = senderAddress;
    }

    public int getSenderNumber() {
        return SenderNumber;
    }

    public void setSenderNumber(int senderNumber) {
        SenderNumber = senderNumber;
    }

    public Boolean getDeliverCheck() {
        return DeliverCheck;
    }

    public void setDeliverCheck(Boolean deliverCheck) {
        DeliverCheck = deliverCheck;
    }

    public String getUberId() {
        return UberId;
    }

    public void setUberId(String uberId) {
        UberId = uberId;
    }

    public String getUberName() {
        return UberName;
    }

    public void setUberName(String uberName) {
        UberName = uberName;
    }

    public Boolean getPostCheck() {
        return postCheck;
    }

    public void setPostCheck(Boolean postCheck) {
        this.postCheck = postCheck;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getNo() {
        return No;
    }

    public void setNo(String no) {
        No = no;
    }
}
