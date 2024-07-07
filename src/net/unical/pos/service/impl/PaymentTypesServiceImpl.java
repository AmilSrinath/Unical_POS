/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.PaymentTypeDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.PosMainPaymentTypes;
import net.unical.pos.repository.custom.PaymentTypesRepositoryCustom;
import net.unical.pos.service.custom.PaymentTypesServiceCustom;

/**
 *
 * @author Sanjuka
 */

public class PaymentTypesServiceImpl implements PaymentTypesServiceCustom{

    private PaymentTypesRepositoryCustom paymentTypesRepositoryCustom;
    
    public PaymentTypesServiceImpl() {
        paymentTypesRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.PAYMENT_TYPE);
    }
    
    @Override
    public ArrayList<PaymentTypeDto> getPaymentTypes(String quary) throws Exception {
        ArrayList<PosMainPaymentTypes> mainPaymentTypeses=paymentTypesRepositoryCustom.getAll(quary);
        ArrayList<PaymentTypeDto> paymentTypeDtos=new ArrayList<>();
        for(PosMainPaymentTypes paymentTypes: mainPaymentTypeses){
            paymentTypeDtos.add(new PaymentTypeDto(
                    paymentTypes.getPaymentTypeId(),
                    paymentTypes.getName(),
                    paymentTypes.getStatus(),
                    paymentTypes.getUserId(),
                    paymentTypes.getVisible()));
        }
        return paymentTypeDtos;
    }

    @Override
    public boolean savePaymentType(PaymentTypeDto paymentTypeDto) throws Exception {
        PosMainPaymentTypes mainPaymentTypes = new PosMainPaymentTypes(
                paymentTypeDto.getPaymentTypeId(), 
                paymentTypeDto.getName(), 
                paymentTypeDto.getStatus(), 
                paymentTypeDto.getUserd(),
                paymentTypeDto.getVisible());
        
        return paymentTypesRepositoryCustom.save(mainPaymentTypes);
    }
    
}
