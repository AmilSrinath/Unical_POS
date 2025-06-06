/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.StoreTemplateModel;
import net.unical.pos.repository.custom.StoreTemplateRepositoryCustom;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author Sanjuka
 */
public class StoreTemplateRepositoryImpl implements StoreTemplateRepositoryCustom{

    @Override
    public boolean save(StoreTemplateModel storeTemplateModel) throws Exception {
        return Statement.executeUpdate("Insert into pos_inv_item_store_tamplate_tb values(?,?,?,?,?,?,?)",
                storeTemplateModel.getTemplateId(),
                storeTemplateModel.getMainItemId(),
                storeTemplateModel.getSubItemId(),
                storeTemplateModel.getTemplateName(),
                storeTemplateModel.getQty(),
                LogInForm.userID,
                storeTemplateModel.getVisible())>0;
    }
    
}
