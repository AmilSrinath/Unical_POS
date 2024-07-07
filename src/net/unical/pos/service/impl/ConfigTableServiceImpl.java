/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import net.unical.pos.dbConnection.DBConnection;
import net.unical.pos.dto.ConfigTableDetailsDto;
import net.unical.pos.dto.ConfigTablesDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.ConfigTable;
import net.unical.pos.model.ConfigTableDetails;
import net.unical.pos.repository.custom.ConfigTableDetailsRepositoryCustom;
import net.unical.pos.repository.custom.ConfigTableRepositoryCustom;
import net.unical.pos.service.custom.ConfigTableServiceCustom;

/**
 *
 * @author HP
 */
public class ConfigTableServiceImpl implements ConfigTableServiceCustom {

    private ConfigTableRepositoryCustom configTableRepositoryCustom;
    private ConfigTableDetailsRepositoryCustom configTableDetailsRepositoryCustom;

    public ConfigTableServiceImpl() {
        configTableRepositoryCustom = RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.CONFIG_TABLE);
        configTableDetailsRepositoryCustom = RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.CONFIG_TABLE_DETAILS);
    }

    @Override
    public boolean saveconfigTables(ConfigTablesDto configTablesDto) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        try {
            ConfigTable configTable = new ConfigTable(
                    0,
                    configTablesDto.getMainTableLocationId(),
                    configTablesDto.getSubTableLocationId(),
                    configTablesDto.getMainLocationName(),
                    configTablesDto.getSubLocationName(),
                    configTablesDto.getTableCodePrefix(),
                    configTablesDto.getNumOfTables(),
                    configTablesDto.getImagePath(),
                    1,
                    configTablesDto.getUserId(),
                    1);
            Integer savedConfig = configTableRepositoryCustom.save(configTable);
            if (savedConfig <= 0) {
                connection.rollback();
                return false;
            } else {
                for (int i = 0; i < configTablesDto.getNumOfTables(); i++) {
                    ConfigTableDetails configTableDetails = new ConfigTableDetails(
                            0,
                            savedConfig,
                            configTablesDto.getMainTableLocationId(),
                            configTablesDto.getSubTableLocationId(),
                            configTablesDto.getTableCodePrefix(),
                            0,
                            0,
                            configTablesDto.getImagePath(),
                            1,
                            configTablesDto.getUserId(),
                            1);
                    Integer saved = configTableDetailsRepositoryCustom.save(configTableDetails);
                    if (saved <= 0) {
                        connection.rollback();
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public boolean updateconfigTables(ConfigTablesDto configTablesDto) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ConfigTablesDto> getAllconfigTables() throws Exception {
        ArrayList<ConfigTablesDto> configTablesDtos = new ArrayList<>();
        ArrayList<ConfigTable> configTables = configTableRepositoryCustom.getAll();
        
        for (ConfigTable configTable : configTables) {
            configTablesDtos.add(new ConfigTablesDto(
                    configTable.getConfigTablesId(), 
                   configTable.getMainTableLocationId(),
                    configTable.getSubTableLocationId(),
                    configTable.getMainLocationName(),
                    configTable.getSubLocationName(),
                    configTable.getTableCodePrefix(),
                    configTable.getNumOfTables(),
                    configTable.getImagePath(),
                    configTable.getStatus(),
                    configTable.getUserId(),
                    configTable.getVisible()));
            
        }
        return configTablesDtos;
    }

    @Override
    public ConfigTablesDto searchconfigTables(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ConfigTableDetailsDto> getAllconfigTableDetails() throws Exception {
        ArrayList<ConfigTableDetailsDto> configTableDetailsDtos = new ArrayList<>();
        ArrayList<ConfigTableDetails> configTableDetails = configTableRepositoryCustom.getAllDetails();
        
        for (ConfigTableDetails configTable : configTableDetails) {
            configTableDetailsDtos.add(new ConfigTableDetailsDto(
                    configTable.getConfigDetailTablesId(),
                    configTable.getConfigTablesId(), 
                   configTable.getMainTableLocationId(),
                    configTable.getSubTableLocationId(),
                    configTable.getTableName(),
                    configTable.getWidth(),
                    configTable.getHeight(),
                    configTable.getImagePath(),
                    configTable.getStatus(),
                    configTable.getUserId(),
                    configTable.getVisible()));
            
        }
        return configTableDetailsDtos;
    }

}
