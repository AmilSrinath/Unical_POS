/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.EmployeeTitleDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.EmployeeTitleServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class EmployeeTitleController {

    private EmployeeTitleServiceCustom employeeTitleServiceCustom;
    
    public EmployeeTitleController() {
        employeeTitleServiceCustom=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.EMPLOYEE_TITLE);
    }

    public boolean saveTitle(EmployeeTitleDto titleDto)throws Exception{
        return employeeTitleServiceCustom.saveTitle(titleDto);
    }

    public ArrayList<EmployeeTitleDto> getAllTitles(String query) throws Exception{
        return employeeTitleServiceCustom.getAllTitles(query);
    }
    
    
}
