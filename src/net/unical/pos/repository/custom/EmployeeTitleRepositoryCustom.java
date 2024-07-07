/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.model.EmployeeTitleModel;

/**
 *
 * @author Sanjuka
 */
public interface EmployeeTitleRepositoryCustom {

    public boolean save(EmployeeTitleModel titleModel)throws Exception;
    
    public ArrayList<EmployeeTitleModel>getAll(String quary)throws Exception;
}
