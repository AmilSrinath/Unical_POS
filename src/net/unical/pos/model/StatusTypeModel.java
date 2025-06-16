/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.unical.pos.model;

import java.sql.Timestamp;

/**
 *
 * @author Amil Srinath
 */
public class StatusTypeModel {
    private int status_id;
    private int reg_id;
    private String status_type;
    private Timestamp create_date;
    private Timestamp edited_date;
    private int status;
    private int user_id;

    public StatusTypeModel() {}

    public StatusTypeModel(int status_id, int reg_id, String status_type, Timestamp create_date, Timestamp edited_date, int status, int user_id) {
        this.status_id = status_id;
        this.reg_id = reg_id;
        this.status_type = status_type;
        this.create_date = create_date;
        this.edited_date = edited_date;
        this.status = status;
        this.user_id = user_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getReg_id() {
        return reg_id;
    }

    public void setReg_id(int reg_id) {
        this.reg_id = reg_id;
    }

    public String getStatus_type() {
        return status_type;
    }

    public void setStatus_type(String status_type) {
        this.status_type = status_type;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public Timestamp getEdited_date() {
        return edited_date;
    }

    public void setEdited_date(Timestamp edited_date) {
        this.edited_date = edited_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
   
}
