/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import net.unical.pos.model.StoreTemplateModel;

/**
 *
 * @author Sanjuka
 */
public interface StoreTemplateRepositoryCustom {

    public boolean save(StoreTemplateModel storeTemplateModel)throws Exception;
    
}
