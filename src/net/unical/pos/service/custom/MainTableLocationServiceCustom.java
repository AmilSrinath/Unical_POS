/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.MainTableLocationDto;

/**
 *
 * @author HP
 */
public interface MainTableLocationServiceCustom {
    public boolean saveMainTableLocation(MainTableLocationDto mainTableLocationDto)throws Exception;
    
    public boolean updateMainTableLocation(MainTableLocationDto mainTableLocationDto)throws Exception;
    
    public ArrayList<MainTableLocationDto> getAllMainTableLocation()throws Exception;

    public MainTableLocationDto searchMainTableLocation(String key)throws Exception;
}
