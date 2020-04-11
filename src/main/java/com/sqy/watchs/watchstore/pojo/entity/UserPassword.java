package com.sqy.watchs.watchstore.pojo.entity;

public class UserPassword {
    private String userId ;
    private String originPassword ;
    private String newPassword;
    private String confirmPassword;

    public UserPassword(){}

    public UserPassword(User user){
        this.userId = user.getId() ;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOriginPassword() {
        return originPassword;
    }

    public void setOriginPassword(String originPassword) {
        this.originPassword = originPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
