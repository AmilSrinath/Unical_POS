/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.model.EmployeeDesignationModel;

/**
 *
 * @author Sanjuka
 */
public interface EmployeeDesignationRepositoryCustom {

    public ArrayList<EmployeeDesignationModel> getAll(String quary)throws Exception;

    public boolean save(EmployeeDesignationModel designationModel)throws Exception;
    
}
