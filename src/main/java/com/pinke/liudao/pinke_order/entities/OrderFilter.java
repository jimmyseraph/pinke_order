package com.pinke.liudao.pinke_order.entities;

public class OrderFilter {
    private int orderId;
    private int isPaid;
    private String address;
    private double amountLow;
    private double amountHigh;
    private String receiver;
    private int status;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(int isPaid) {
        this.isPaid = isPaid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAmountLow() {
        return amountLow;
    }

    public void setAmountLow(double amountLow) {
        this.amountLow = amountLow;
    }

    public double getAmountHigh() {
        return amountHigh;
    }

    public void setAmountHigh(double amountHigh) {
        this.amountHigh = amountHigh;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
