package Dao;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


import Model.Order;
import Model.Product;

public enum OrderBase implements OrderDao{
    INSTANCE;

    private AtomicLong idGenerator = new AtomicLong();
    private Map<Long, Order> allOrders = new HashMap<>();

    @Override
    public Order getById(long id) {
        return allOrders.get(id);
    }

    @Override
    public Order create(String customerName, String customerPhone, List<Product> customerOrder) {
        Order order = new Order (idGenerator.incrementAndGet(), customerName, customerPhone, customerOrder);
        allOrders.put(order.getId(), order);
        return order;
    }

    @Override
    public void update(Order order) {
        allOrders.put(order.getId(), order);
    }

    @Override
    public boolean delete(long id) {
        Order removedOrder= allOrders.remove(id);
        return removedOrder != null;
    }

    @Override
    public List<Order> getAll() {
        return new ArrayList<>(allOrders.values());
    }

}
