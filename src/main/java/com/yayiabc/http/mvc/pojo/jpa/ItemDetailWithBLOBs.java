package com.yayiabc.http.mvc.pojo.jpa;

public class ItemDetailWithBLOBs extends ItemDetail {
    private String itemDesc;

    private String itemUse;

    @Override
    public String getItemDesc() {
        return itemDesc;
    }

    @Override
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }

    @Override
    public String getItemUse() {
        return itemUse;
    }

    @Override
    public void setItemUse(String itemUse) {
        this.itemUse = itemUse == null ? null : itemUse.trim();
    }
}