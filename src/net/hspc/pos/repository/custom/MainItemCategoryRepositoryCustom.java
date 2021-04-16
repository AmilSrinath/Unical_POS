/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hspc.pos.repository.custom;

import java.util.ArrayList;
import net.hspc.pos.dto.MainItemCategoryDto;

/**
 *
 * @author Sanjuka
 */
public interface MainItemCategoryRepositoryCustom {
    
    public boolean saveMainItemCategory(MainItemCategoryDto mainItemCategoryDto)throws Exception;
    
    public boolean updateMainItemCategory(MainItemCategoryDto mainItemCategoryDto)throws Exception;
    
    public ArrayList<MainItemCategoryDto> getAllMainCategories()throws Exception;

    public MainItemCategoryDto searchMainItemCategory(String key)throws Exception;
    
}
