/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.controller;

import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.ItemRegistryServiceCustom;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class ItemRegistryController {
    private ItemRegistryServiceCustom itemRegistryServiceCustom = ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ITEM_REGISTRY);
    
    
}
