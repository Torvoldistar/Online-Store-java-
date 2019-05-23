package Dao;

import Model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public enum OrderBase implements OrderDao{
    INSTANCE;

    private AtomicLong idGenerator = new AtomicLong();
    private Map<Long, Order> allOrders = new HashMap<>();

    @Override
    public Order getById(long id) {
        return allOrders.get(id);
    }

    @Override
    public Order create(String customerName, String customerPhone, String customerOrder) {
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
