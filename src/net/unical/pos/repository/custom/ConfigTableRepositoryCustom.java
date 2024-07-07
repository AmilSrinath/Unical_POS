/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.model.ConfigTable;
import net.unical.pos.model.ConfigTableDetails;

/**
 *
 * @author HP
 */
public interface ConfigTableRepositoryCustom {

    public Integer save(ConfigTable configTable);

    public ArrayList<ConfigTable> getAll();
    
    public ArrayList<ConfigTableDetails> getAllDetails();

   
    
}
