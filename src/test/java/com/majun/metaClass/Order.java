package com.majun.metaClass;

import java.util.List;

/**
 * Created by majun on 2018/7/20.
 */
public class Order {

    private Long orderId;

    private String orderName;

    private Location location;

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
