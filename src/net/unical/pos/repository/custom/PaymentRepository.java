/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.repository.custom;

import java.util.ArrayList;
import net.unical.pos.model.DeliveryOrder;

/**
 *
 * @author apple
 */
public interface PaymentRepository {
    public ArrayList<DeliveryOrder> getAllPaymentDuration(String fromDate, String toDate, Integer paymentType, int status);
}
