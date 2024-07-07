/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.model.DeliveryOrder;

/**
 *
 * @author Sanjuka
 */
public interface DeliveryOrderRepositoryCustom {
    public Integer save(DeliveryOrder deliveryOrder)throws Exception;

    public ArrayList<DeliveryOrder> getAll(String date,Integer paymentType)throws Exception;

    public ArrayList<DeliveryOrder> getAllDuration(String fromDate, String toDate, Integer paymentType);
}
