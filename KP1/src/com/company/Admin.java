package com.company;

public class Admin extends User{
    protected boolean admin_access;
    public Admin(String username, String password) {
        super(username,password);
        this.admin_access = true;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
