/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import java.util.ArrayList;
import net.unical.pos.dto.PaymentTypeDto;

/**
 *
 * @author Sanjuka
 */
public interface PaymentTypesServiceCustom {

    public ArrayList<PaymentTypeDto> getPaymentTypes(String quary)throws Exception;

    public boolean savePaymentType(PaymentTypeDto paymentTypeDto)throws Exception;
    
}
