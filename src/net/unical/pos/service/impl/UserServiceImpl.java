/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.unical.pos.dto.UserDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.PosMainUser;
import net.unical.pos.repository.custom.UserRepositoryCustom;
import net.unical.pos.service.custom.UserServiceCustom;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author HP
 */
public class UserServiceImpl implements UserServiceCustom {

    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    private UserRepositoryCustom userRepositoryCustom;

   
    public UserServiceImpl() {
        userRepositoryCustom = RepositoryFactory.getInstance().
                getRepository(RepositoryFactory.RepositoryType.MAIN_USER);
    }

    @Override
    public UserDto saveUser(UserDto userDto) throws Exception {
        String encode = "";
        if (userDto.getPassword() != null) {
            encode = passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(encode);
        }
        String token = token();
        userDto.setToken(token);

        PosMainUser posMainUser = new PosMainUser(0, userDto.getEmployeeId(),userDto.getRoleId(), userDto.getUserName(), encode, userDto.getStatus(), 
                userDto.getVisible(), token);
        boolean b = userRepositoryCustom.saveUser(posMainUser);
        if (b == true) {
            return userDto;
        }
        return null;
    }

    @Override
    public boolean updateUserRole(UserDto userDto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteUser(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDto findOneUser(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<UserDto> getAllUser(String quary) throws Exception {
        ArrayList<PosMainUser> posMainUsers=userRepositoryCustom.getAllUser(quary);
        ArrayList<UserDto> mainUserDtos=new ArrayList<>();
        for(PosMainUser model: posMainUsers ){
            mainUserDtos.add(new UserDto(model.getUserId(), model.getEmployeeId(),
                    model.getRoleId(),model.getUserName(),  
                    model.getPassword(), model.getStatus(), model.getVisible(), 
                    model.getToken()));
        }

        return mainUserDtos;
    }

    @Override
    public UserDto login(UserDto userDto) throws Exception {
        PosMainUser posMainUser = userRepositoryCustom.findUserName(userDto.getUserName());
        if (posMainUser.getUserName() != null) {
            if (passwordEncoder.matches(userDto.getPassword(), posMainUser.getPassword()) == true) {
                return new UserDto(posMainUser.getUserId(),posMainUser.getEmployeeId(), posMainUser.getRoleId(), posMainUser.getUserName(),
                        posMainUser.getPassword(), posMainUser.getStatus(),posMainUser.getVisible(), posMainUser.getToken());
            }
        }
        return null;
    }

    public String token() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        int length = 10;

        for (int i = 0; i < length; i++) {

            int index = random.nextInt(alphabet.length());

            char randomChar = alphabet.charAt(index);

            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;

    }

    @Override
    public UserDto updateUser(UserDto userDto) throws Exception {
        return userRepositoryCustom.updateUser(userDto);
    }

    @Override
    public UserDto updateUserWithPassword(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepositoryCustom.updateUserWithPassword(userDto);
    }
}
