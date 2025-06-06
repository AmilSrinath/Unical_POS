/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.EmployeeTitleModel;
import net.unical.pos.repository.custom.EmployeeTitleRepositoryCustom;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author Sanjuka
 */
public class EmployeeTitleRepositoryImpl implements EmployeeTitleRepositoryCustom{

    @Override
    public boolean save(EmployeeTitleModel titleModel) throws Exception {
        return Statement.executeUpdate("Insert into pos_emp_employee_title_tb values(?,?,?,?,?)",
                titleModel.getTitleId(),
                titleModel.getTitleName(),
                LogInForm.userID,
                titleModel.getStatus(),
                titleModel.getVisible()) > 0;
    }

    @Override
    public ArrayList<EmployeeTitleModel> getAll(String quary) throws Exception {
        ResultSet rst=Statement.executeQuery("Select * from pos_emp_employee_title_tb "+quary);
        
        ArrayList<EmployeeTitleModel> employeeTitleModels=new ArrayList<>();
        while(rst.next()){
            employeeTitleModels.add(new EmployeeTitleModel(rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getInt(5)
            ));
        }
        return employeeTitleModels;
    }
    
}
