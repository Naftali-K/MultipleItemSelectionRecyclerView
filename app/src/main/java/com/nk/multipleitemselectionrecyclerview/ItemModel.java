package com.nk.multipleitemselectionrecyclerview;

/**
 * @Author: Naftali
 * @Date: 08.12.2021 13:59
 */
public class ItemModel {
    private String itemName;
    private boolean checked;

    public ItemModel(String itemName, boolean checked) {
        this.itemName = itemName;
        this.checked = checked;
    }

    public ItemModel(ItemModel itemModel) {
        this.itemName = itemModel.getItemName();
        this.checked = itemModel.isChecked();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Boolean isChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
