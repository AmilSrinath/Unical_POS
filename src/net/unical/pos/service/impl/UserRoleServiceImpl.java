/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.unical.pos.dto.UserRoleDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.PosMainUserRole;
import net.unical.pos.repository.custom.UserRoleRepositoryCustom;
import net.unical.pos.service.custom.UserRoleServiceCustom;

/**
 *
 * @author HP
 */
public class UserRoleServiceImpl implements UserRoleServiceCustom {

    private UserRoleRepositoryCustom userRoleRepositoryCustom;

    public UserRoleServiceImpl() {
        userRoleRepositoryCustom = RepositoryFactory.getInstance().
                getRepository(RepositoryFactory.RepositoryType.MAIN_USER_ROLE);
    }

    @Override
    public boolean saveUserRole(UserRoleDto userRoledto) {

        try {
            PosMainUserRole posMainUserRole = new PosMainUserRole(0, userRoledto.getRoleName(), userRoledto.getStatus(), 
                    userRoledto.getUserId(),userRoledto.getVisible());
            return userRoleRepositoryCustom.save(posMainUserRole);
        } catch (Exception ex) {
            Logger.getLogger(UserRoleServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean updateUserRole(UserRoleDto userRoledto) throws Exception {

        PosMainUserRole posMainUserRole = new PosMainUserRole(userRoledto.getUserRoleId(), 
                userRoledto.getRoleName(), userRoledto.getStatus(),
                userRoledto.getUserId(),userRoledto.getVisible());
        return userRoleRepositoryCustom.update(posMainUserRole);

    }

    @Override
    public boolean deleteUserRole(Integer id) throws Exception {

        return userRoleRepositoryCustom.delete(id);

    }

    @Override
    public ArrayList<UserRoleDto> getAllUserRole(String quary) throws Exception {

        ArrayList<UserRoleDto> userRoleDtos = new ArrayList<>();
        ArrayList<PosMainUserRole> posMainUserRoles = new ArrayList<>();
        posMainUserRoles = userRoleRepositoryCustom.getAll(quary);

        for (PosMainUserRole role : posMainUserRoles) {
            userRoleDtos.add(new UserRoleDto(role.getRoleId(), role.getRole(), role.getStatus(), 
                    role.getUserId(),role.getVisible()));
        }
        return userRoleDtos;
    }

    @Override
    public UserRoleDto findOneUserRole(Integer id)throws Exception{

        PosMainUserRole posMainUserRole = userRoleRepositoryCustom.findOne(id);
        return new UserRoleDto(posMainUserRole.getRoleId(), posMainUserRole.getRole(),
                posMainUserRole.getStatus(),posMainUserRole.getUserId(), posMainUserRole.getVisible());

    }

    @Override
    public UserRoleDto searchUserRole(String key) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
