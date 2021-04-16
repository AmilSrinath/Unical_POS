/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hspc.pos.dto;

/**
 *
 * @author Sanjuka
 */
public class EmployeeManagementDto {
    
    Integer employeeId;
    String title;
    String name;
    String designation;
    String Prefix;
    Integer Code;
    String codePrefix;
    Integer phone;
    String email;
    String address;
    String imagePath;
    boolean status;

    public EmployeeManagementDto(Integer employeeId, String title, String name, String designation, String Prefix, Integer Code, String codePrefix, Integer phone, String email, String address, String imagePath, boolean status) {
        this.employeeId = employeeId;
        this.title = title;
        this.name = name;
        this.designation = designation;
        this.Prefix = Prefix;
        this.Code = Code;
        this.codePrefix = codePrefix;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.imagePath = imagePath;
        this.status = status;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPrefix() {
        return Prefix;
    }

    public void setPrefix(String Prefix) {
        this.Prefix = Prefix;
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer Code) {
        this.Code = Code;
    }

    public String getCodePrefix() {
        return codePrefix;
    }

    public void setCodePrefix(String codePrefix) {
        this.codePrefix = codePrefix;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
