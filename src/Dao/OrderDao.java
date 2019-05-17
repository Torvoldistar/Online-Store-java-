package Dao;

import Model.Order;
import Model.Product;

import java.util.List;

public interface OrderDao {
    List<Order> getAll();

    Order getById(long id);

    Order create(String customerName, String customerPhone, String customerOrder);

    void update(Order order);

    boolean delete(long id);
}

