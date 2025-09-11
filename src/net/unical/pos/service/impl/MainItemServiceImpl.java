/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.dbConnection.DBConnection;
import net.unical.pos.dto.MainItemDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.model.ItemRegistry;
import net.unical.pos.model.PosMainItem;
import net.unical.pos.repository.custom.MainItemCategoryRepositoryCustom;
import net.unical.pos.repository.custom.MainItemRepositoryCustom;
import net.unical.pos.repository.custom.SubItemCategoryRepositoryCustom;
import net.unical.pos.service.custom.ItemRegistryServiceCustom;
import net.unical.pos.service.custom.MainItemServiceCustom;

/**
 *
 * @author HP
 */
public class MainItemServiceImpl implements MainItemServiceCustom {

    private MainItemRepositoryCustom repo;
    private MainItemCategoryRepositoryCustom mainItemCategoryRepositoryCustom;
    private SubItemCategoryRepositoryCustom subItemCategoryRepositoryCustom;
    private ItemRegistryServiceCustom itemRegistryServiceCustom;

    public MainItemServiceImpl() {
        repo = RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.MAIN_ITEM);
        mainItemCategoryRepositoryCustom = RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.MAIN_ITEM_CATEGORY);
        subItemCategoryRepositoryCustom = RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.SUB_ITEM_CATEGORY);
        this.itemRegistryServiceCustom = ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ITEM_REGISTRY);
    }

    @Override
    public boolean saveItem(MainItemDto itemDto) throws Exception{
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            PosMainItem item = new PosMainItem(itemDto.getItemId(), itemDto.getBarCode(), itemDto.getMainCategoryId(), itemDto.getSubCataegoryId(),
                    itemDto.getPrefix(), itemDto.getCodePrefix(), itemDto.getDiscount(),
                    itemDto.getItemName(), itemDto.getUnitType(), itemDto.getPriterType(), itemDto.getCostPrice(), itemDto.getUnitPrice(),
                    itemDto.getImagePath(), itemDto.getStatus(), itemDto.getGrnStatus(), itemDto.getSellingItem(), itemDto.getUserId(), 1, itemDto.getWeight());
            boolean isSaved = itemRegistryServiceCustom.saveRegistry(item, connection);
            
            if (isSaved) {
                System.out.println("Registry is saved!!");
                ItemRegistry itemRegistry = itemRegistryServiceCustom.getLatestRegistry();
                item.setRegistryId(itemRegistry.getRegistryId());
                boolean isItemSaved = repo.save(item, connection);
                if (isItemSaved) {
                    System.out.println("Item is saved!!");
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }
            } else {
                connection.rollback();
                return false;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainItemServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("call the finally block");
            if (connection != null) {
                connection.setAutoCommit(true);
            }
        }
        return false;
    }

    @Override
    public boolean updateItem(MainItemDto itemDto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MainItemDto> getAllItem(String quary) throws Exception {
        ArrayList<PosMainItem> posMainItems = repo.getAllItems(quary);
        ArrayList<MainItemDto> mainItemDtos = new ArrayList<>();
        for (PosMainItem model : posMainItems) {
            mainItemDtos.add(new MainItemDto(model.getItemId(), model.getBarCode(),
                    model.getMainItemCategoryId(), model.getSubItemCategoryId(),
                    model.getPrefix(), model.getCodePrefix(), model.getDiscount(),
                    model.getItemName(), model.getUnitType(), model.getPriterType(),
                    model.getCostPrice(), model.getUnitPrice(), model.getImagePath(),
                    model.getGrnStatus(), model.getSellingItem(), model.getStatus(), model.getUserId(), model.getWeight()));
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
        ArrayList<PosMainItem> posMainItems = repo.searchAllItems(key);
        ArrayList<MainItemDto> mainItemDtos = new ArrayList<>();
        for (PosMainItem model : posMainItems) {
            mainItemDtos.add(new MainItemDto(model.getItemId(), model.getBarCode(),
                    model.getMainItemCategoryId(), model.getSubItemCategoryId(),
                    model.getPrefix(), model.getCodePrefix(), model.getDiscount(),
                    model.getItemName(), model.getUnitType(), model.getPriterType(),
                    model.getCostPrice(), model.getUnitPrice(), model.getImagePath(),
                    model.getGrnStatus(), model.getStatus(), model.getSellingItem(), model.getUserId(), model.getWeight()));
        }

        return mainItemDtos;
    }

    @Override
    public ArrayList<MainItemDto> searchAllSubItems(Integer key) throws Exception {
        ArrayList<PosMainItem> posMainItems = new ArrayList<>();
        ArrayList<MainItemDto> mainItemDtos = new ArrayList<>();
        posMainItems = repo.searchAllSubItems(key);

        for (PosMainItem model : posMainItems) {

            mainItemDtos.add(new MainItemDto(model.getItemId(), model.getMainItemCategoryId(), model.getSubItemCategoryId(), model.getBarCode(),
                    model.getPrefix(), model.getCodePrefix(), model.getDiscount(),
                    model.getItemName(), model.getUnitType(), model.getPriterType(),
                    model.getCostPrice(), model.getUnitPrice(), model.getImagePath(),
                    model.getGrnStatus(), model.getStatus(), model.getSellingItem(), model.getUserId(), model.getWeight()));
        }

        return mainItemDtos;
    }

    @Override
    public ArrayList<MainItemDto> searchAllItems(Integer main, Integer sub) throws Exception {
        ArrayList<PosMainItem> posMainItems = new ArrayList<>();
        ArrayList<MainItemDto> mainItemDtos = new ArrayList<>();
        posMainItems = repo.searchAllItems(main, sub);

        for (PosMainItem model : posMainItems) {

            mainItemDtos.add(new MainItemDto(model.getItemId(), model.getMainItemCategoryId(), model.getSubItemCategoryId(), model.getBarCode(),
                    model.getPrefix(), model.getCodePrefix(), model.getDiscount(),
                    model.getItemName(), model.getUnitType(), model.getPriterType(),
                    model.getCostPrice(), model.getUnitPrice(), model.getImagePath(),
                    model.getGrnStatus(), model.getStatus(), model.getSellingItem(), model.getUserId(), model.getWeight()));
        }

        return mainItemDtos;
    }

    @Override
    public ArrayList<MainItemDto> searchAllItems(String key) throws Exception {
        ArrayList<PosMainItem> posMainItems = new ArrayList<>();
        ArrayList<MainItemDto> mainItemDtos = new ArrayList<>();
        posMainItems = repo.searchAllItems(key);

        for (PosMainItem model : posMainItems) {

            mainItemDtos.add(new MainItemDto(model.getItemId(), model.getMainItemCategoryId(), model.getSubItemCategoryId(), model.getBarCode(),
                    model.getPrefix(), model.getCodePrefix(), model.getDiscount(),
                    model.getItemName(), model.getUnitType(), model.getPriterType(),
                    model.getCostPrice(), model.getUnitPrice(), model.getImagePath(),
                    model.getGrnStatus(), model.getStatus(), model.getSellingItem(), model.getUserId(), model.getWeight()));
        }

        return mainItemDtos;
    }

    @Override
    public String getmainItemid(String mainCat) {
        return null;
    }

    @Override
    public boolean isNewItem(String itemName) {
        return repo.isNewItem(itemName);
    }

}
