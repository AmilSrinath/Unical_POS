/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.EmployeeDesignationDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.EmployeeDesignationModel;
import net.unical.pos.repository.custom.EmployeeDesignationRepositoryCustom;
import net.unical.pos.service.custom.EmployeeDesignationServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class EmployeeDesignationServiceImpl implements EmployeeDesignationServiceCustom{

    private EmployeeDesignationRepositoryCustom designationRepositoryCustom;
    
    public EmployeeDesignationServiceImpl() {
        designationRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.EMPLOYEE_DESIGNATION);
    }

    @Override
    public boolean saveDesignation(EmployeeDesignationDto designationDto) throws Exception {
        EmployeeDesignationModel designationModel = new EmployeeDesignationModel(
                designationDto.getDesignationId(), 
                designationDto.getDesignationName(), 
                designationDto.getStatus(), 
                designationDto.getUserId(),
                designationDto.getVisible());
        
        return designationRepositoryCustom.save(designationModel);
        
    }

    @Override
    public ArrayList<EmployeeDesignationDto> getAllDesignations(String quary) throws Exception {
        ArrayList<EmployeeDesignationModel> designationModels=designationRepositoryCustom.getAll(quary);
        ArrayList<EmployeeDesignationDto> designationDtos=new ArrayList<>();
        for(EmployeeDesignationModel designationModel: designationModels){
            
            designationDtos.add(new EmployeeDesignationDto(designationModel.getDesignationId(),
                    designationModel.getDesignationName(),
                    designationModel.getStatus(),
                    designationModel.getUserId(),
                    designationModel.getVisible()));
        }
        return designationDtos;
    }
    
}
