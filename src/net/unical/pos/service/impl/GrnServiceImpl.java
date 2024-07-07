/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.service.impl;

import java.util.ArrayList;
import net.unical.pos.dto.GoodReceivedNoteDto;
import net.unical.pos.factory.RepositoryFactory;
import net.unical.pos.model.PosInvGrn;
import net.unical.pos.repository.custom.GrnRepositoryCustom;
import net.unical.pos.service.custom.GrnServiceCustom;

/**
 *
 * @author Sanjuka
 */
public class GrnServiceImpl implements GrnServiceCustom{

    private GrnRepositoryCustom grnRepositoryCustom;

    public GrnServiceImpl() {
        grnRepositoryCustom=RepositoryFactory.getInstance().getRepository(RepositoryFactory.RepositoryType.GRN);
    }

    @Override
    public Integer saveGrn(GoodReceivedNoteDto grnDto) throws Exception {
        PosInvGrn posInvGrn=new PosInvGrn(grnDto.getGrnId(),
                grnDto.getInvoiceNo(),
                grnDto.getSupplierId(),
                grnDto.getTotalPrice(),
                grnDto.getTotalDiscount(),
                grnDto.getCreateDate(),
                grnDto.getStatus(),
                grnDto.getUserId(),
                1);
        Integer grnId=grnRepositoryCustom.SaveGrn(posInvGrn);
        
        return grnId;
    }

    @Override
    public ArrayList<GoodReceivedNoteDto> getGrnList() throws Exception {
        ArrayList<PosInvGrn> allPosInvGrns=grnRepositoryCustom.getAll();
        ArrayList<GoodReceivedNoteDto> noteDtos=new ArrayList<>();
        
        for(PosInvGrn invGrn: allPosInvGrns){
            noteDtos.add(new GoodReceivedNoteDto(invGrn.getGrnId(),
                    invGrn.getInvoiceNo(),
                    invGrn.getSupplierId(),
                    invGrn.getTotalPrice(),
                    invGrn.getTotalDiscount(),
                    invGrn.getCreatedDate(),
                    invGrn.getStatus(),
                    invGrn.getUserId(),
                    invGrn.getVisible()
            ));
        }
        return noteDtos;
    }
    
}
