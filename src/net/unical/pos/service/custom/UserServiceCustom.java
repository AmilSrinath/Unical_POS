/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.UserDto;

/**
 *
 * @author HP
 */
public interface UserServiceCustom {

    UserDto saveUser(UserDto userDto)throws Exception;

    boolean updateUserRole(UserDto userDto)throws Exception;

    boolean deleteUser(Integer id)throws Exception;

    UserDto findOneUser(Integer id)throws Exception;

    ArrayList<UserDto> getAllUser(String quary)throws Exception;
    
    UserDto login(UserDto dto)throws Exception;
}
