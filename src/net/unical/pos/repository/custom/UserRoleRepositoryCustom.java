/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.model.PosMainUserRole;

/**
 *
 * @author HP
 */
public interface UserRoleRepositoryCustom {

    public boolean save(PosMainUserRole posMainUserRole) throws Exception;

    public boolean update(PosMainUserRole posMainUserRole)throws Exception;

    public boolean delete(Integer id)throws Exception;

    public ArrayList<PosMainUserRole> getAll(String quary)throws Exception;

    public PosMainUserRole findOne(Integer id)throws Exception;


    
}
