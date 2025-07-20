/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.DiscountDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.DiscountModel;
import net.unical.pos.repository.custom.DiscountRepositoryCustom;
import net.unical.pos.service.custom.DiscountServiceCustom;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class DiscountServiceImpl implements DiscountServiceCustom{
    private DiscountRepositoryCustom discountRepositoryCustom = RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.DISCOUNT);

    @Override
    public ArrayList<DiscountDto> getAllDiscounts() {
        ArrayList<DiscountDto> discounts = new ArrayList<>();
        ArrayList<DiscountModel> allDiscounts = discountRepositoryCustom.getAllDiscounts();
        
        for (DiscountModel allDiscount : allDiscounts) {
            discounts.add(new DiscountDto(
                allDiscount.getDiscountId(),
                allDiscount.getDiscountName(),
                allDiscount.getPercentage(),
                allDiscount.getAmount(),
                allDiscount.getStatus()
            ));
        }
        return discounts;
    }

    @Override
    public boolean addDiscount(DiscountDto discountDto) {
        return discountRepositoryCustom.addDiscount(discountDto);
    }
}
