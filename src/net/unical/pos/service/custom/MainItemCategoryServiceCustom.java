/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.MainItemCategoryDto;

/**
 *
 * @author Sanjuka
 */
public interface MainItemCategoryServiceCustom {
    
    public boolean saveMainItemCategory(MainItemCategoryDto mainItemCategoryDto)throws Exception;
    
    public boolean updateMainItemCategory(MainItemCategoryDto mainItemCategoryDto)throws Exception;
    
    public ArrayList<MainItemCategoryDto> getAllMainCategories(String quary)throws Exception;

    public Integer searchMainItemCategory(String key)throws Exception;
    
}
