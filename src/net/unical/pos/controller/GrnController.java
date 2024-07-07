/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.GoodReceivedNoteDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.GrnServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class GrnController {

    private GrnServiceCustom grnServiceCustom;
    
    public GrnController() {
        grnServiceCustom=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.GRN);
    }
    
    public Integer saveGrn(GoodReceivedNoteDto grnDto) throws Exception{
        return grnServiceCustom.saveGrn(grnDto);
    }
    
    public ArrayList<GoodReceivedNoteDto>getGrnList()throws Exception{
        return grnServiceCustom.getGrnList();
    }
}
