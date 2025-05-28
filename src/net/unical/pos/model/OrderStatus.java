/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package net.unical.pos.model;

/**
 *
 * @author Amil Srinath
 */
public enum OrderStatus {
    CTIVE(1, "Active"),
    PENDING(2, "Pending"),
    WRAPPING(3, "Wrapping"),
    OUT_OF_DELIVERY(4, "Out of Delivery"),
    DELIVERED(5, "Delivered"),
    RETURN(6, "Return"),
    CANCEL(7, "Cancel");

    private final int code;
    private final String description;

    OrderStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static String getDescriptionByCode(int code) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getCode() == code) {
                return status.getDescription();
            }
        }
        return "Unknown";
    }
}
