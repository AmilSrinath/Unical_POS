/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.EmployeeManagementModel;
import net.unical.pos.repository.custom.EmployeeManagementRepositoryCustom;
import net.unical.pos.view.main.LogInForm;

/**
 *
 * @author Sanjuka
 */
public class EmployeeManagementRepositoryImpl implements EmployeeManagementRepositoryCustom{

    public EmployeeManagementRepositoryImpl() {
    }
    
    @Override
    public boolean Save(EmployeeManagementModel entity) throws Exception {
        return Statement.executeUpdate("Insert into pos_emp_employee_management_tb values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                0,
                entity.getTitle(),
                entity.getName(),
                entity.getDesignation(),
                entity.getPrefix(),
                entity.getCode(),
                entity.getCodePrefix(),
                entity.getImagePath(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getAddress(),
                entity.getStatus(),
                LogInForm.userID,
                entity.getVisible())>0;
    }

    @Override
    public boolean Update(EmployeeManagementModel entity) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean Delete(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmployeeManagementModel Search(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EmployeeManagementModel> getAll() throws Exception {
        ResultSet rst=Statement.executeQuery("Select * from pos_emp_employee_management_tb");
        
        ArrayList<EmployeeManagementModel> managementModels=new ArrayList<>();
        while(rst.next()){
            managementModels.add(new EmployeeManagementModel(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getInt(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getInt(12),
                    rst.getInt(13),
                    rst.getInt(14)
            ));
        }
        return managementModels;
    }

    @Override
    public ArrayList<EmployeeManagementModel> getSearchItems(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EmployeeManagementModel> getAll(String quary) throws Exception{
        ResultSet rst=Statement.executeQuery("Select * from pos_emp_employee_management_tb "+quary);
        
        ArrayList<EmployeeManagementModel> managementModels=new ArrayList<>();
        while(rst.next()){
            managementModels.add(new EmployeeManagementModel(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getInt(9),
                    rst.getString(10),
                    rst.getString(11),
                    rst.getInt(12),
                    rst.getInt(13),
                    rst.getInt(14)
            ));
        }
        return managementModels;
    }
    
}
