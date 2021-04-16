/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hspc.pos.factory;

import net.hspc.pos.repository.impl.MainItemCategoryRepositoryImpl;

/**
 *
 * @author Sanjuka
 */
public class RepositoryFactory {
    
    private static RepositoryFactory repositoryFactory;
    
    public enum RepositoryType{
        MAIN_ITEM_CATEGORY, SUB_ITEM_CATEGORY;
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
            case MAIN_ITEM_CATEGORY:
                return (T)new MainItemCategoryRepositoryImpl();
            default:
                return null;
        }
    }
}
