/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.UserRoleDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.UserRoleServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class UserRoleController {
    private UserRoleServiceCustom userRoleServiceCustom;
    
    public UserRoleController() {
        userRoleServiceCustom=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.USER_ROLE);
    }
    
    
    public boolean saveUserRole(UserRoleDto userRoleDto) throws Exception{
        return userRoleServiceCustom.saveUserRole(userRoleDto);
    }
    
    public ArrayList<UserRoleDto> getAllUserRoles(String quary)throws Exception{
        return userRoleServiceCustom.getAllUserRole(quary);
    }
    
    public boolean updateUserRole(UserRoleDto userRoleDto)throws Exception{
//        return .updateEmployee(employeeManagementDto);
    return true;
    }
}
