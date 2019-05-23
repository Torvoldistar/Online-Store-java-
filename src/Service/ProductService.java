package Service;

import Dao.H2ProductDao;
import Model.Product;
import Dao.ProductBase;

import java.util.List;

public class ProductService implements IProductService {
    private final ProductBase dao;
    private final H2ProductDao h2dao;

    public ProductService(H2ProductDao h2ProductDao, ProductBase input_dao) {
        this.dao = input_dao;
        this.h2dao = h2ProductDao;
    }

    @Override
    public List<Product> getAll() {
        return h2dao.getAll();
    }

    @Override
    public Product getById(long input_id) {
        return h2dao.getById(input_id);
    }

    @Override
    public Product create(String input_name, int input_price, String input_comments) {
        return h2dao.create(input_name, input_price, input_comments);
    }

    @Override
    public void update(Product input_Product) {
        h2dao.update(input_Product);
    }

    @Override
    public boolean delete(long input_id) {
        return h2dao.delete(input_id);
    }
}
