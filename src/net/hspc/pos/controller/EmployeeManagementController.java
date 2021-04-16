/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hspc.pos.controller;

import java.util.ArrayList;
import net.hspc.pos.dto.EmployeeManagementDto;

/**
 *
 * @author Sanjuka
 */
public class EmployeeManagementController {
    
    public boolean saveEmployee(EmployeeManagementDto  employeeManagementDto) throws Exception{
        System.out.println(employeeManagementDto.getEmployeeId());
        System.out.println(employeeManagementDto.getTitle());
        System.out.println(employeeManagementDto.getName());
        System.out.println(employeeManagementDto.getDesignation());
        System.out.println(employeeManagementDto.getPrefix());
        System.out.println(employeeManagementDto.getCode());
        System.out.println(employeeManagementDto.getPhone());
        System.out.println(employeeManagementDto.getEmail());
        System.out.println(employeeManagementDto.getAddress());
        System.out.println(employeeManagementDto.isStatus());
//        return .saveEmployee(employeeManagementDto);
System.out.println("em");
    return true;
    }
    
    public ArrayList<EmployeeManagementDto> getAllEmployees()throws Exception{
//        return .getAllEmployees();
    return null;
    }
    
    public boolean updateEmployee(EmployeeManagementDto employeeManagementDto)throws Exception{
//        return .updateEmployee(employeeManagementDto);
    return true;
    }
}
