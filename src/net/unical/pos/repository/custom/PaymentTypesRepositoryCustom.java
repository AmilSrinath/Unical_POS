/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.model.PosMainPaymentTypes;

/**
 *
 * @author Sanjuka
 */
public interface PaymentTypesRepositoryCustom {

    public ArrayList<PosMainPaymentTypes> getAll(String quary)throws Exception;

    public boolean save(PosMainPaymentTypes mainPaymentTypes)throws Exception;
    
}
