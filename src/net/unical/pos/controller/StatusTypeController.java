/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.controller;

import net.unical.pos.factory.ServiceFactory;
import net.unical.pos.service.custom.StatusTypeServiceCustom;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class StatusTypeController {
    private StatusTypeServiceCustom statusTypeServiceCustom = ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.STATUS_TYPE);
    public int getStatusIdByStatusType(String status) {
        return statusTypeServiceCustom.getStatusIdByStatusType(status);
    }
    
    
}
