/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.MainItemCategoryDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.MainItemCategoryModel;
import net.unical.pos.repository.custom.MainItemCategoryRepositoryCustom;
import net.unical.pos.service.custom.MainItemCategoryServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class MainItemCategoryServiceImpl implements MainItemCategoryServiceCustom{
    
    private MainItemCategoryRepositoryCustom mainItemCategoryRepositoryCustom;
    
    public MainItemCategoryServiceImpl() {
        mainItemCategoryRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.MAIN_ITEM_CATEGORY);
    }
    
    @Override
    public boolean saveMainItemCategory(MainItemCategoryDto mainItemCategoryDto) throws Exception {
        MainItemCategoryModel mainItemCategoryModel=new MainItemCategoryModel(0,mainItemCategoryDto.getCategoryName(),
                mainItemCategoryDto.getImagePath(),
                mainItemCategoryDto.getStatus(),mainItemCategoryDto.getUserId(),1,mainItemCategoryDto.getCreatedDate(),
                mainItemCategoryDto.getEditedBy(),mainItemCategoryDto.getEditedDate()
        );
        return mainItemCategoryRepositoryCustom.Save(mainItemCategoryModel);
    }

    @Override
    public boolean updateMainItemCategory(MainItemCategoryDto mainItemCategoryDto) throws Exception {
        MainItemCategoryModel mainItemCategoryModel=new MainItemCategoryModel(mainItemCategoryDto.getMainItemCategeryId(),mainItemCategoryDto.getCategoryName(),
                mainItemCategoryDto.getImagePath(),
                mainItemCategoryDto.getStatus(),mainItemCategoryDto.getUserId(),mainItemCategoryDto.getVisible(),mainItemCategoryDto.getCreatedDate(),
                mainItemCategoryDto.getEditedBy(),mainItemCategoryDto.getEditedDate()
        );
        return mainItemCategoryRepositoryCustom.Update(mainItemCategoryModel);
    }

    @Override
    public ArrayList<MainItemCategoryDto> getAllMainCategories(String quary) throws Exception {
        ArrayList<MainItemCategoryModel> allCategories=mainItemCategoryRepositoryCustom.getAll(quary);
        ArrayList<MainItemCategoryDto> categoryDtos=new ArrayList<>();
        for(MainItemCategoryModel categoryModel: allCategories){
            categoryDtos.add(new MainItemCategoryDto(categoryModel.getMainItemCategeryId(),
                    categoryModel.getCategoryName(),
                    categoryModel.getImagePath(),
                    categoryModel.getStatus(),
                    categoryModel.getUserId(),
                    categoryModel.getVisible(),
                    categoryModel.getCreatedDate(),
                    categoryModel.getEditedBy(),
                    categoryModel.getEditedDate()));
        }
        return categoryDtos;
    }

    @Override
    public Integer searchMainItemCategory(String key) throws Exception {
        Integer categoryId=mainItemCategoryRepositoryCustom.getMainCategory(key);
        return categoryId;
    }

    @Override
    public Integer getCatCode(String catName) {
        return mainItemCategoryRepositoryCustom.getCatCode(catName);
    }
}
