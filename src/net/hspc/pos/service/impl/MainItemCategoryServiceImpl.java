/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hspc.pos.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import net.hspc.pos.dbConnection.DBConnection;
import net.hspc.pos.dbConnection.Statement;
import net.hspc.pos.model.MainItemCategoryModel;
import net.hspc.pos.service.custom.MainItemCategoryServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class MainItemCategoryServiceImpl implements MainItemCategoryServiceCustom{

    public MainItemCategoryServiceImpl() {
    }

    @Override
    public boolean Save(MainItemCategoryModel entity) throws Exception {
        return Statement.executeUpdate("Insert into pos_main_item_category_tb values(?,?,?,?)",
                0,
                entity.getCategoryName(),
                entity.getImagePath(),
                entity.getStatus())>0;
    }

    @Override
    public boolean Update(MainItemCategoryModel entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Delete(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MainItemCategoryModel Search(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MainItemCategoryModel> getAll() throws Exception {
        ResultSet rst=Statement.executeQuery("Select * from pos_main_item_category_tb");
        
        ArrayList<MainItemCategoryModel> categoryModels=new ArrayList<>();
        while(rst.next()){
            categoryModels.add(new MainItemCategoryModel(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            ));
        }
        return categoryModels;
    }
}
