package com.SpiritStore.Domain;

/**
 * Created by Dell on 2017/9/20.
 */
public class OrderItem {
    private int comid;
    private int num;
    private int orderId;

    public int getComid() {
        return comid;
    }

    public void setComid(int comid) {
        this.comid = comid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
