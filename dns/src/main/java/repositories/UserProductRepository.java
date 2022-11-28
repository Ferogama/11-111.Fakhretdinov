package repositories;

import config.PostgresConnectionProvider;
import models.UserProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserProductRepository {
    public UserProduct save(UserProduct userProduct) {
        // language=SQL
        String insert = "insert into user_product (user_id, product_id) values (?, ?)";

        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, userProduct.getUserId());
            statement.setLong(2, userProduct.getProductId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException();
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                userProduct.setId(generatedKeys.getLong("id"));
            }

            return userProduct;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<UserProduct> findAll() {
        // language=SQL
        String findAll = "select * from user_product";

        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findAll);
            ResultSet resultSet = statement.executeQuery();

            List<UserProduct> list = new ArrayList<>();

            while (resultSet.next()) {
                UserProduct userProduct = UserProduct.builder()
                        .id(resultSet.getLong("id"))
                        .userId(resultSet.getLong("user_id"))
                        .productId(resultSet.getLong("product_id"))
                        .isProcessed(resultSet.getBoolean("is_processed"))
                        .build();
                list.add(userProduct);
            }

            return list;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void delete(Long id) {

        // language=SQL
        String delete = "delete from user_product where id = ?";

        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void deleteByProductId(Long productId) {
        // language=SQL
        String delete = "delete from user_product where product_id = ?";
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setLong(1, productId);

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void update(UserProduct userProduct) {
        // language=SQL
        String update = "update user_product set is_processed = ? where id = ?";
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setBoolean(1, userProduct.isProcessed());
            statement.setLong(2, userProduct.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
