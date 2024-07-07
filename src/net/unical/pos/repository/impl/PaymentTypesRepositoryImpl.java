/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import net.unical.pos.dbConnection.Statement;
import net.unical.pos.model.PosMainPaymentTypes;
import net.unical.pos.repository.custom.PaymentTypesRepositoryCustom;

/**
 *
 * @author Sanjuka
 */
public class PaymentTypesRepositoryImpl implements PaymentTypesRepositoryCustom{

    @Override
    public ArrayList<PosMainPaymentTypes> getAll(String quary) throws Exception {
        ResultSet rst=Statement.executeQuery("Select * from pos_main_payment_types_tb "+quary);
        
        ArrayList<PosMainPaymentTypes> paymentTypeses=new ArrayList<>();
        while(rst.next()){
            paymentTypeses.add(new PosMainPaymentTypes(rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getInt(4),
                    rst.getInt(5)
            ));
        }
        return paymentTypeses;
    }

    @Override
    public boolean save(PosMainPaymentTypes mainPaymentTypes) throws Exception {
        return Statement.executeUpdate("Insert into pos_main_payment_types_tb values(?,?,?,?,?)",
                mainPaymentTypes.getPaymentTypeId(),
                mainPaymentTypes.getName(),
                mainPaymentTypes.getUserId(),
                mainPaymentTypes.getStatus(),
                mainPaymentTypes.getVisible()) > 0;
    }
    
}
