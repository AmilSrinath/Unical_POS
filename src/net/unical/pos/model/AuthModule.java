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
public class AuthModule {
    private int module_id;
    private String module_name;
    private Timestamp created_date;
    private Timestamp edited_date;
    private int status;

    public AuthModule() {}

    public AuthModule(int module_id, String module_name, Timestamp created_date, Timestamp edited_date, int status) {
        this.module_id = module_id;
        this.module_name = module_name;
        this.created_date = created_date;
        this.edited_date = edited_date;
        this.status = status;
    }

    public int getModule_id() {
        return module_id;
    }

    public void setModule_id(int module_id) {
        this.module_id = module_id;
    }

    public String getModule_name() {
        return module_name;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
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
