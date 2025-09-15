/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.controller;

import java.util.ArrayList;
import net.unical.pos.dto.StockLocationDto;
import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.StockLoactionServiceCustom;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class StockLocationController {
    private StockLoactionServiceCustom serviceCustom = ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.STOCK_LOCATION);
    
    public ArrayList<StockLocationDto> getStockLocations() {
        return serviceCustom.getStockLocations();
    }
    
}
