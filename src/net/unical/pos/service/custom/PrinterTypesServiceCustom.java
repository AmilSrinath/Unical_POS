/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.MainPrinterTypesDto;

/**
 *
 * @author Sanjuka
 */
public interface PrinterTypesServiceCustom {

    public boolean savePrinterType(MainPrinterTypesDto mainPrinterTypesDto)throws Exception;

    public ArrayList<MainPrinterTypesDto> getPrinterTypes(String quary)throws Exception;
    
}
