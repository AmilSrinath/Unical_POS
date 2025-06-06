/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.EmployeeDesignationModel;
import net.unical.pos.repository.custom.EmployeeDesignationRepositoryCustom;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author Sanjuka
 */
public class EmployeeDesignationRepositoryImpl implements EmployeeDesignationRepositoryCustom{

    @Override
    public ArrayList<EmployeeDesignationModel> getAll(String quary)throws Exception{
        ResultSet rst=Statement.executeQuery("Select * from pos_emp_employee_designation_tb "+quary);
        
        ArrayList<EmployeeDesignationModel> designationModels=new ArrayList<>();
        while(rst.next()){
            designationModels.add(new EmployeeDesignationModel(rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getInt(5)
            ));
        }
        return designationModels;
    }

    @Override
    public boolean save(EmployeeDesignationModel designationModel) throws Exception {
        return Statement.executeUpdate("Insert into pos_emp_employee_designation_tb values(?,?,?,?,?)",
                designationModel.getDesignationId(),
                designationModel.getDesignationName(),
                LogInForm.userID,
                designationModel.getStatus(),
                designationModel.getVisible()) > 0;
    }
    
}
