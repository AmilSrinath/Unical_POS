/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.factory;

import net.unical.pos.service.impl.SupplierServiceImpl;
import net.unical.pos.service.impl.SubTableLocationServiceImpl;
import net.unical.pos.service.impl.ConfigTableServiceImpl;
import net.unical.pos.service.impl.CustomerServiceImpl;
import net.unical.pos.service.impl.DeliveryOrderServiceImpl;
import net.unical.pos.service.impl.EmployeeDesignationServiceImpl;
import net.unical.pos.service.impl.EmployeeManagementServiceImpl;
import net.unical.pos.service.impl.EmployeeTitleServiceImpl;
import net.unical.pos.service.impl.GrnServiceImpl;
import net.unical.pos.service.impl.MainItemCategoryServiceImpl;
import net.unical.pos.service.impl.MainItemServiceImpl;
import net.unical.pos.service.impl.MainOrderServiceImpl;
import net.unical.pos.service.impl.MainTableLocationServiceImpl;
import net.unical.pos.service.impl.PaymentTypesServiceImpl;
import net.unical.pos.service.impl.PrinterTypesServiceImpl;
import net.unical.pos.service.impl.PurchaseOrderDetailsServiceImpl;
import net.unical.pos.service.impl.PurchaseOrderServiceImpl;
import net.unical.pos.service.impl.StockServiceImpl;
import net.unical.pos.service.impl.StoreTemplateServiceImpl;
import net.unical.pos.service.impl.SubItemCategoryServiceImpl;
import net.unical.pos.service.impl.UnitTypesServiceImpl;
import net.unical.pos.service.impl.UserRoleServiceImpl;
import net.unical.pos.service.impl.UserServiceImpl;

/**
 *
 * @author Sanjuka
 */
public class ServiceFactory {
    
    public static ServiceFactory serviceFactory;
    
    public enum ServiceType{
        MAIN_ITEM_CATEGORY,
        SUB_ITEM_CATEGORY,
        MAIN_ITEM,
        USER_ACCOUNT,
        USER_ROLE,
        SUPPLIER,
        MAIN_ORDER,
        MAIN_TABLE_LOCATION,
        SUB_TABLE_LOCATION,
        CONFIG_TABLES,
        PURCHASE_ORDER,
        PURCHASE_ORDER_DETAILS,
        STOCK,
        GRN,
        STORE_TEMPLATE,
        EMPLOYEE_DESIGNATION,
        EMPLOYEE_TITLE,
        PAYMENT_TYPE,
        UNIT_TYPE,
        PRINTER_TYPE,
        EMPLOYEE_MANAGEMENT,
        CUSTOMER, DELIVERY_ORDER;
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
            case SUB_ITEM_CATEGORY:
                return (T)new SubItemCategoryServiceImpl();
            case MAIN_ITEM:
                return (T)new MainItemServiceImpl();
            case USER_ACCOUNT:
                return (T)new UserServiceImpl();
            case EMPLOYEE_MANAGEMENT:
                return (T)new EmployeeManagementServiceImpl();
            case SUPPLIER:
                return (T)new SupplierServiceImpl();
            case USER_ROLE:
                return (T)new UserRoleServiceImpl();
            case MAIN_TABLE_LOCATION:
                return (T)new MainTableLocationServiceImpl();
            case SUB_TABLE_LOCATION:
                return (T)new SubTableLocationServiceImpl();
            case CONFIG_TABLES:
                return (T)new ConfigTableServiceImpl();
            case PURCHASE_ORDER:
                return (T)new PurchaseOrderServiceImpl();
            case PURCHASE_ORDER_DETAILS:
                return (T)new PurchaseOrderDetailsServiceImpl();
            case STOCK:
                return (T)new StockServiceImpl();
            case STORE_TEMPLATE:
                return (T)new StoreTemplateServiceImpl();
            case GRN:
                return (T)new GrnServiceImpl();
            case EMPLOYEE_DESIGNATION:
                return (T)new EmployeeDesignationServiceImpl();
            case EMPLOYEE_TITLE:
                return (T)new EmployeeTitleServiceImpl();
            case PAYMENT_TYPE:
                return (T)new PaymentTypesServiceImpl();
            case UNIT_TYPE:
                return (T)new UnitTypesServiceImpl();
            case PRINTER_TYPE:
                return (T)new PrinterTypesServiceImpl();
            case CUSTOMER:
                return (T)new CustomerServiceImpl();
            case MAIN_ORDER:
                return (T)new MainOrderServiceImpl();
            case DELIVERY_ORDER:
                return (T)new DeliveryOrderServiceImpl();
            default:
                return null;
        }
    }
}
