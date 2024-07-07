/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import net.unical.pos.dto.StoreTemplateDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.StoreTemplateServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class StoreTemplateController {
    
    private StoreTemplateServiceCustom storeTemplateServiceCustom;

    public StoreTemplateController() {
        storeTemplateServiceCustom=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.STORE_TEMPLATE);
    }
    
    public boolean saveTemplate(StoreTemplateDto storeTemplateDto) throws Exception {
        return storeTemplateServiceCustom.saveTemplate(storeTemplateDto);
    }
}
