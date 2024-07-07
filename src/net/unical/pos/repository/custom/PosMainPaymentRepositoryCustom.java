/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import net.unical.pos.model.PosMainPaymentTypes;

/**
 *
 * @author HP
 */
public interface PosMainPaymentRepositoryCustom {

    public PosMainPaymentTypes getPaimentType(Integer paymentType);

    
    
}
