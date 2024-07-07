/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.ResultSet;
import net.unical.pos.dbConnection.Statement;
import java.util.ArrayList;
import net.unical.pos.model.PosMainUser;
import net.unical.pos.repository.custom.UserRepositoryCustom;

/**
 *
 * @author HP
 */
public class UserRepositoryimpl implements UserRepositoryCustom {

    @Override
    public boolean saveUser(PosMainUser posMainUser) throws Exception {
        return Statement.executeUpdate("Insert into pos_main_user_tb values(?,?,?,?,?,?,?,?)",
                0,
                posMainUser.getEmployeeId(),
                posMainUser.getRoleId(),
                posMainUser.getUserName(),
                posMainUser.getPassword(),
                posMainUser.getStatus(),
                posMainUser.getVisible(),
                posMainUser.getToken()) > 0;
    }

    @Override
    public boolean updateUserRole(PosMainUser PosMainUser) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteUser(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PosMainUser findOneUser(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<PosMainUser> getAllUser(String quary) throws Exception {
        ResultSet rst=Statement.executeQuery("SELECT * FROM pos_main_user_tb "+quary);
        ArrayList<PosMainUser> posMainUsers=new ArrayList<>();
        while(rst.next()){
            posMainUsers.add(new PosMainUser(rst.getInt(1), 
                    rst.getInt(2), 
                    rst.getInt(3), 
                    rst.getString(4), 
                    rst.getString(5), 
                    rst.getInt(6), 
                    rst.getInt(7), 
                    rst.getString(8)));
        }
        
        return posMainUsers;
    }

    @Override
    public boolean login(PosMainUser PosMainUser) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PosMainUser findUserName(String userName) throws Exception {
        ResultSet resultSet = Statement.executeQuery("SELECT * FROM pos_main_user_tb WHERE username  = " + userName + " visible = " + 1);
        PosMainUser mainUser = new PosMainUser();
        if (resultSet.next()) {
            mainUser = new PosMainUser(
                    resultSet.getInt("user_id "),
                    resultSet.getInt("mployee_id "),
                    resultSet.getInt("role_id "),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getInt("status"),
                    resultSet.getInt("visible"),
                    resultSet.getString("token"));
        }
        return mainUser;
    }

}
