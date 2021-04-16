/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hspc.pos.controller;

import java.util.ArrayList;
import net.hspc.pos.dto.MainItemCategoryDto;
import net.hspc.pos.dto.SubItemCategoryDto;

/**
 *
 * @author Sanjuka
 */
public class SubItemCategoryController {
    
    public boolean saveSubItemCategory(SubItemCategoryDto subItemCategoryDto) throws Exception{
//        return .saveDoctor(mainItemCategoryDto);

    return true;
    }
    
    public boolean updateSubItemCategory(SubItemCategoryDto subItemCategoryDto) throws Exception{
//        return .saveDoctor(mainItemCategoryDto);

    return true;
    }
    
    public ArrayList<SubItemCategoryDto> getAllCategories()throws Exception{
//        return doctorRepository.getAll();
        return null;
    }
}
