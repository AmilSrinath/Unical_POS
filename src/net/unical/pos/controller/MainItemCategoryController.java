/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.MainItemCategoryDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.MainItemCategoryServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class MainItemCategoryController {
    
    private MainItemCategoryServiceCustom mainItemCategoryServiceCustom;
    
    public MainItemCategoryController() {
        mainItemCategoryServiceCustom = ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.MAIN_ITEM_CATEGORY);
    }
    
    public boolean saveMainItemCategory(MainItemCategoryDto mainItemCategoryDto) throws Exception{
        return mainItemCategoryServiceCustom.saveMainItemCategory(mainItemCategoryDto);
    }
    
    public boolean updateMainItemCategory(MainItemCategoryDto mainItemCategoryDto) throws Exception{
        return mainItemCategoryServiceCustom.updateMainItemCategory(mainItemCategoryDto);
    }
    
    public ArrayList<MainItemCategoryDto> getAll(String quary)throws Exception{
        return mainItemCategoryServiceCustom.getAllMainCategories(quary);
    }
    
    public Integer searchMainCategory(String quary)throws Exception{
        return mainItemCategoryServiceCustom.searchMainItemCategory(quary);
    }

    public Integer getItemCode(String catName) {
        return mainItemCategoryServiceCustom.getCatCode(catName);
    }
}
