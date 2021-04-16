/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hspc.pos.factory;

import net.hspc.pos.service.impl.MainItemCategoryServiceImpl;

/**
 *
 * @author Sanjuka
 */
public class ServiceFactory {
    
    public static ServiceFactory serviceFactory;
    
    public enum ServiceType{
        MAIN_ITEM_CATEGORY;
    }

    public ServiceFactory() {
    }
    
    public static ServiceFactory getInstance(){
        if(serviceFactory==null){
            serviceFactory= new ServiceFactory();
        }
        return serviceFactory;
    }
    
    public <T>T getService(ServiceType serviceType){
        switch(serviceType){
            case MAIN_ITEM_CATEGORY:
                return (T)new MainItemCategoryServiceImpl();
            
            default:
                return null;
        }
    }
}
