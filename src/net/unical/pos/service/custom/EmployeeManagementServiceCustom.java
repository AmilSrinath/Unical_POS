/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.EmployeeManagementDto;

/**
 *
 * @author Sanjuka
 */
public interface EmployeeManagementServiceCustom {
    
    public boolean saveEmployee(EmployeeManagementDto employeeManagementDto)throws Exception;
    
    public boolean updateEmployee(EmployeeManagementDto employeeManagementDto)throws Exception;
    
    public ArrayList<EmployeeManagementDto> getAllEmployees(String quary)throws Exception;
    
    public ArrayList<EmployeeManagementDto> getAllEmployees()throws Exception;

    public EmployeeManagementDto searchEmployee(String key)throws Exception;
}
