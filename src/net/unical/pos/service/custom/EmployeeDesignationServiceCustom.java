/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.EmployeeDesignationDto;

/**
 *
 * @author Sanjuka
 */
public interface EmployeeDesignationServiceCustom {

    public boolean saveDesignation(EmployeeDesignationDto designationDto)throws Exception;

    public ArrayList<EmployeeDesignationDto> getAllDesignations(String quary)throws Exception;
    
}
