/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.factory;

import net.unical.pos.repository.impl.ConfigTableDetailsRepositoryImpl;
import net.unical.pos.repository.impl.ConfigTableRepositoryImpl;
import net.unical.pos.repository.impl.CustomerRepositoryImpl;
import net.unical.pos.repository.impl.DeliveryOrderRepositoryImpl;
import net.unical.pos.repository.impl.DiscountRepositoryImpl;
import net.unical.pos.repository.impl.EmployeeDesignationRepositoryImpl;
import net.unical.pos.repository.impl.EmployeeManagementRepositoryImpl;
import net.unical.pos.repository.impl.EmployeeTitleRepositoryImpl;
import net.unical.pos.repository.impl.GrnRepositoryImpl;
import net.unical.pos.repository.impl.ItemRegistryRepositoryImpl;
import net.unical.pos.repository.impl.MainItemCategoryRepositoryImpl;
import net.unical.pos.repository.impl.MainItemRepositoryImpl;
import net.unical.pos.repository.impl.MainOrderRepositoryImpl;
import net.unical.pos.repository.impl.MainTableLocationRepositoryImpl;
import net.unical.pos.repository.impl.OrderRepo;
import net.unical.pos.repository.impl.OrderTypeRepositoryImpl;
import net.unical.pos.repository.impl.PaymentTypesRepositoryImpl;
import net.unical.pos.repository.impl.PrinterTypesRepositoryImpl;
import net.unical.pos.repository.impl.PurchaseOrderDetailsRepositoryImpl;
import net.unical.pos.repository.impl.PurchaseOrderRepositoryImpl;
import net.unical.pos.repository.impl.StatusTypeRepositoryImpl;
import net.unical.pos.repository.impl.StockRepositoryImpl;
import net.unical.pos.repository.impl.StoreTemplateRepositoryImpl;
import net.unical.pos.repository.impl.SubItemCategoryRepositoryImpl;
import net.unical.pos.repository.impl.SubTableLocationRepositoryImpl;
import net.unical.pos.repository.impl.SupplierRepositoryImpl;
import net.unical.pos.repository.impl.UnitTypesRepositoryImpl;
import net.unical.pos.repository.impl.UserRepositoryimpl;
import net.unical.pos.repository.impl.UserRoleRepositoryImpl;

/**
 *
 * @author Sanjuka
 */
public class RepositoryFactory {
    
    private static RepositoryFactory repositoryFactory;
    
    public enum RepositoryType{
        MAIN_ITEM_CATEGORY, 
        SUB_ITEM_CATEGORY,
        MAIN_ITEM,
        MAIN_USER_ROLE,MAIN_USER,
        MAIN_TABLE_LOCATION,
        SUB_TABLE_LOCATION,
        CONFIG_TABLE,
        CONFIG_TABLE_DETAILS,
        SUPPLIER,
        PURCHASE_ORDER,
        PURCHASE_ORDER_DETAILS,
        STOCK,
        GRN,
        STORE_TEMPLATE,
        EMPLOYEE_DESIGNATION,
        EMPLOYEE_TITLE,
        PAYMENT_TYPE,
        UNIT_TYPES,
        PRINTER_TYPE,
        EMPLOYEE_MANAGEMENT,
        MAIN_ORDER,
        ORDER,
        CUSTOMER,
        DELIVERY_ORDER, 
        DISCOUNT, 
        ORDER_TYPE, 
        STATUS_TYPE,
        ITEM_REGISTRY
        ;
    }

    public RepositoryFactory() {
    }
    
    public static RepositoryFactory getInstance(){
        if(repositoryFactory==null){
            repositoryFactory= new RepositoryFactory();
        }
        return repositoryFactory;
    }
    
    public <T>T getRepository(RepositoryType repositoryType){
        switch(repositoryType){
            case MAIN_USER_ROLE:
                return (T) new UserRoleRepositoryImpl();
            case MAIN_USER:
                return (T) new UserRepositoryimpl();
            case MAIN_ITEM_CATEGORY:
                return (T)new MainItemCategoryRepositoryImpl();
            case SUB_ITEM_CATEGORY:
                return (T)new SubItemCategoryRepositoryImpl();
            case MAIN_ITEM:
                return (T)new MainItemRepositoryImpl();
            case EMPLOYEE_MANAGEMENT:
                return (T)new EmployeeManagementRepositoryImpl();
            case MAIN_TABLE_LOCATION:
                return (T)new MainTableLocationRepositoryImpl();
            case SUB_TABLE_LOCATION:
                return (T)new SubTableLocationRepositoryImpl();
            case CONFIG_TABLE:
                return (T)new ConfigTableRepositoryImpl();    
            case CONFIG_TABLE_DETAILS:
                return (T)new ConfigTableDetailsRepositoryImpl();    
            case SUPPLIER:
                return (T)new SupplierRepositoryImpl();
            case PURCHASE_ORDER:
                return (T)new PurchaseOrderRepositoryImpl();
            case PURCHASE_ORDER_DETAILS:
                return (T)new PurchaseOrderDetailsRepositoryImpl();    
            case STOCK:
                return (T)new StockRepositoryImpl();
            case STORE_TEMPLATE:
                return (T)new StoreTemplateRepositoryImpl();
            case GRN:
                return (T)new GrnRepositoryImpl();
            case EMPLOYEE_DESIGNATION:
                return (T)new EmployeeDesignationRepositoryImpl();
            case EMPLOYEE_TITLE:
                return (T)new EmployeeTitleRepositoryImpl();
            case PAYMENT_TYPE:
                return (T)new PaymentTypesRepositoryImpl();
            case UNIT_TYPES:
                return (T)new UnitTypesRepositoryImpl();
            case PRINTER_TYPE:
                return (T)new PrinterTypesRepositoryImpl();
            case CUSTOMER:
                return (T)new CustomerRepositoryImpl();
            case MAIN_ORDER:
                return (T)new MainOrderRepositoryImpl();
            case ORDER:
                return (T)new OrderRepo();
            case DELIVERY_ORDER:
                return (T)new DeliveryOrderRepositoryImpl();
            case DISCOUNT:
                return (T)new DiscountRepositoryImpl();
            case ORDER_TYPE:
                return (T)new OrderTypeRepositoryImpl();
            case STATUS_TYPE:
                return (T)new StatusTypeRepositoryImpl();
            case ITEM_REGISTRY:
                return (T)new ItemRegistryRepositoryImpl();
            default:
                return null;
        }
    }
}
