package com.example.demo.pojo;


import java.util.Objects;

public class UserInfo {
    private String userId;
    private String name;
    private String tellphoneNumber;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTellphoneNumber() {
        return tellphoneNumber;
    }

    public void setTellphoneNumber(String tellphoneNumber) {
        this.tellphoneNumber = tellphoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;//使用反射技术，判断o是否是person类 等效于obj instanceof person。getclass（）相当于获取类名称，如Getclass（）获取当前类的名称。
        UserInfo userInfo = (UserInfo) o;
        return userId == userInfo.userId &&
                Objects.equals(name, userInfo.name);
    }





}
