/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.SubItemCategoryDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.SubItemCategoryModel;
import net.unical.pos.repository.custom.SubItemCategoryRepositoryCustom;
import net.unical.pos.service.custom.SubItemCategoryServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class SubItemCategoryServiceImpl implements SubItemCategoryServiceCustom{
    
    private SubItemCategoryRepositoryCustom subItemCategoryRepositoryCustom;

    public SubItemCategoryServiceImpl() {
        subItemCategoryRepositoryCustom=RepositoryFactory.getInstance().
                getRepository(RepositoryFactory.RepositoryType.SUB_ITEM_CATEGORY);
    }
    
    @Override
    public boolean saveSubItemCategory(SubItemCategoryDto subItemCategoryDto) throws Exception {
        
        SubItemCategoryModel subItemCategoryModel=new SubItemCategoryModel(0,
                subItemCategoryDto.getMainItemCategoryId(),
                subItemCategoryDto.getMainCategoryName(),
                subItemCategoryDto.getSubCategoryName(),
                subItemCategoryDto.getImagePath(),
                subItemCategoryDto.getStatus(),
                subItemCategoryDto.getUserId(),1);
        return subItemCategoryRepositoryCustom.Save(subItemCategoryModel);
    }

    @Override
    public boolean updateSubItemCategory(SubItemCategoryDto subItemCategoryDto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<SubItemCategoryDto> getAllCategories() throws Exception {
        ArrayList<SubItemCategoryModel> subItemCategoryModel=new ArrayList<>();
        ArrayList<SubItemCategoryDto> subItemCategoryDto=new ArrayList<>();
        subItemCategoryModel=subItemCategoryRepositoryCustom.getAll();
        
        for(SubItemCategoryModel model: subItemCategoryModel ){
            subItemCategoryDto.add(new SubItemCategoryDto(model.getSubItemCategoryId(),
                    model.getMainItemCategoryId(),model.getMainCategoryName(),
                    model.getSubCategoryName(),model.getImagePath(),model.getStatus(),
                    model.getUserId()));
        }
        
        return subItemCategoryDto;
    }

    @Override
    public SubItemCategoryDto searchSubItemCategory(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<SubItemCategoryDto> getMainCategory(String key) throws Exception {
        ArrayList<SubItemCategoryModel> subItemCategoryModel=new ArrayList<>();
        ArrayList<SubItemCategoryDto> subItemCategoryDto=new ArrayList<>();
        subItemCategoryModel=subItemCategoryRepositoryCustom.getSearchItems(key);
        
        for(SubItemCategoryModel model: subItemCategoryModel ){
            subItemCategoryDto.add(new SubItemCategoryDto(model.getSubItemCategoryId(),
                    model.getMainItemCategoryId(),model.getMainCategoryName(),
                    model.getSubCategoryName(),model.getImagePath(),model.getStatus(),
                    model.getUserId()));
        }
        
        return subItemCategoryDto;
    }

    @Override
    public ArrayList<SubItemCategoryDto> getSubItemCategories(Integer key) throws Exception {
        ArrayList<SubItemCategoryModel> subItemCategoryModel=new ArrayList<>();
        ArrayList<SubItemCategoryDto> subItemCategoryDto=new ArrayList<>();
        subItemCategoryModel=subItemCategoryRepositoryCustom.getSubItemCategories(key);
        
        for(SubItemCategoryModel model: subItemCategoryModel ){
           
            subItemCategoryDto.add(new SubItemCategoryDto(model.getSubItemCategoryId(), model.getMainItemCategoryId(), 
                    model.getMainCategoryName(), model.getSubCategoryName(), model.getImagePath(), model.getStatus(), model.getUserId()));
        }
        
        return subItemCategoryDto;
    }

    @Override
    public ArrayList<SubItemCategoryDto> getAllSubCategories() throws Exception {
        ArrayList<SubItemCategoryModel> subItemCategoryModel=new ArrayList<>();
        ArrayList<SubItemCategoryDto> subItemCategoryDto=new ArrayList<>();
        subItemCategoryModel=subItemCategoryRepositoryCustom.getAllSubCategories();
        
        for(SubItemCategoryModel model: subItemCategoryModel ){
            subItemCategoryDto.add(new SubItemCategoryDto(model.getSubItemCategoryId(),
                    model.getMainItemCategoryId(),model.getMainCategoryName(),
                    model.getSubCategoryName(),model.getImagePath(),model.getStatus(),
                    model.getUserId()));
        }
        
        return subItemCategoryDto;
    }
}
