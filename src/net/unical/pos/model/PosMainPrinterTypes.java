/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unical.pos.model;

/**
 *
 * @author Sanjuka
 */
public class PosMainPrinterTypes {
    
    private Integer printerTypeId;
    private String name;
    private Integer status;
    private Integer userd;
    private Integer visible;

    public PosMainPrinterTypes() {
    }

    public PosMainPrinterTypes(Integer printerTypeId, String name, Integer status, Integer userd, Integer visible) {
        this.printerTypeId = printerTypeId;
        this.name = name;
        this.status = status;
        this.userd = userd;
        this.visible = visible;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getPrinterTypeId() {
        return printerTypeId;
    }

    public void setPrinterTypeId(Integer printerTypeId) {
        this.printerTypeId = printerTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserd() {
        return userd;
    }

    public void setUserd(Integer userd) {
        this.userd = userd;
    }
    
}
