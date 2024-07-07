/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.MainTableLocationDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.MainTableLocation;
import net.unical.pos.repository.custom.MainTableLocationRepositoryCustom;
import net.unical.pos.service.custom.MainTableLocationServiceCustom;

/**
 *
 * @author HP
 */
public class MainTableLocationServiceImpl implements MainTableLocationServiceCustom {

    private MainTableLocationRepositoryCustom mainTableLocationRepositoryCustom;

    public MainTableLocationServiceImpl() {
        mainTableLocationRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.MAIN_TABLE_LOCATION);

    }

    @Override
    public boolean saveMainTableLocation(MainTableLocationDto mainTableLocationDto) throws Exception {
        MainTableLocation mainTableLocation = new MainTableLocation(
                0, mainTableLocationDto.getLocationName(), mainTableLocationDto.getImagePath(), mainTableLocationDto.getStatus(), mainTableLocationDto.getUserId(), 1);
        return mainTableLocationRepositoryCustom.saveMainTableLocation(mainTableLocation);
    }

    @Override
    public boolean updateMainTableLocation(MainTableLocationDto mainTableLocationDto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<MainTableLocationDto> getAllMainTableLocation() throws Exception {
        ArrayList<MainTableLocationDto> tableLocationDtos = new ArrayList<>();
        ArrayList<MainTableLocation> mainTableLocations = new ArrayList<>();
        mainTableLocations = mainTableLocationRepositoryCustom.getAllMainTableLocations();

        for (MainTableLocation mainTableLocation : mainTableLocations) {
            tableLocationDtos.add(new MainTableLocationDto(mainTableLocation.getMainTableLocationId(), mainTableLocation.getLocationName(),
                    mainTableLocation.getImagePath(), mainTableLocation.getStatus(), mainTableLocation.getUserId(), mainTableLocation.getVisible()));
        }
        return tableLocationDtos;
    }

    @Override
    public MainTableLocationDto searchMainTableLocation(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
