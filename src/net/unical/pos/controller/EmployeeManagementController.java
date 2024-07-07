/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.EmployeeManagementDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.EmployeeManagementServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class EmployeeManagementController {

    private EmployeeManagementServiceCustom employeeManagementServiceCustom;
    
    public EmployeeManagementController() {
        employeeManagementServiceCustom=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.EMPLOYEE_MANAGEMENT);
    }
    
    
    public boolean saveEmployee(EmployeeManagementDto  employeeManagementDto) throws Exception{
        return employeeManagementServiceCustom.saveEmployee(employeeManagementDto);
    }
    
    public ArrayList<EmployeeManagementDto> getAllEmployees(String quary)throws Exception{
        return employeeManagementServiceCustom.getAllEmployees(quary);
    }
    
    public ArrayList<EmployeeManagementDto> getAllEmployees()throws Exception{
        return employeeManagementServiceCustom.getAllEmployees();
    }
    
    public boolean updateEmployee(EmployeeManagementDto employeeManagementDto)throws Exception{
//        return .updateEmployee(employeeManagementDto);
    return true;
    }
}
