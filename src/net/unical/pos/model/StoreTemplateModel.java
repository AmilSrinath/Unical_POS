/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.model;

/**
 *
 * @author Sanjuka
 */
public class StoreTemplateModel {
    
    Integer templateId;
    Integer mainItemId;
    Integer subItemId;
    String templateName;
    Double qty;
    Integer userId;
    Integer visible;

    public StoreTemplateModel() {
    }

    public StoreTemplateModel(Integer templateId, Integer mainItemId, Integer subItemId, String templateName, Double qty, Integer userId, Integer visible) {
        this.templateId = templateId;
        this.mainItemId = mainItemId;
        this.subItemId = subItemId;
        this.templateName = templateName;
        this.qty = qty;
        this.userId = userId;
        this.visible = visible;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public Integer getMainItemId() {
        return mainItemId;
    }

    public void setMainItemId(Integer mainItemId) {
        this.mainItemId = mainItemId;
    }

    public Integer getSubItemId() {
        return subItemId;
    }

    public void setSubItemId(Integer subItemId) {
        this.subItemId = subItemId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }
}
