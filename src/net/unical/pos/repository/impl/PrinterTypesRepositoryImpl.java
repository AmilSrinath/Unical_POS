/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.PosMainPrinterTypes;
import net.unical.pos.repository.custom.PrinterTypesRepositoryCustom;

/**
 *
 * @author Sanjuka
 */
public class PrinterTypesRepositoryImpl implements PrinterTypesRepositoryCustom{

    @Override
    public ArrayList<PosMainPrinterTypes> getAll(String quary) throws Exception {
        ResultSet rst=Statement.executeQuery("Select * from pos_main_printer_type_tb "+quary);
        
        ArrayList<PosMainPrinterTypes> mainPrinterTypeses=new ArrayList<>();
        while(rst.next()){
            mainPrinterTypeses.add(new PosMainPrinterTypes(rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getInt(5)
            ));
        }
        return mainPrinterTypeses;
    }

    @Override
    public boolean save(PosMainPrinterTypes mainPrinterTypes) throws Exception {
        return Statement.executeUpdate("Insert into pos_main_printer_type_tb values(?,?,?,?,?)",
                mainPrinterTypes.getPrinterTypeId(),
                mainPrinterTypes.getName(),
                mainPrinterTypes.getUserd(),
                mainPrinterTypes.getStatus(),
                mainPrinterTypes.getVisible()) > 0;
    }
    
}
