/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.model.PosMainUnitType;

/**
 *
 * @author Sanjuka
 */
public interface UnitTypesRepositoryCustom {

    public boolean save(PosMainUnitType posMainUnitType)throws Exception;

    public ArrayList<PosMainUnitType> getAll(String quary)throws Exception;
    
}
