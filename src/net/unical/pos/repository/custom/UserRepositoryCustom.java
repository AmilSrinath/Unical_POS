/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.dto.UserDto;
import net.unical.pos.model.PosMainUser;

/**
 *
 * @author HP
 */
public interface UserRepositoryCustom {

    boolean saveUser(PosMainUser posMainUser)throws Exception;

    boolean updateUserRole(PosMainUser PosMainUser)throws Exception;

    boolean deleteUser(Integer id)throws Exception;

    PosMainUser findOneUser(Integer id)throws Exception;

    ArrayList<PosMainUser> getAllUser(String quary)throws Exception;
    
    boolean login(PosMainUser PosMainUser)throws Exception;

    public PosMainUser findUserName(String userName)throws Exception;

    public UserDto updateUser(UserDto userDto);

    public UserDto updateUserWithPassword(UserDto userDto);
    
    
}
