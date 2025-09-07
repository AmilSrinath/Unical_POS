/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.DeliveryOrderDto;
import net.unical.pos.dto.DiscountDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.DeliveryOrder;
import net.unical.pos.repository.custom.DeliveryOrderRepositoryCustom;
import net.unical.pos.service.custom.DeliveryOrderServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class DeliveryOrderServiceImpl implements DeliveryOrderServiceCustom{
    
    private DeliveryOrderRepositoryCustom deliveryOrderRepositoryCustom;

    public DeliveryOrderServiceImpl() {
        deliveryOrderRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.DELIVERY_ORDER);
    }
    
    
    @Override
    public Integer saveDeliveryOrder(DeliveryOrderDto deliveryOrderDto) throws Exception {
//        DeliveryOrder deliveryOrder=new DeliveryOrder(
//                deliveryOrderDto.getOrderId(),
//                deliveryOrderDto.getOrderCode(),
//                deliveryOrderDto.getCustomerId(),
//                deliveryOrderDto.getCustomerName(),
//                deliveryOrderDto.getAddress(),
//                deliveryOrderDto.getCod(),
//                deliveryOrderDto.getPhoneOne(),
//                deliveryOrderDto.getPhoneTwo(),
//                deliveryOrderDto.getSubTotalPrice(),
//                deliveryOrderDto.getDeliveryFee(),
//                deliveryOrderDto.getWeight(),
//                deliveryOrderDto.getStatus(),
//                deliveryOrderDto.getFreeShip(),
//                deliveryOrderDto.getGrandTotalPrice(),
//                deliveryOrderDto.getRemark(),
//                deliveryOrderDto.getPaymentTypeId(),
//                deliveryOrderDto.getOrderDetailsDtos()
//        );
//        return deliveryOrderRepositoryCustom.save(deliveryOrder);
        return null;
    }

    @Override
    public ArrayList<DeliveryOrderDto> getAllOrders(String date,Integer paymentType) throws Exception {
        ArrayList<DeliveryOrder>deliveryOrders=deliveryOrderRepositoryCustom.getAll(date,paymentType);
        ArrayList<DeliveryOrderDto>deliveryOrderDtos=new ArrayList<>();
        for(DeliveryOrder deliveryOrder:deliveryOrders){
            deliveryOrderDtos.add(new DeliveryOrderDto(deliveryOrder.getOrderId(),
                    deliveryOrder.getOrderCode(),
                    deliveryOrder.getCustomerName(),
                    deliveryOrder.getAddress(),
                    deliveryOrder.getCod(),
                    deliveryOrder.getPhoneOne(),
                    deliveryOrder.getPhoneTwo(),
                    deliveryOrder.getSubTotalPrice(),
                    deliveryOrder.getDeliveryFee(),
                    deliveryOrder.getStatus(),
                    deliveryOrder.getGrandTotalPrice(),
                    deliveryOrder.getRemark(),
                    deliveryOrder.getPaymentTypeId(),
                    deliveryOrder.getIsPrint()
            ));
        }
        return deliveryOrderDtos;
    }

    @Override
    public ArrayList<DeliveryOrderDto> getAllDurationOrders(String fromDate, String toDate, Integer paymentType) {
        ArrayList<DeliveryOrder>deliveryOrders=deliveryOrderRepositoryCustom.getAllDuration(fromDate,toDate,paymentType,0, "Any");
        ArrayList<DeliveryOrderDto>deliveryOrderDtos=new ArrayList<>();
        for(DeliveryOrder deliveryOrder:deliveryOrders){
            deliveryOrderDtos.add(new DeliveryOrderDto(deliveryOrder.getOrderId(),
                    deliveryOrder.getOrderCode(),
                    deliveryOrder.getCustomerName(),
                    deliveryOrder.getAddress(),
                    deliveryOrder.getCod(),
                    deliveryOrder.getPhoneOne(),
                    deliveryOrder.getPhoneTwo(),
                    deliveryOrder.getSubTotalPrice(),
                    deliveryOrder.getDeliveryFee(),
                    deliveryOrder.getStatus(),
                    deliveryOrder.getGrandTotalPrice(),
                    deliveryOrder.getRemark(),
                    deliveryOrder.getPaymentTypeId(),
                    deliveryOrder.getIsPrint()
            ));
        }
        return deliveryOrderDtos;
    }

    @Override
    public Double getSpecificWaight(Integer id) {
        return deliveryOrderRepositoryCustom.getSpecificWaight(id);
    }

    @Override
    public String getOrderType(String deliveryID) {
        return deliveryOrderRepositoryCustom.getOrderType(deliveryID);
    }

    @Override
    public String getOrderId(String order_ID) {
        return deliveryOrderRepositoryCustom.getOrderId(order_ID);
    }

    
}
