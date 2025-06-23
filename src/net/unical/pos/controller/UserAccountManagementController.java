/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.UserDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.model.PosMainUser;
import net.unical.pos.service.custom.UserServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class UserAccountManagementController {
    
    private UserServiceCustom userServiceCustom;
    
    public UserAccountManagementController() {
        userServiceCustom=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.USER_ACCOUNT);
    }
    
    public UserDto saveUser(UserDto userDto) throws Exception{
        return userServiceCustom.saveUser(userDto);
    }
    
    public ArrayList<UserDto> getAllUsers(String quary)throws Exception{
        return userServiceCustom.getAllUser(quary);
    }
    
    public UserDto updateUser(UserDto userDto)throws Exception{
        return userServiceCustom.updateUser(userDto);
    }
    
    public UserDto updateUserWithPassword(UserDto userDto)throws Exception{
        return userServiceCustom.updateUserWithPassword(userDto);
    }

    public PosMainUser getUserByUserID(int userID) throws Exception {
        return userServiceCustom.findOneUser(userID);
    }
    
}
