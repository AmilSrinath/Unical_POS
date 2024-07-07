/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.model;

/**
 *
 * @author HP
 */
public class PosMainItemSubCategory implements SuperModel {

    private Integer subItemCategoryId;
    private Integer mainItemCategoryId;
    private String mainItemCategoryName;
    private String subItemCategoryName;
    private String imagePath;
    private Integer status;
    private Integer userId;
    Integer visible;

    public PosMainItemSubCategory(int aInt, int aInt0, String string, String string0, String string1, int aInt1, String string2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

   

    /**
     * @return the subItemCategoryId
     */
    public Integer getSubItemCategoryId() {
        return subItemCategoryId;
    }

    /**
     * @param subItemCategoryId the subItemCategoryId to set
     */
    public void setSubItemCategoryId(Integer subItemCategoryId) {
        this.subItemCategoryId = subItemCategoryId;
    }

    /**
     * @return the mainItemCategoryId
     */
    public Integer getMainItemCategoryId() {
        return mainItemCategoryId;
    }

    /**
     * @param mainItemCategoryId the mainItemCategoryId to set
     */
    public void setMainItemCategoryId(Integer mainItemCategoryId) {
        this.mainItemCategoryId = mainItemCategoryId;
    }

    /**
     * @return the mainItemCategoryName
     */
    public String getMainItemCategoryName() {
        return mainItemCategoryName;
    }

    /**
     * @param mainItemCategoryName the mainItemCategoryName to set
     */
    public void setMainItemCategoryName(String mainItemCategoryName) {
        this.mainItemCategoryName = mainItemCategoryName;
    }

    /**
     * @return the subItemCategoryName
     */
    public String getSubItemCategoryName() {
        return subItemCategoryName;
    }

    /**
     * @param subItemCategoryName the subItemCategoryName to set
     */
    public void setSubItemCategoryName(String subItemCategoryName) {
        this.subItemCategoryName = subItemCategoryName;
    }

    /**
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
