/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hspc.pos.controller;

import java.util.ArrayList;
import net.hspc.pos.dto.MainItemCategoryDto;
import net.hspc.pos.factory.RepositoryFactory;
import net.hspc.pos.repository.custom.MainItemCategoryRepositoryCustom;

/**
 *
 * @author Sanjuka
 */
public class MainItemCategoryController {
    
    private MainItemCategoryRepositoryCustom mainItemCategoryRepositoryCustom;
    

    
    public MainItemCategoryController() {
        mainItemCategoryRepositoryCustom = RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.MAIN_ITEM_CATEGORY);
    }
    
    public boolean saveMainItemCategory(MainItemCategoryDto mainItemCategoryDto) throws Exception{
        return mainItemCategoryRepositoryCustom.saveMainItemCategory(mainItemCategoryDto);
    }
    
    public boolean updateMainItemCategory(MainItemCategoryDto mainItemCategoryDto) throws Exception{
//        return .saveDoctor(mainItemCategoryDto);

    return true;
    }
    
    public ArrayList<MainItemCategoryDto> getAll()throws Exception{
        return mainItemCategoryRepositoryCustom.getAllMainCategories();
    }
}
