/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.SubTableLocationDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.SubTableLocation;
import net.unical.pos.repository.custom.SubTableLocationRepositoryCustom;
import net.unical.pos.service.custom.SubTableLocationServiceCustom;

/**
 *
 * @author HP
 */
public class SubTableLocationServiceImpl implements SubTableLocationServiceCustom {

    private SubTableLocationRepositoryCustom subTableLocationRepositoryCustom;

    public SubTableLocationServiceImpl() {
        subTableLocationRepositoryCustom = RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.SUB_TABLE_LOCATION);
    }

    @Override
    public boolean saveSubTableLocation(SubTableLocationDto subTableLocationDto) throws Exception {
        SubTableLocation subTableLocation = new SubTableLocation(
                subTableLocationDto.getSubTableLocationId(),
                subTableLocationDto.getMainTableLocationId(),
                subTableLocationDto.getMainName(),
                subTableLocationDto.getSubName(),
                subTableLocationDto.getImagePath(),
                subTableLocationDto.getStatus(),
                subTableLocationDto.getUserId(),
                subTableLocationDto.getVisible());
        return subTableLocationRepositoryCustom.save(subTableLocation);
    }

    @Override
    public boolean updateSubTableLocation(SubTableLocationDto subTableLocationDto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<SubTableLocationDto> getAllSubTableLocation() throws Exception {
        ArrayList<SubTableLocationDto> tableLocationDtos = new ArrayList<>();
        ArrayList<SubTableLocation> subTableLocations = new ArrayList<>();
        subTableLocations = subTableLocationRepositoryCustom.getAllSubTables();
        for (SubTableLocation subTableLocation : subTableLocations) {
            tableLocationDtos.add(new SubTableLocationDto(
                    subTableLocation.getSubTableLocationId(),
                    subTableLocation.getMainTableLocationId(),
                    subTableLocation.getMainName(),
                    subTableLocation.getSubName(),
                    subTableLocation.getImagePath(),
                    subTableLocation.getStatus(),
                    subTableLocation.getUserId(),
                    subTableLocation.getVisible()));
        }
        return tableLocationDtos;
    }

    @Override
    public SubTableLocationDto searchSubTableLocation(String key) throws Exception {
        return null;
    }

}
