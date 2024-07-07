/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.SubItemCategoryDto;

/**
 *
 * @author Sanjuka
 */
public interface SubItemCategoryServiceCustom {
    
    public boolean saveSubItemCategory(SubItemCategoryDto subItemCategoryDto)throws Exception;
    
    public boolean updateSubItemCategory(SubItemCategoryDto subItemCategoryDto)throws Exception;
    
    public ArrayList<SubItemCategoryDto> getAllCategories()throws Exception;

    public SubItemCategoryDto searchSubItemCategory(String key)throws Exception;
    
    public ArrayList<SubItemCategoryDto> getMainCategory(String key)throws Exception;
    
    public ArrayList<SubItemCategoryDto> getSubItemCategories(Integer key)throws Exception;

    public ArrayList<SubItemCategoryDto> getAllSubCategories()throws Exception;
    
}
