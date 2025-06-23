/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.unical.pos.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Amil Srinath
 */
public class AuthModuleWise {
    private int module_wise_id;
    private int module_id;
    private int user_id;
    private Timestamp created_date;
    private Timestamp edited_date;
    private int status;

    public AuthModuleWise() {}

    public AuthModuleWise(int module_wise_id, int module_id, int user_id, Timestamp created_date, Timestamp edited_date, int status) {
        this.module_wise_id = module_wise_id;
        this.module_id = module_id;
        this.user_id = user_id;
        this.created_date = created_date;
        this.edited_date = edited_date;
        this.status = status;
    }

    public int getModule_wise_id() {
        return module_wise_id;
    }

    public void setModule_wise_id(int module_wise_id) {
        this.module_wise_id = module_wise_id;
    }

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Timestamp getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
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

    
}
