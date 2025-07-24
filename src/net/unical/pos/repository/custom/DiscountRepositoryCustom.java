/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.dto.DiscountDto;
import net.unical.pos.model.DiscountModel;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public interface DiscountRepositoryCustom {

    public ArrayList<DiscountModel> getAllDiscounts();

    public boolean addDiscount(DiscountDto discountDto);

    public Integer getDiscountId(double percentage);
    
}
