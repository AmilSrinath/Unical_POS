/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.MainPrinterTypesDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.PrinterTypesServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class PrinterTypesController {

    private PrinterTypesServiceCustom printerTypesServiceCustom;
    
    public PrinterTypesController() {
        printerTypesServiceCustom=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.PRINTER_TYPE);
    }

    public boolean savePrinterType(MainPrinterTypesDto mainPrinterTypesDto) throws Exception{
        return printerTypesServiceCustom.savePrinterType(mainPrinterTypesDto);
    }

    public ArrayList<MainPrinterTypesDto> getPrinterTypes(String quary) throws Exception{
        return printerTypesServiceCustom.getPrinterTypes(quary);
    }
    
}
