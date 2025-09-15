/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.StockLocationDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.StockLocation;
import net.unical.pos.repository.custom.StockLocationRepositoryCustom;
import net.unical.pos.service.custom.StockLoactionServiceCustom;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class StockLocationServiceImpl implements StockLoactionServiceCustom{
    private StockLocationRepositoryCustom repo = RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.STOCK_LOCATION);
    
    @Override
    public ArrayList<StockLocationDto> getStockLocations() {
        ArrayList<StockLocationDto> stockLocationDtos = new ArrayList<>();
        ArrayList<StockLocation> stockLocations = repo.getStockLocations();
        for (StockLocation stockLocation : stockLocations) {
            stockLocationDtos.add(new StockLocationDto(
                    stockLocation.getStockCode(),
                    stockLocation.getCodePrefix(),
                    stockLocation.getStockName(),
                    stockLocation.getContact(),
                    stockLocation.getAddress(),
                    stockLocation.getStatus(),
                    stockLocation.getUserId(),
                    stockLocation.getCreatedDate()
            ));
        }
    return stockLocationDtos;
    }   
}
