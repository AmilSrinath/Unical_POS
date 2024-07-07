/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.crudService.SuperServiceCrud;
import net.unical.pos.model.EmployeeManagementModel;

/**
 *
 * @author Sanjuka
 */
public interface EmployeeManagementRepositoryCustom extends SuperServiceCrud<EmployeeManagementModel, String>{

    public ArrayList<EmployeeManagementModel> getAll(String quary)throws Exception;
    
}
