package repositories;

import config.PostgresConnectionProvider;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public User save(User user) {
        // language=SQL
        String insert = "insert into users_table (login,password) values (?, ?)";

        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException();
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            }

            return user;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<User> findAll() {
        // language=SQL
        String findAll = "select * from users_table";
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findAll);
            ResultSet set = statement.executeQuery();

            List<User> list = new ArrayList<>();

            while (set.next()) {
                User user = User.builder()
                        .id(set.getLong("id"))
                        .login(set.getString("login"))
                        .password(set.getString("password"))
                        .role(set.getString("role"))
                        .balance(set.getObject("balance", Integer.class))
                        .build();

                list.add(user);
            }

            return list;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void update(User user) {
        // language=SQL
        String update = "update users_table set balance = ? where id = ?";

        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setInt(1, user.getBalance());
            statement.setLong(2, user.getId());

            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
