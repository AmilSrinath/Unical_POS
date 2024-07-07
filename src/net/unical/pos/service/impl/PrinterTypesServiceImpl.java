/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.MainPrinterTypesDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.PosMainPrinterTypes;
import net.unical.pos.repository.custom.PrinterTypesRepositoryCustom;
import net.unical.pos.service.custom.PrinterTypesServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class PrinterTypesServiceImpl implements PrinterTypesServiceCustom{

    private PrinterTypesRepositoryCustom printerTypesRepositoryCustom;
    
    public PrinterTypesServiceImpl() {
        printerTypesRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.PRINTER_TYPE);
    }
    
    @Override
    public boolean savePrinterType(MainPrinterTypesDto mainPrinterTypesDto) throws Exception {
        PosMainPrinterTypes mainPrinterTypes = new PosMainPrinterTypes(
                mainPrinterTypesDto.getPrinterTypeId(), 
                mainPrinterTypesDto.getName(), 
                mainPrinterTypesDto.getStatus(), 
                mainPrinterTypesDto.getUserd(),
                mainPrinterTypesDto.getVisible());
        
        return printerTypesRepositoryCustom.save(mainPrinterTypes);
    }

    @Override
    public ArrayList<MainPrinterTypesDto> getPrinterTypes(String quary) throws Exception {
        ArrayList<PosMainPrinterTypes> mainPrinterTypeses=printerTypesRepositoryCustom.getAll(quary);
        ArrayList<MainPrinterTypesDto> printerTypesDtos=new ArrayList<>();
        for(PosMainPrinterTypes printerTypes: mainPrinterTypeses){
            printerTypesDtos.add(new MainPrinterTypesDto(
                    printerTypes.getPrinterTypeId(),
                    printerTypes.getName(),
                    printerTypes.getStatus(),
                    printerTypes.getUserd(),
                    printerTypes.getVisible()));
        }
        return printerTypesDtos;
    }
    
}
