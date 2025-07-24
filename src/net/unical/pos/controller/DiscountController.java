/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.DiscountDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.DiscountServiceCustom;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class DiscountController {
    
    private DiscountServiceCustom discountServiceCustom = ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.DISCOUNT);

    public ArrayList<DiscountDto> getAllDiscounts() {
        return discountServiceCustom.getAllDiscounts();
    }

    public boolean addDiscount(DiscountDto discountDto) {
       return discountServiceCustom.addDiscount(discountDto);
    }

    public Integer getDiscountId(double percentage) {
        return discountServiceCustom.getDiscountId(percentage); 
    }
    
}
