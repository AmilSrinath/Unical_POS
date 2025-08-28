/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.model.MainItemCategoryModel;


/**
 *
 * @author Sanjuka
 */
public interface MainItemCategoryRepositoryCustom{
    
    public Integer getMainCategory(String key)throws Exception;

    public boolean Save(MainItemCategoryModel mainItemCategoryModel)throws Exception;

    public ArrayList<MainItemCategoryModel> getAll(String quary)throws Exception;

    public boolean Update(MainItemCategoryModel mainItemCategoryModel)throws Exception;

    public Integer getCatCode(String catName);
}
