package thymeleaf;

import Dao.H2ProductDao;
import Dao.OrderBase;
import Dao.OrderDao;
import Dao.ProductBase;
import Dao.ProductDao;
import Model.Product;
import Service.ProductService;
import exception.ServiceException;

import javax.sql.DataSource;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        DataSource dataSource = ConnectionManager.createDataSource();
        ProductService service = new ProductService(
                new H2ProductDao(dataSource),
                ProductBase.INSTANCE);

        try {
            service.create("Blue Tool", 10000,"-");
            service.create("Green Tool", 20000, "Nice Tool");

            for (Product product : service.getAll()) {
                System.out.println(product);
            }
        } catch (ServiceException e) {
            System.err.println(e);
        }
    }
}
