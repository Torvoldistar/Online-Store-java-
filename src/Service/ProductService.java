package Service;

import Dao.H2ProductDao;
import Model.Product;
import Dao.ProductBase;

import java.util.List;

public class ProductService implements IProductService {
    private final ProductBase dao;

    public ProductService(H2ProductDao h2ProductDao, ProductBase input_dao) {
        this.dao = input_dao;
    }

    @Override
    public List<Product> getAll() {
        return dao.getAll();
    }

    @Override
    public Product getById(long input_id) {
        return dao.getById(input_id);
    }

    @Override
    public Product create(String input_name, int input_price, String input_comments) {
        return dao.create(input_name, input_price, input_comments);
    }

    @Override
    public void update(Product input_Product) {
        dao.update(input_Product);
    }

    @Override
    public boolean delete(long input_id) {
        return dao.delete(input_id);
    }
}
