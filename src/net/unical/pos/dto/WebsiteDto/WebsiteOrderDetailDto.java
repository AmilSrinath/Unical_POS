/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.dto.WebsiteDto;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class WebsiteOrderDetailDto {
    private Integer orderId;
    private Integer itemId;
    private String product_name;
    private Double price;
    private Integer quantity;

    public WebsiteOrderDetailDto() {
    }

    public WebsiteOrderDetailDto(Integer orderId, Integer itemId, String product_name, Double price, Integer quantity) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.product_name = product_name;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
}

