/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.ConfigTableDetailsDto;
import net.unical.pos.dto.ConfigTablesDto;

/**
 *
 * @author HP
 */
public interface ConfigTableServiceCustom {

    public boolean saveconfigTables(ConfigTablesDto configTablesDto) throws Exception;

    public boolean updateconfigTables(ConfigTablesDto configTablesDto) throws Exception;

    public ArrayList<ConfigTablesDto> getAllconfigTables() throws Exception;
    
    public ArrayList<ConfigTableDetailsDto> getAllconfigTableDetails() throws Exception;

    public ConfigTablesDto searchconfigTables(String key) throws Exception;
}
