/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.model;

/**
 *
 * @author apple
 */
public class OrderDetails {
    private Integer order_id;
    private String orderCode;
    private String item_name;
    private Integer quantity;
    private Double per_item_price;
    private Double total_item_price;
    private Double delivery_fee;
    private Double total_order_price;
    private String date;
    
    public OrderDetails() {}
    
    public OrderDetails(Integer order_id, String orderCode, String item_name, Integer quantity, Double per_item_price, Double total_item_price, Double total_order_price, Double delivery_fee, String date) {
        this.order_id = order_id;
        this.item_name = item_name;
        this.quantity = quantity;
        this.per_item_price = per_item_price;
        this.total_item_price = total_item_price;
        this.total_order_price = total_order_price;
        this.delivery_fee = delivery_fee;
        this.orderCode = orderCode;
        this.date = date;
    }
    
    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
    
    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPer_item_price() {
        return per_item_price;
    }

    public void setPer_item_price(Double per_item_price) {
        this.per_item_price = per_item_price;
    }

    public Double getTotal_item_price() {
        return total_item_price;
    }

    public void setTotal_item_price(Double total_item_price) {
        this.total_item_price = total_item_price;
    }

    public Double getTotal_order_price() {
        return total_order_price;
    }

    public void setTotal_order_price(Double total_order_price) {
        this.total_order_price = total_order_price;
    }

    public Double getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(Double delivery_fee) {
        this.delivery_fee = delivery_fee;
    }
    
    public String getDate(){
        return date;
    }
    
    public void setDate(String date){
        this.date=date;
    }
    
    public Object[] toArray() {
        return new Object[] {
            item_name,
            quantity,
            per_item_price,
            total_item_price,
            total_order_price,
            delivery_fee,
            date
        };
    }
}
