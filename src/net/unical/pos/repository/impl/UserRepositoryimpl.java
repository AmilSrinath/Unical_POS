/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.ResultSet;
import net.unical.pos.dbConnection.Statement;
import java.util.ArrayList;
import net.unical.pos.configurations.Log;
import net.unical.pos.dto.UserDto;
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
        ResultSet resultSet = Statement.executeQuery("SELECT * FROM pos_main_user_tb WHERE user_id = " + id);
    
        if (resultSet.next()) {
            return new PosMainUser(
                    resultSet.getInt("user_id"),
                    resultSet.getInt("employee_id"),
                    resultSet.getInt("role_id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getInt("status"),
                    resultSet.getInt("visible"),
                    resultSet.getString("token")
            );
        }
        return null;
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

    @Override
    public UserDto updateUser(UserDto userDto) {
        try {
            boolean updated = Statement.executeUpdate(
                    "UPDATE pos_main_user_tb SET employee_id = ?, role_id = ?, username = ?, status = ?, visible = ?, token = ? WHERE user_id = ?",
                    userDto.getEmployeeId(),
                    userDto.getRoleId(),
                    userDto.getUserName(),
                    userDto.getStatus(),
                    userDto.getVisible(),
                    userDto.getToken(),
                    userDto.getUserId()
            ) > 0;

            return updated ? userDto : null;
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(e, e);
            return null;
        }
    }

    @Override
    public UserDto updateUserWithPassword(UserDto userDto) {
        try {
            boolean updated = Statement.executeUpdate(
                    "UPDATE pos_main_user_tb SET employee_id = ?, role_id = ?, username = ?, password = ?, status = ?, visible = ?, token = ? WHERE user_id = ?",
                    userDto.getEmployeeId(),
                    userDto.getRoleId(),
                    userDto.getUserName(),
                    userDto.getPassword(),
                    userDto.getStatus(),
                    userDto.getVisible(),
                    userDto.getToken(),
                    userDto.getUserId()
            ) > 0;

            
            System.out.println("userDto.getUserId() : "+userDto.getUserId());
            System.out.println("updated : "+updated);
            
            return updated ? userDto : null;
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(e, e);
            return null;
        }
    }

}
