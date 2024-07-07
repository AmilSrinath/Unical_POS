/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.GoodReceivedNoteDto;

/**
 *
 * @author Sanjuka
 */
public interface GrnServiceCustom{

    public Integer saveGrn(GoodReceivedNoteDto grnDto)throws Exception;

    public ArrayList<GoodReceivedNoteDto> getGrnList()throws Exception;
    
}
