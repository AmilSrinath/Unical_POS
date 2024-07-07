/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.custom;

import net.unical.pos.dto.MainOrderDto;
import net.unical.pos.repository.custom.MainOrderRepositoryCustom;

/**
 *
 * @author HP
 */
public interface MainOrderServiceCustom {

    public boolean saveMainOrder(MainOrderDto mainOrderDto)throws Exception;
  
}
