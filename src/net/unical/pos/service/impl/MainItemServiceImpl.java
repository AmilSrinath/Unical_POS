/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.MainItemDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.PosMainItem;
import net.unical.pos.repository.custom.MainItemCategoryRepositoryCustom;
import net.unical.pos.repository.custom.MainItemRepositoryCustom;
import net.unical.pos.repository.custom.SubItemCategoryRepositoryCustom;
import net.unical.pos.service.custom.MainItemServiceCustom;

/**
 *
 * @author HP
 */
public class MainItemServiceImpl implements MainItemServiceCustom {

    private MainItemRepositoryCustom repo;
    private MainItemCategoryRepositoryCustom mainItemCategoryRepositoryCustom;
    private SubItemCategoryRepositoryCustom subItemCategoryRepositoryCustom;

    public MainItemServiceImpl() {
        repo = RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.MAIN_ITEM);
        mainItemCategoryRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.MAIN_ITEM_CATEGORY);
        subItemCategoryRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.SUB_ITEM_CATEGORY);
    }

    @Override
    public boolean saveItem(MainItemDto itemDto) throws Exception {
        
        PosMainItem item = new PosMainItem(itemDto.getItemId(),itemDto.getBarCode(),itemDto.getMainCategoryId(),itemDto.getSubCataegoryId(),
                 itemDto.getPrefix(), itemDto.getCodePrefix(), itemDto.getDiscount(),
                itemDto.getItemName(), itemDto.getUnitType(), itemDto.getPriterType(), itemDto.getCostPrice(), itemDto.getUnitPrice(),
                itemDto.getImagePath(),itemDto.getStatus(), itemDto.getGrnStatus(),itemDto.getSellingItem(), itemDto.getUserId(), 1);
        return repo.save(item);

    }

    @Override
    public boolean updateItem(MainItemDto itemDto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MainItemDto> getAllItem(String quary) throws Exception {
        ArrayList<PosMainItem> posMainItems=repo.getAllItems(quary);
        ArrayList<MainItemDto> mainItemDtos=new ArrayList<>();
        for(PosMainItem model: posMainItems ){
            mainItemDtos.add(new MainItemDto(model.getItemId(), model.getBarCode(),
                    model.getMainItemCategoryId(),model.getSubItemCategoryId(),  
                    model.getPrefix(), model.getCodePrefix(), model.getDiscount(), 
                    model.getItemName(), model.getUnitType(), model.getPriterType(), 
                    model.getCostPrice(), model.getUnitPrice(), model.getImagePath(), 
                    model.getGrnStatus(),model.getSellingItem(), model.getStatus(), model.getUserId()));
        }

        return mainItemDtos;
    }

    @Override
    public MainItemDto searchItem(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MainItemDto> getItem(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MainItemDto> searchAllItems(Integer key) throws Exception {
        ArrayList<PosMainItem> posMainItems=repo.searchAllItems(key);
        ArrayList<MainItemDto> mainItemDtos=new ArrayList<>();
        for(PosMainItem model: posMainItems ){
            mainItemDtos.add(new MainItemDto(model.getItemId(), model.getBarCode(),
                    model.getMainItemCategoryId(),model.getSubItemCategoryId(),  
                    model.getPrefix(), model.getCodePrefix(), model.getDiscount(), 
                    model.getItemName(), model.getUnitType(), model.getPriterType(), 
                    model.getCostPrice(), model.getUnitPrice(), model.getImagePath(), 
                    model.getGrnStatus(), model.getStatus(),model.getSellingItem(), model.getUserId()));
        }

        return mainItemDtos;
    }

    @Override
    public ArrayList<MainItemDto> searchAllSubItems(Integer key) throws Exception {
        ArrayList<PosMainItem> posMainItems=new ArrayList<>();
        ArrayList<MainItemDto> mainItemDtos=new ArrayList<>();
        posMainItems=repo.searchAllSubItems(key);
        
        for(PosMainItem model: posMainItems ){
            
            mainItemDtos.add(new MainItemDto(model.getItemId(), model.getMainItemCategoryId(),model.getSubItemCategoryId(), model.getBarCode(), 
                    model.getPrefix(), model.getCodePrefix(), model.getDiscount(), 
                    model.getItemName(), model.getUnitType(), model.getPriterType(), 
                    model.getCostPrice(), model.getUnitPrice(), model.getImagePath(), 
                    model.getGrnStatus(), model.getStatus(),model.getSellingItem(), model.getUserId()));
        }
        
        return mainItemDtos;
    }

    @Override
    public ArrayList<MainItemDto> searchAllItems(Integer main, Integer sub)throws Exception{
        ArrayList<PosMainItem> posMainItems=new ArrayList<>();
        ArrayList<MainItemDto> mainItemDtos=new ArrayList<>();
        posMainItems=repo.searchAllItems(main,sub);
        
        for(PosMainItem model: posMainItems ){
            
            mainItemDtos.add(new MainItemDto(model.getItemId(), model.getMainItemCategoryId(),model.getSubItemCategoryId(), model.getBarCode(), 
                    model.getPrefix(), model.getCodePrefix(), model.getDiscount(), 
                    model.getItemName(), model.getUnitType(), model.getPriterType(), 
                    model.getCostPrice(), model.getUnitPrice(), model.getImagePath(), 
                    model.getGrnStatus(), model.getStatus(),model.getSellingItem(), model.getUserId()));
        }
        
        return mainItemDtos;
    }

    @Override
    public ArrayList<MainItemDto> searchAllItems(String key) throws Exception {
        ArrayList<PosMainItem> posMainItems=new ArrayList<>();
        ArrayList<MainItemDto> mainItemDtos=new ArrayList<>();
        posMainItems=repo.searchAllItems(key);
        
        for(PosMainItem model: posMainItems ){
            
            mainItemDtos.add(new MainItemDto(model.getItemId(), model.getMainItemCategoryId(),model.getSubItemCategoryId(), model.getBarCode(), 
                    model.getPrefix(), model.getCodePrefix(), model.getDiscount(), 
                    model.getItemName(), model.getUnitType(), model.getPriterType(), 
                    model.getCostPrice(), model.getUnitPrice(), model.getImagePath(), 
                    model.getGrnStatus(), model.getStatus(),model.getSellingItem(), model.getUserId()));
        }
        
        return mainItemDtos;
    }

}
