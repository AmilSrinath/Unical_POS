/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.crudService.SuperServiceCrud;
import net.unical.pos.model.SubItemCategoryModel;

/**
 *
 * @author Sanjuka
 */
public interface SubItemCategoryRepositoryCustom extends SuperServiceCrud<SubItemCategoryModel, String>{
    
    Integer getSubCategory(String key)throws Exception;
    
    ArrayList<SubItemCategoryModel> getSubItemCategories(Integer key)throws Exception;
    
    ArrayList<SubItemCategoryModel>getAllSubCategories()throws Exception;

    public Integer getSubItemCode(String item);
}
