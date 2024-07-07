/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.EmployeeTitleDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.EmployeeTitleModel;
import net.unical.pos.repository.custom.EmployeeTitleRepositoryCustom;
import net.unical.pos.service.custom.EmployeeTitleServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class EmployeeTitleServiceImpl implements EmployeeTitleServiceCustom{

    private EmployeeTitleRepositoryCustom employeeTitleRepositoryCustom;
    
    public EmployeeTitleServiceImpl() {
        employeeTitleRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.EMPLOYEE_TITLE);
    }

    @Override
    public boolean saveTitle(EmployeeTitleDto titleDto) throws Exception {
        EmployeeTitleModel titleModel = new EmployeeTitleModel(
                titleDto.getTitleId(), 
                titleDto.getTitleName(), 
                titleDto.getStatus(), 
                titleDto.getUserId(),
                titleDto.getVisible());
        
        return employeeTitleRepositoryCustom.save(titleModel);
    }

    @Override
    public ArrayList<EmployeeTitleDto> getAllTitles(String quary) throws Exception {
        ArrayList<EmployeeTitleModel> titleModels=employeeTitleRepositoryCustom.getAll(quary);
        ArrayList<EmployeeTitleDto> titleDtos=new ArrayList<>();
        for(EmployeeTitleModel titleModel: titleModels){
            
            titleDtos.add(new EmployeeTitleDto(
                    titleModel.getTitleId(),
                    titleModel.getTitleName(),
                    titleModel.getStatus(),
                    titleModel.getUserId(),
                    titleModel.getVisible()));
        }
        return titleDtos;
    }
    
}
