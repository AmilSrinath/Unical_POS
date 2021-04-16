/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hspc.pos.repository.impl;

import java.util.ArrayList;
import net.hspc.pos.dto.MainItemCategoryDto;
import net.hspc.pos.factory.ServiceFactory;
import net.hspc.pos.model.MainItemCategoryModel;
import net.hspc.pos.repository.custom.MainItemCategoryRepositoryCustom;
import net.hspc.pos.service.custom.MainItemCategoryServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class MainItemCategoryRepositoryImpl implements MainItemCategoryRepositoryCustom{
    
    private MainItemCategoryServiceCustom mainItemCategoryServiceCustom;

    public MainItemCategoryRepositoryImpl() {
        mainItemCategoryServiceCustom=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.MAIN_ITEM_CATEGORY);
    }
    
    @Override
    public boolean saveMainItemCategory(MainItemCategoryDto mainItemCategoryDto) throws Exception {
        MainItemCategoryModel mainItemCategoryModel=new MainItemCategoryModel(0,mainItemCategoryDto.getCategoryName(),mainItemCategoryDto.getImagePath(),1);
        return mainItemCategoryServiceCustom.Save(mainItemCategoryModel);
    }

    @Override
    public boolean updateMainItemCategory(MainItemCategoryDto mainItemCategoryDto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MainItemCategoryDto> getAllMainCategories() throws Exception {
        ArrayList<MainItemCategoryModel> allCategories=mainItemCategoryServiceCustom.getAll();
        ArrayList<MainItemCategoryDto> categoryDtos=new ArrayList<>();
        boolean isActive=false;
        for(MainItemCategoryModel categoryModel: allCategories){
            if(categoryModel.getStatus()==1){
                isActive=true;
            }else{
                isActive=false;
            }
            categoryDtos.add(new MainItemCategoryDto(categoryModel.getMainItemCategeryId(),
                    categoryModel.getCategoryName(),
                    categoryModel.getImagePath(),
                    isActive
                    ));
        }
        return categoryDtos;
    }

    @Override
    public MainItemCategoryDto searchMainItemCategory(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
