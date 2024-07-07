/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.SubItemCategoryModel;
import net.unical.pos.repository.custom.SubItemCategoryRepositoryCustom;

/**
 *
 * @author Sanjuka
 */
public class SubItemCategoryRepositoryImpl implements SubItemCategoryRepositoryCustom{

    public SubItemCategoryRepositoryImpl() {
    }

    @Override
    public boolean Save(SubItemCategoryModel entity) throws Exception {
        return Statement.executeUpdate("Insert into pos_sub_item_category_tb values(?,?,?,?,?,?,?,?)",
                0,
                entity.getMainItemCategoryId(),
                entity.getMainCategoryName(),
                entity.getSubCategoryName(),
                entity.getImagePath(),
                entity.getStatus(),
                entity.getUserId(),
                entity.getVisible())>0;
    }

    @Override
    public boolean Update(SubItemCategoryModel entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Delete(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SubItemCategoryModel Search(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<SubItemCategoryModel> getAll() throws Exception {
        ResultSet rst=Statement.executeQuery("SELECT * FROM pos_sub_item_category_tb WHERE visible=1");
        
        ArrayList<SubItemCategoryModel> categoryModels=new ArrayList<>();
        while(rst.next()){
            categoryModels.add(new SubItemCategoryModel(rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getInt(7),
                    rst.getInt(8)
            ));
        }
        return categoryModels;
    }

    @Override
    public ArrayList<SubItemCategoryModel> getSearchItems(String key) throws Exception {
        ResultSet rst=Statement.executeQuery("SELECT * FROM pos_sub_item_category_tb WHERE main_item_category_name='"+key+"'");
        System.out.println("SELECT * FROM pos_sub_item_category_tb WHERE main_item_category_name='"+key+"'");
        
        ArrayList<SubItemCategoryModel> categoryModels=new ArrayList<>();
        while(rst.next()){
            categoryModels.add(new SubItemCategoryModel(rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getInt(7),
                    rst.getInt(8)
            ));
        }
        return categoryModels;
    }

    @Override
    public Integer getSubCategory(String key) throws Exception {
        ResultSet rst=Statement.executeQuery("SELECT sub_item_category_id FROM pos_sub_item_category_tb WHERE sub_item_category_name='"+key+"'");
        
        Integer id=null;
        if(rst.next()){
            id=rst.getInt(1);
        }
        return id;
    }

    @Override
    public ArrayList<SubItemCategoryModel> getSubItemCategories(Integer key) throws Exception {
        ResultSet rst=Statement.executeQuery("SELECT * FROM pos_sub_item_category_tb WHERE main_item_category_id='"+key+"'");
        
        ArrayList<SubItemCategoryModel> categoryModels=new ArrayList<>();
        while(rst.next()){
            categoryModels.add(new SubItemCategoryModel(rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getInt(7),
                    rst.getInt(8)
            ));
        }
        return categoryModels;
    }

    @Override
    public ArrayList<SubItemCategoryModel> getAllSubCategories() throws Exception {
        ResultSet rst=Statement.executeQuery("SELECT * FROM pos_sub_item_category_tb WHERE status=1 and visible=1");
        
        ArrayList<SubItemCategoryModel> categoryModels=new ArrayList<>();
        while(rst.next()){
            categoryModels.add(new SubItemCategoryModel(rst.getInt(1),
                    rst.getInt(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getInt(7),
                    rst.getInt(8)
            ));
        }
        return categoryModels;
    }
}
