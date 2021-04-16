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
public class SupplierDto {
    
    Integer supplierDto;
    String salesmanName;
    String companyName;
    String brandName;
    Integer phone;
    String address;
    String gmail;
    Integer status;

    public SupplierDto(Integer supplierDto, String salesmanName, String companyName, String brandName, Integer phone, String address, String gmail, Integer status) {
        this.supplierDto = supplierDto;
        this.salesmanName = salesmanName;
        this.companyName = companyName;
        this.brandName = brandName;
        this.phone = phone;
        this.address = address;
        this.gmail = gmail;
        this.status = status;
    }

    public Integer getSupplierDto() {
        return supplierDto;
    }

    public void setSupplierDto(Integer supplierDto) {
        this.supplierDto = supplierDto;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    
}
