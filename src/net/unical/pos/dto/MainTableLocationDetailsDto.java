/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.dto;

/**
 *
 * @author Sanjuka
 */
public class MainTableLocationDetailsDto {
    
    Integer minTableLocationDetailsId;
    String name;

    public MainTableLocationDetailsDto(Integer minTableLocationDetailsId, String name) {
        this.minTableLocationDetailsId = minTableLocationDetailsId;
        this.name = name;
    }

    public Integer getMinTableLocationDetailsId() {
        return minTableLocationDetailsId;
    }

    public void setMinTableLocationDetailsId(Integer minTableLocationDetailsId) {
        this.minTableLocationDetailsId = minTableLocationDetailsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
