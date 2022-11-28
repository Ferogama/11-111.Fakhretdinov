package repositories;

import config.PostgresConnectionProvider;
import models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepository {
    public Product save(Product product) {
        // language=SQL
        String insert = "insert into products_table (name, description, price, img, img_name) values (?, ?, ?, ?, ?)";

        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getPrice());
            statement.setBytes(4, product.getImg());
            statement.setString(5, product.getImgName());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException();
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getLong("id"));
            }

            return product;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<Product> findAll() {
        // language=SQL
        String findAll = "select * from products_table";

        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findAll);
            ResultSet resultSet = statement.executeQuery();

            List<Product> list = new ArrayList<>();

            while (resultSet.next()) {
                Product product = Product.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .price(resultSet.getObject("price", Integer.class))
                        .img(resultSet.getBytes("img"))
                        .imgName(resultSet.getString("img_name"))
                        .build();

                list.add(product);
            }

            return list;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Optional<Product> getById(Long id) {
        // language=SQL
        String getById = "select * from products_table where id = ?";
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(getById);
            statement.setLong(1, id);
            ResultSet resultSet = statement .executeQuery();

            Product product;
            if (resultSet.next()) {
                product = Product.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .price(resultSet.getObject("price", Integer.class))
                        .img(resultSet.getBytes("img"))
                        .imgName(resultSet.getString("img_name"))
                        .build();

                return Optional.of(product);
            }

            return Optional.empty();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void delete(Long id) {
        // language=SQL
        String delete = "delete from products_table where id = ?";
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void update(Product product) {
        // language=SQL
        String update = "update products_table set price = ?, description = ?, name = ? where id = ?";
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setInt(1, product.getPrice());
            statement.setString(2, product.getDescription());
            statement.setString(3, product.getName());
            statement.setLong(4, product.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
