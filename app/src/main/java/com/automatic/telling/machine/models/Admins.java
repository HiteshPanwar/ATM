package com.automatic.telling.machine.models;

/**
 * Created by HITESH on 9/15/2017.
 */

public class Admins {
    public String adminName;
    public String adminPassword;
    public String adminId;


    public Admins(String adminId, String adminPassword, String adminName) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
        this.adminName = adminName;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
