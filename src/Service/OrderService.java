package Service;

import Dao.OrderBase;
import Model.Product;
import Model.Order;

import java.util.List;

public class OrderService implements IOrderService{
    private final OrderBase dao;

    public OrderService(OrderBase input_dao) {
        this.dao = input_dao;
    }

    @Override
    public List<Order> getAll() {
        return dao.getAll();
    }

    @Override
    public Order getById(long input_id) {
        return dao.getById(input_id);
    }

    @Override
    public Order create(String input_customerName, String input_customerPhone, List<Product> input_customerOrder) {
        return dao.create(input_customerName, input_customerPhone, input_customerOrder);
    }

    @Override
    public void update(Order input_Order) {
        dao.update(input_Order);
    }

    @Override
    public boolean delete(long input_id) {
        return dao.delete(input_id);
    }
}
