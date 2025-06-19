/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.EmployeeManagementDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.EmployeeManagementModel;
import net.unical.pos.repository.custom.EmployeeManagementRepositoryCustom;
import net.unical.pos.service.custom.EmployeeManagementServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class EmployeeManagementServiceImpl implements EmployeeManagementServiceCustom{
    
    private boolean isActive=false;
    private EmployeeManagementRepositoryCustom employeeManagementRepositoryCustom;
    
    public EmployeeManagementServiceImpl() {
        employeeManagementRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.EMPLOYEE_MANAGEMENT);
    }
    
    @Override
    public boolean saveEmployee(EmployeeManagementDto employeeManagementDto) throws Exception {
        
        
        EmployeeManagementModel employeeManagementModel=new EmployeeManagementModel(0, employeeManagementDto.getTitle(),
                employeeManagementDto.getName(), employeeManagementDto.getDesignation(), 
                employeeManagementDto.getPrefix(),employeeManagementDto.getCode(),employeeManagementDto.getCodePrefix(),
                employeeManagementDto.getImagePath(), employeeManagementDto.getPhone(), employeeManagementDto.getEmail(), 
                employeeManagementDto.getAddress(), employeeManagementDto.getStatus(),employeeManagementDto.getUserId(),1);
        
        return employeeManagementRepositoryCustom.Save(employeeManagementModel);
    }

    @Override
    public boolean updateEmployee(EmployeeManagementDto employeeManagementDto) throws Exception {
        EmployeeManagementModel employeeManagementModel=new EmployeeManagementModel(employeeManagementDto.getEmployeeId(), employeeManagementDto.getTitle(),
                employeeManagementDto.getName(), employeeManagementDto.getDesignation(), 
                employeeManagementDto.getPrefix(),employeeManagementDto.getCode(),employeeManagementDto.getCodePrefix(),
                employeeManagementDto.getImagePath(), employeeManagementDto.getPhone(), employeeManagementDto.getEmail(), 
                employeeManagementDto.getAddress(), employeeManagementDto.getStatus(),employeeManagementDto.getUserId(),1);
        
        return employeeManagementRepositoryCustom.Update(employeeManagementModel);
    }

    @Override
    public ArrayList<EmployeeManagementDto> getAllEmployees(String quary) throws Exception {
        ArrayList<EmployeeManagementModel> allEmployees=employeeManagementRepositoryCustom.getAll(quary);
        ArrayList<EmployeeManagementDto> employeeDto=new ArrayList<>();
        boolean isActive=false;
        for(EmployeeManagementModel employeeModel: allEmployees){
            if(employeeModel.getStatus()==1){
                isActive=true;
            }else{
                isActive=false;
            }
            employeeDto.add(new EmployeeManagementDto(employeeModel.getEmployeeId(),
                    employeeModel.getTitle(),
                    employeeModel.getName(),
                    employeeModel.getDesignation(),
                    employeeModel.getPrefix(),
                    employeeModel.getCode(),
                    employeeModel.getCodePrefix(),
                    employeeModel.getPhone(),
                    employeeModel.getEmail(),
                    employeeModel.getAddress(),
                    employeeModel.getImagePath(),
                    employeeModel.getStatus(),
                    employeeModel.getUserId()));
        }
        return employeeDto;
    }

    @Override
    public EmployeeManagementDto searchEmployee(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EmployeeManagementDto> getAllEmployees() throws Exception {
        ArrayList<EmployeeManagementModel> allEmployees=employeeManagementRepositoryCustom.getAll();
        ArrayList<EmployeeManagementDto> employeeDto=new ArrayList<>();
        boolean isActive=false;
        for(EmployeeManagementModel employeeModel: allEmployees){
            if(employeeModel.getStatus()==1){
                isActive=true;
            }else{
                isActive=false;
            }
            employeeDto.add(new EmployeeManagementDto(employeeModel.getEmployeeId(),
                    employeeModel.getTitle(),
                    employeeModel.getName(),
                    employeeModel.getDesignation(),
                    employeeModel.getPrefix(),
                    employeeModel.getCode(),
                    employeeModel.getCodePrefix(),
                    employeeModel.getPhone(),
                    employeeModel.getEmail(),
                    employeeModel.getAddress(),
                    employeeModel.getImagePath(),
                    employeeModel.getStatus(),
                    employeeModel.getUserId()));
        }
        return employeeDto;
    }
    
}
