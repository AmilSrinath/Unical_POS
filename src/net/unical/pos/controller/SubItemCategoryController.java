/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.SubItemCategoryDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.SubItemCategoryServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class SubItemCategoryController {
    
    private SubItemCategoryServiceCustom subItemCategoryServiceCustom;
            
    public SubItemCategoryController() {
        subItemCategoryServiceCustom = ServiceFactory.getInstance().
                getService(ServiceFactory.ServiceType.SUB_ITEM_CATEGORY);
    }
    
    public boolean saveSubItemCategory(SubItemCategoryDto subItemCategoryDto) throws Exception{
        return subItemCategoryServiceCustom.saveSubItemCategory(subItemCategoryDto);
    }
    
    public boolean updateSubItemCategory(SubItemCategoryDto subItemCategoryDto) throws Exception{
//        return .saveDoctor(mainItemCategoryDto);

    return true;
    }
    
    public ArrayList<SubItemCategoryDto> getAllCategories()throws Exception{
        return subItemCategoryServiceCustom.getAllCategories();
    }
    
    public ArrayList<SubItemCategoryDto> getAllSubCategories()throws Exception{
        return subItemCategoryServiceCustom.getAllSubCategories();
    }
    
    public ArrayList<SubItemCategoryDto> searchSubItemCategories(String key)throws Exception{
        return subItemCategoryServiceCustom.getMainCategory(key);
    }
    
    public ArrayList<SubItemCategoryDto> getSubItemCategories(Integer key)throws Exception{
        return subItemCategoryServiceCustom.getSubItemCategories(key);
    }

    public Integer getSubItemCode(String item) {
        return subItemCategoryServiceCustom.getSubItemCode(item);
    }
}
