/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import java.util.List;
import net.unical.pos.dto.UserRoleDto;

/**
 *
 * @author HP
 */
public interface UserRoleServiceCustom {
    boolean saveUserRole(UserRoleDto userRoledto)throws Exception;
    
    boolean updateUserRole(UserRoleDto dto)throws Exception;
    
    boolean deleteUserRole(Integer id)throws Exception;
    
    UserRoleDto findOneUserRole(Integer id)throws Exception;
    
    ArrayList<UserRoleDto>getAllUserRole(String quary)throws Exception;
    
    public UserRoleDto searchUserRole(String key)throws Exception;
}
