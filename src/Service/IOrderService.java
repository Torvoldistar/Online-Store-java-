package Service;

import Model.Product;
import Model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getAll();

    Order getById(long id);

    Order create(String customerName, String customerPhone, List<Product> customerOrder);

    void update(Order order);

    boolean delete(long id);
}
