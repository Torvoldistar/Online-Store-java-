package Service;

import Model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();

    Product getById(long id);

    Product create(String name, int price, String comments);

    void update(Product item);

    boolean delete(long id);
}
