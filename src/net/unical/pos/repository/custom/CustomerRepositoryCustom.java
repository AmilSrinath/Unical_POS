/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import java.util.List;
import net.unical.pos.model.CustomerModel;

/**
 *
 * @author Sanjuka
 */
public interface CustomerRepositoryCustom {

    public boolean save(CustomerModel customerModel)throws Exception;

    public List<CustomerModel> getCustomer(String quary);
    
}
