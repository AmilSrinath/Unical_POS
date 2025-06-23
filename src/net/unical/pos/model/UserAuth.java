/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.unical.pos.model;

/**
 *
 * @author Amil Srinath
 */
public class UserAuth {
    private int user_id;
    private int emp_id;
    private int role_id;
    private String emp_name;
    private String user_role;

    public UserAuth() {}

    public UserAuth(int user_id, int emp_id, int role_id, String emp_name, String user_role) {
        this.user_id = user_id;
        this.emp_id = emp_id;
        this.role_id = role_id;
        this.emp_name = emp_name;
        this.user_role = user_role;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }
    
    
}
