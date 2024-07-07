/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.dto;

import java.util.ArrayList;

/**
 *
 * @author Sanjuka
 */
public class MainOrderDto {
    
    private OrderDto orderDto;
    private ArrayList<OrderDetailsDto> orderDetailsDtos;
    
    public MainOrderDto() {
    }

    public MainOrderDto(OrderDto orderDto, ArrayList<OrderDetailsDto> orderDetailsDtos) {
        this.orderDto = orderDto;
        this.orderDetailsDtos = orderDetailsDtos;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }

    public ArrayList<OrderDetailsDto> getOrderDetailsDtos() {
        return orderDetailsDtos;
    }

    public void setOrderDetailsDtos(ArrayList<OrderDetailsDto> orderDetailsDtos) {
        this.orderDetailsDtos = orderDetailsDtos;
    }
    
    
}
