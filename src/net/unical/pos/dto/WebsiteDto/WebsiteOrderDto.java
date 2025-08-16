/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.dto.WebsiteDto;

import java.util.List;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class WebsiteOrderDto {
    private String order_id;
    private String first_name;
    private String address1;
    private String address2;
    private String city;
    private String phone_1;
    private String phone_2;
    private Double sub_total;
    private Double delivery;
    private Double weight;
    private boolean isFreeShipping;
    private Double total;
    private List<WebsiteOrderDetailDto> items;

    public WebsiteOrderDto() {
    }

    public WebsiteOrderDto(String order_id, String first_name, String address1, String address2, String city, String phone_1, String phone_2, Double sub_total, Double delivery, Double weight, boolean isFreeShipping, Double total, List<WebsiteOrderDetailDto> items) {
        this.order_id = order_id;
        this.first_name = first_name;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.phone_1 = phone_1;
        this.phone_2 = phone_2;
        this.sub_total = sub_total;
        this.delivery = delivery;
        this.weight = weight;
        this.isFreeShipping = isFreeShipping;
        this.total = total;
        this.items = items;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    
    public String getPhone_1() {
        return phone_1;
    }

    public void setPhone_1(String phone_1) {
        this.phone_1 = phone_1;
    }

    public String getPhone_2() {
        return phone_2;
    }

    public void setPhone_2(String phone_2) {
        this.phone_2 = phone_2;
    }

    public Double getSub_total() {
        return sub_total;
    }

    public void setSub_total(Double sub_total) {
        this.sub_total = sub_total;
    }

    public Double getDelivery() {
        return delivery;
    }

    public void setDelivery(Double delivery) {
        this.delivery = delivery;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public boolean isIsFreeShipping() {
        return isFreeShipping;
    }

    public void setIsFreeShipping(boolean isFreeShipping) {
        this.isFreeShipping = isFreeShipping;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<WebsiteOrderDetailDto> getItems() {
        return items;
    }

    public void setItems(List<WebsiteOrderDetailDto> items) {
        this.items = items;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    
     
}
