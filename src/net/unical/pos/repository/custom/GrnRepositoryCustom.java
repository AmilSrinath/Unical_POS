/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.repository.custom;

import net.unical.pos.crudService.SuperServiceCrud;
import net.unical.pos.model.PosInvGrn;

/**
 *
 * @author Sanjuka
 */
public interface GrnRepositoryCustom extends SuperServiceCrud<PosInvGrn, String>{
    
    public Integer SaveGrn(PosInvGrn entity) throws Exception;
}
