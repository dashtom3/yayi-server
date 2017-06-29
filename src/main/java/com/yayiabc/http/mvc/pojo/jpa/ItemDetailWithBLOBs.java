package com.yayiabc.http.mvc.pojo.jpa;

public class ItemDetailWithBLOBs extends ItemDetail {
    private String itemDesc;

    private String itemUse;

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }

    public String getItemUse() {
        return itemUse;
    }

    public void setItemUse(String itemUse) {
        this.itemUse = itemUse == null ? null : itemUse.trim();
    }
}