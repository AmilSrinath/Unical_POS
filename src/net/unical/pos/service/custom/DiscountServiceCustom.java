/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.DiscountDto;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public interface DiscountServiceCustom {

    public ArrayList<DiscountDto> getAllDiscounts();

    public boolean addDiscount(DiscountDto discountDto);
   
    
}
