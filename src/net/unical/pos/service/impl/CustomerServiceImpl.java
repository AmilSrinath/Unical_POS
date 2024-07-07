/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import net.unical.pos.dto.CustomerDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.CustomerModel;
import net.unical.pos.repository.custom.CustomerRepositoryCustom;
import net.unical.pos.service.custom.CustomerServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class CustomerServiceImpl implements CustomerServiceCustom{

    private CustomerRepositoryCustom customerRepositoryCustom;
    
    public CustomerServiceImpl() {
        customerRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.CUSTOMER);
    }
    
    @Override
    public boolean saveCustomer(CustomerDto customerDto) throws Exception {
        CustomerModel customerModel = new CustomerModel(
                customerDto.getCustomerId(), 
                customerDto.getCustomerName(), 
                customerDto.getNic(), 
                customerDto.getAddress(),
                customerDto.getPhoneOne(),
                customerDto.getCreatedDate(),
                customerDto.getIsLoyalty(),
                customerDto.getLoyaltyPoints(),
                customerDto.getStatus(),
                customerDto.getUserId(),
                customerDto.getVisible()
        );
        
        return customerRepositoryCustom.save(customerModel);
    }

    @Override
    public Vector<CustomerDto> getCustomer(String quary) {
        List<CustomerModel>customerModels=customerRepositoryCustom.getCustomer(quary);
        Vector<CustomerDto>customerDtos=new Vector<>();
        for(CustomerModel customerModel:customerModels){
            customerDtos.add(new CustomerDto(customerModel.getCustomerId(),
                    customerModel.getCustomerName(),
                    customerModel.getNic(),
                    customerModel.getAddress(),
                    customerModel.getPhoneOne(),
                    customerModel.getPhoneTwo(),
                    customerModel.getCreatedDate(),
                    customerModel.getIsLoyalty(),
                    customerModel.getLoyaltyPoints(),
                    customerModel.getStatus(),
                    customerModel.getUserId(),
                    customerModel.getVisible()
            ));
        }
        return customerDtos;
    }

    @Override
    public List<CustomerDto> getCustomerDetails(String quary) {
        List<CustomerModel>customerModels=customerRepositoryCustom.getCustomer(quary);
        Vector<CustomerDto>customerDtos=new Vector<>();
        for(CustomerModel customerModel:customerModels){
            customerDtos.add(new CustomerDto(customerModel.getCustomerId(),
                    customerModel.getCustomerName(),
                    customerModel.getNic(),
                    customerModel.getAddress(),
                    customerModel.getPhoneOne(),
                    customerModel.getCreatedDate(),
                    customerModel.getIsLoyalty(),
                    customerModel.getLoyaltyPoints(),
                    customerModel.getStatus(),
                    customerModel.getUserId(),
                    customerModel.getVisible()
            ));
        }
        return customerDtos;
    }

    @Override
    public CustomerDto searchCustomer(String quary) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
