/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.PosMainPaymentTypes;
import net.unical.pos.model.PosMainUnitType;
import net.unical.pos.repository.custom.UnitTypesRepositoryCustom;

/**
 *
 * @author Sanjuka
 */
public class UnitTypesRepositoryImpl implements UnitTypesRepositoryCustom{

    @Override
    public boolean save(PosMainUnitType posMainUnitType) throws Exception {
        return Statement.executeUpdate("Insert into pos_main_unit_type_tb values(?,?,?,?,?)",
                posMainUnitType.getUnitTypeId(),
                posMainUnitType.getUnitType(),
                posMainUnitType.getUserId(),
                posMainUnitType.getStatus(),
                posMainUnitType.getVisible()) > 0;
    }

    @Override
    public ArrayList<PosMainUnitType> getAll(String quary) throws Exception {
        ResultSet rst=Statement.executeQuery("Select * from pos_main_unit_type_tb "+quary);
        
        ArrayList<PosMainUnitType> mainUnitTypes=new ArrayList<>();
        while(rst.next()){
            mainUnitTypes.add(new PosMainUnitType(rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getInt(5)
            ));
        }
        return mainUnitTypes;
    }
    
}
