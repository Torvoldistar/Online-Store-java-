package Service;

import Dao.H2OrderDao;
import Dao.OrderBase;
import Model.Order;

import java.util.List;

public class OrderService implements IOrderService{
    private final OrderBase dao;
    private final H2OrderDao h2dao;
    public OrderService(OrderBase input_dao, H2OrderDao h2orderdao) {
        this.dao = input_dao;
        this.h2dao=h2orderdao;
    }

    @Override
    public List<Order> getAll() {
        return h2dao.getAll();
    }

    @Override
    public Order getById(long input_id) {
        return h2dao.getById(input_id);
    }

    @Override
    public Order create(String input_customerName, String input_customerPhone, String input_customerOrder) {
        return h2dao.create(input_customerName, input_customerPhone, input_customerOrder);
    }

    @Override
    public void update(Order input_Order) {
        h2dao.update(input_Order);
    }

    @Override
    public boolean delete(long input_id) {
        return h2dao.delete(input_id);
    }
}
