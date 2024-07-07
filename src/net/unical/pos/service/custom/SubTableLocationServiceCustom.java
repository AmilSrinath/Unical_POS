/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.SubTableLocationDto;

/**
 *
 * @author HP
 */
public interface SubTableLocationServiceCustom {
    
     public boolean saveSubTableLocation(SubTableLocationDto subTableLocationDto) throws Exception;

    public boolean updateSubTableLocation(SubTableLocationDto subTableLocationDto) throws Exception;

    public ArrayList<SubTableLocationDto> getAllSubTableLocation() throws Exception;

    public SubTableLocationDto searchSubTableLocation(String key) throws Exception;
}
