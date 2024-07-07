/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.ItemUnitTypeDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.PosMainPaymentTypes;
import net.unical.pos.model.PosMainUnitType;
import net.unical.pos.repository.custom.UnitTypesRepositoryCustom;
import net.unical.pos.service.custom.UnitTypesServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class UnitTypesServiceImpl implements UnitTypesServiceCustom{

    private UnitTypesRepositoryCustom unitTypesRepositoryCustom;
    
    public UnitTypesServiceImpl() {
        unitTypesRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.UNIT_TYPES);
    }

    @Override
    public boolean saveUnitType(ItemUnitTypeDto itemUnitTypeDto) throws Exception {
        PosMainUnitType posMainUnitType= new PosMainUnitType(
                itemUnitTypeDto.getUnitTypeId(), 
                itemUnitTypeDto.getName(), 
                itemUnitTypeDto.getStatus(), 
                itemUnitTypeDto.getUserId(),
                itemUnitTypeDto.getVisible());
        
        return unitTypesRepositoryCustom.save(posMainUnitType);
    }

    @Override
    public ArrayList<ItemUnitTypeDto> getUnitTypes(String quary) throws Exception {
        ArrayList<PosMainUnitType> mainUnitTypes=unitTypesRepositoryCustom.getAll(quary);
        ArrayList<ItemUnitTypeDto> itemUnitTypeDtos=new ArrayList<>();
        for(PosMainUnitType mainUnitType: mainUnitTypes){
            itemUnitTypeDtos.add(new ItemUnitTypeDto(
                    mainUnitType.getUnitTypeId(),
                    mainUnitType.getUnitType(),
                    mainUnitType.getStatus(),
                    mainUnitType.getUserId(),
                    mainUnitType.getVisible()));
        }
        return itemUnitTypeDtos;
    }
    
}
