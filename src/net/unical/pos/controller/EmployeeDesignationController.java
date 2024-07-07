/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.EmployeeDesignationDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.EmployeeDesignationServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class EmployeeDesignationController {

    private EmployeeDesignationServiceCustom serviceCustom;
    
    public EmployeeDesignationController() {
        serviceCustom=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.EMPLOYEE_DESIGNATION);
    }
    
    public boolean saveDesignation(EmployeeDesignationDto designationDto)throws Exception{
        return serviceCustom.saveDesignation(designationDto);
    }
    
    public ArrayList<EmployeeDesignationDto> getAllDesignations(String quary)throws Exception{
        return serviceCustom.getAllDesignations(quary);
    }
}
