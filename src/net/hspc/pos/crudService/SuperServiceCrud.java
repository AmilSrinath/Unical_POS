/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.hspc.pos.crudService;

import java.util.ArrayList;
import net.hspc.pos.model.SuperModel;

/**
 *
 * @author Sanjuka
 * @param <D>
 * @param <ID>
 */
public interface SuperServiceCrud <D extends SuperModel,ID>extends CrudService{
    
    public boolean Save(D entity)throws Exception;
    
    public boolean Update(D entity)throws Exception;
    
    public boolean Delete(ID key)throws Exception;
    
    public D Search(ID key)throws Exception;
    
    public ArrayList<D> getAll()throws Exception;
    
}
