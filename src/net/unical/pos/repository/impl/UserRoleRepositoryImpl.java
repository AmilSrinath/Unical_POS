/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.PosMainUserRole;
import net.unical.pos.repository.custom.UserRoleRepositoryCustom;

/**
 *
 * @author HP
 */
public class UserRoleRepositoryImpl implements UserRoleRepositoryCustom {

    @Override
    public boolean save(PosMainUserRole posMainUserRole) throws Exception {

        return Statement.executeUpdate("INSERT INTO pos_main_user_role_tb VALUES (?,?,?,?,?)",
                0,
                posMainUserRole.getRole(),
                posMainUserRole.getStatus(),
                posMainUserRole.getUserId(),
                1) > 0;

    }

    @Override
    public boolean update(PosMainUserRole posMainUserRole) throws Exception {
        return Statement.executeUpdate("UPDATE pos_main_user_role_tb SET role=(?) ,status=(?) ,visible=(?) WHERE id=(?)",
                posMainUserRole.getRole(), posMainUserRole.getStatus(), posMainUserRole.getVisible(), posMainUserRole.getRoleId()) > 0;
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return Statement.executeUpdate("UPDATE pos_main_user_role_tb SET visible=(?) WHERE id=(?)",
                0, id) > 0;
    }

    @Override
    public ArrayList<PosMainUserRole> getAll(String quary) throws Exception {

        ResultSet resultSet = Statement.executeQuery("SELECT * FROM pos_main_user_role_tb "+quary);
        ArrayList<PosMainUserRole> posMainUserRoles = new ArrayList<>();

        while (resultSet.next()) {
            PosMainUserRole posMainUserRole = new PosMainUserRole(resultSet.getInt(1),
                    resultSet.getString("role"), resultSet.getInt("status"),
                    resultSet.getInt("user_id"),resultSet.getInt("visible"));
            posMainUserRoles.add(posMainUserRole);
        }
        return posMainUserRoles;
    }

    @Override
    public PosMainUserRole findOne(Integer id) throws Exception {
        ResultSet resultSet = Statement.executeQuery("SELECT * FROM pos_main_user_role_tb WHERE id = " + id);
        PosMainUserRole mainUserRole = new PosMainUserRole();
        if (resultSet.next()) {
            mainUserRole = new PosMainUserRole(resultSet.getInt("role_id"),
                    resultSet.getString("role"), resultSet.getInt("status"), 
                    resultSet.getInt("user_id"),resultSet.getInt("visible"));
        }
        return mainUserRole;
    }

}
