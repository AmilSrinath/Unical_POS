/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import net.unical.pos.dto.StoreTemplateDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.StoreTemplateModel;
import net.unical.pos.repository.custom.StoreTemplateRepositoryCustom;
import net.unical.pos.service.custom.StoreTemplateServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class StoreTemplateServiceImpl implements StoreTemplateServiceCustom{

    private StoreTemplateRepositoryCustom storeTemplateRepositoryCustom;
    
    public StoreTemplateServiceImpl() {
        storeTemplateRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.STORE_TEMPLATE);
    }

    
    @Override
    public boolean saveTemplate(StoreTemplateDto storeTemplateDto) throws Exception {
        StoreTemplateModel storeTemplateModel = new StoreTemplateModel(
                storeTemplateDto.getTemplateId(),
                storeTemplateDto.getMainItemId(),
                storeTemplateDto.getSubItemId(),
                storeTemplateDto.getTemplateName(),
                storeTemplateDto.getQty(),
                storeTemplateDto.getUserId(),
                storeTemplateDto.getVisible());
        return storeTemplateRepositoryCustom.save(storeTemplateModel);
    }
    
}
