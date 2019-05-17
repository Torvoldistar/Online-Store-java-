package Dao;

import Model.Product;
import exception.ProductServiceException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2ProductDao implements ProductDao {
    private final DataSource dataSource;

    public H2ProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Product> getAll() {
        try (
                Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select * from PRODUCTS")
        ) {
            if (rs == null)
                throw new SQLException("Unable to load products");

            List<Product> products = new ArrayList<>();
            while (rs.next())
                products.add(retrieveProduct(rs));
            return products;
        } catch (SQLException e) {
            throw new ProductServiceException(e);
        }
    }

    private Product retrieveProduct(ResultSet rs) throws SQLException {
        return new Product(
                rs.getLong(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getString(4)
        );
    }

    @Override
    public Product getById(long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement("select id, name, price, comments from PRODUCTS where id = ?")
        ) {
            statement.setLong(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                rs.next();
                return retrieveProduct(rs);
            }
        } catch (SQLException e) {
            throw new ProductServiceException(e);
        }
    }

    @Override
    public Product create(String name, int price, String comments) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "insert into PRODUCTS (name, price, comments) values (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setString(1, name);
            statement.setInt(2, price);
            statement.setString(3, comments);
            int createdRows = statement.executeUpdate();
            if (createdRows != 1)
                throw new SQLException("Unable to create product");

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);
                    return new Product(id, name, price, comments);
                } else {
                    throw new SQLException("Creating product failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new ProductServiceException(e);
        }
    }

    @Override
    public void update(Product product) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE Products " +
                        "SET name = ?, price = ?, comments = ?" +
                        "WHERE id = ?");
        ) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setString(3, product.getComments());
            statement.setLong(4, product.getId());

            int updateRows = statement.executeUpdate();
            if (updateRows != 1)
                throw new ProductServiceException("Unable to update product");
        } catch (SQLException e) {
            throw new ProductServiceException(e);
        }
    }

    @Override
    public boolean delete(long id) {
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement("DELETE from PRODUCTS " +
                        "WHERE id = ?");
        ) {
            statement.setLong(1, id);
            int deletedRows = statement.executeUpdate();
            if (deletedRows != 1)
                throw new ProductServiceException("Unable to delete product");
            return true;
        } catch (SQLException e) {
            throw new ProductServiceException(e);
        }
    }
}
