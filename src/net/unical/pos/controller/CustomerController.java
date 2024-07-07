/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import net.unical.pos.dto.CustomerDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.CustomerServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class CustomerController {

    private CustomerServiceCustom customerServiceCustom;
    
    public CustomerController() {
        customerServiceCustom=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.CUSTOMER);
    }

    public boolean saveCustomer(CustomerDto customerDto)throws Exception{
        return customerServiceCustom.saveCustomer(customerDto);
    }
    
    public List<CustomerDto> getCustomer(String quary) throws Exception{
        return customerServiceCustom.getCustomer(quary);
    }
    
    public CustomerDto searchCustomer(String quary) throws Exception{
        return customerServiceCustom.searchCustomer(quary);
    }

    public List<CustomerDto> getCustomerDetails(String string) {
         return customerServiceCustom.getCustomerDetails(string);
    }
    
}
