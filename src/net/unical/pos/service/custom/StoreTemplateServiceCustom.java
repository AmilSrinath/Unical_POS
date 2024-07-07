/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import net.unical.pos.dto.StoreTemplateDto;

/**
 *
 * @author Sanjuka
 */
public interface StoreTemplateServiceCustom {

    public boolean saveTemplate(StoreTemplateDto storeTemplateDto)throws Exception;
    
}
