/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.MainItemCategoryModel;
import net.unical.pos.repository.custom.MainItemCategoryRepositoryCustom;

/**
 *
 * @author Sanjuka
 */
public class MainItemCategoryRepositoryImpl implements MainItemCategoryRepositoryCustom{
    
    public MainItemCategoryRepositoryImpl() {
        
    }
    
    @Override
    public boolean Save(MainItemCategoryModel entity) throws Exception {
        return Statement.executeUpdate("Insert into pos_main_item_category_tb values(?,?,?,?,?,?,?,?,?)",
                0,
                entity.getCategoryName(),
                entity.getImagePath(),
                entity.getStatus(),
                entity.getUserId(),
                entity.getVisible(),
                entity.getCreatedDate(),
                entity.getEditedBy(),
                entity.getEditedDate()
                )>0;
    }

    @Override
    public ArrayList<MainItemCategoryModel> getAll(String quary) throws Exception {
        ResultSet rst=Statement.executeQuery("Select * from pos_main_item_category_tb "+quary);
        
        ArrayList<MainItemCategoryModel> categoryModels=new ArrayList<>();
        while(rst.next()){
//            System.out.println(rst.getString("main_item_category_name"));
            categoryModels.add(new MainItemCategoryModel(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getInt(5),
                    rst.getInt(6),
                    rst.getDate(7),
                    rst.getInt(8),
                    rst.getDate(9)
            ));
        }
        return categoryModels;
    }

    @Override
    public Integer getMainCategory(String key) throws Exception {
        ResultSet rst=Statement.executeQuery("SELECT main_item_category_id FROM pos_main_item_category_tb WHERE main_item_category_name='"+key+"' and visible=1");
        
        Integer id=null;
        if(rst.next()){
            id=rst.getInt(1);
        }
        return id;
    }

    @Override
    public boolean Update(MainItemCategoryModel mainItemCategoryModel) throws Exception{
        return Statement.executeUpdate("UPDATE pos_main_item_category_tb SET main_item_category_name = ?, image_path = ?,status=?,visible=?,edited_by=?,edited_date=? WHERE main_item_category_id=?",
                mainItemCategoryModel.getCategoryName(),mainItemCategoryModel.getImagePath(),mainItemCategoryModel.getStatus(),
                mainItemCategoryModel.getVisible(),mainItemCategoryModel.getEditedBy(),mainItemCategoryModel.getEditedDate(),mainItemCategoryModel.getMainItemCategeryId())>0;
    }
}
