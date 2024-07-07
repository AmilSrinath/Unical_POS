/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import net.unical.pos.dto.CustomerDto;

/**
 *
 * @author Sanjuka
 */
public interface CustomerServiceCustom {

    public boolean saveCustomer(CustomerDto customerDto)throws Exception;

    public List<CustomerDto> getCustomer(String quary);
    
    public List<CustomerDto> getCustomerDetails(String quary);
    
    public CustomerDto searchCustomer(String quary);
    
}
