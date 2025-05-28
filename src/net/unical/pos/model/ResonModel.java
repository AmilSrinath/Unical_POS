/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.unical.pos.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Amil Srinath
 */
public class ResonModel {
    private int resonId;
    private String reson;
    private int status;
    private int userId;
    private Timestamp createDate;
    private Timestamp editedDate;

    public ResonModel(){}

    public ResonModel(int resonId, String reson, int status, int userId, Timestamp createDate, Timestamp editedDate) {
        this.resonId = resonId;
        this.reson = reson;
        this.status = status;
        this.userId = userId;
        this.createDate = createDate;
        this.editedDate = editedDate;
    }

    public int getResonId() {
        return resonId;
    }

    public void setResonId(int resonId) {
        this.resonId = resonId;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getEditedDate() {
        return editedDate;
    }

    public void setEditedDate(Timestamp editedDate) {
        this.editedDate = editedDate;
    }

    
}
