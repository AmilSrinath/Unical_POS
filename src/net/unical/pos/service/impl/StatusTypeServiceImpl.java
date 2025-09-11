/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.unical.pos.service.impl;

import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.repository.custom.StatusTypeRepositoryCustom;
import net.unical.pos.service.custom.StatusTypeServiceCustom;

/**
 *
 * @author Dhanujaya(Dhanu)
 */
public class StatusTypeServiceImpl implements StatusTypeServiceCustom{
    private StatusTypeRepositoryCustom statusRepo = RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.STATUS_TYPE);
    
    @Override
    public int getStatusIdByStatusType(String status) {
        return statusRepo.getStatusIdByStatus(status);
    }
    
}
