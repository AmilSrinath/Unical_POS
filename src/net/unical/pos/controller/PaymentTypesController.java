/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.PaymentTypeDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.PaymentTypesServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class PaymentTypesController {

    private PaymentTypesServiceCustom paymentTypesServiceCustom;
    
    public PaymentTypesController() {
        paymentTypesServiceCustom=ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.PAYMENT_TYPE);
    }

    
    public ArrayList<PaymentTypeDto> getPaymentTypes(String quary) throws Exception{
        return paymentTypesServiceCustom.getPaymentTypes(quary);
    }

    public boolean savePaymentType(PaymentTypeDto paymentTypeDto) throws Exception{
        return paymentTypesServiceCustom.savePaymentType(paymentTypeDto);
    }
    
}
