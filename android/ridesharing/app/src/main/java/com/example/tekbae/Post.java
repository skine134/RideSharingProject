package com.example.tekbae;

public class Post {
    //배송자 이름, 수령자 이름, 수령자 주소, 수령자 전화번호, 택배물 정보
    String userID;
    String userName;
    String userAddress;
    String userNumber;
    String postInfo;
    String distance;
    Boolean postCheck;

    public Boolean getPostCheck() {
        return postCheck;
    }

    public void setPostCheck(Boolean postCheck) {
        this.postCheck = postCheck;
    }

    public Post(Boolean postCheck) {
        this.postCheck = postCheck;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Post(String distance) {
        this.distance = distance;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getPostInfo() {
        return postInfo;
    }

    public void setPostInfo(String postInfo) {
        this.postInfo = postInfo;
    }

    public Post(String userID, String userName, String userAddress, String userNumber, String postInfo,String distance,Boolean postCheck) {
        this.userID = "배송자:"+userID;
        this.userName = "수령자:"+userName;
        this.userAddress = "주소"+userAddress;
        this.userNumber = "전화번호:"+userNumber;
        this.postInfo = "택배:"+postInfo;
        this.distance = "거리:"+distance;
        this.postCheck = postCheck;
    }
}
