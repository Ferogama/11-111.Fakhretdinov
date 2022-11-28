package repositories;

import config.PostgresConnectionProvider;
import models.Comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CommentRepository {
    public List<Comment> finAll() {
        // language=SQL
        String findAll = "select * from comment";
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(findAll);
            ResultSet resultSet = statement.executeQuery();

            List<Comment> comments = new LinkedList<>();

            while (resultSet.next()) {
                Comment comment = Comment.builder()
                        .id(resultSet.getLong("id"))
                        .productId(resultSet.getLong("product_id"))
                        .userName(resultSet.getString("username"))
                        .text(resultSet.getString("text"))
                        .build();

                comments.add(comment);
            }

            return comments;

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void save(Comment comment) {
        // language=SQL
        String save = "insert into comment (product_id, username, text) values (?, ?, ?)";
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(save);

            statement.setLong(1, comment.getProductId());
            statement.setString(2, comment.getUserName());
            statement.setString(3, comment.getText());

            statement.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void delete(Long productId) {
        // language=SQL
        String delete = "delete from comment where product_id = ?";
        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setLong(1, productId);

            statement.execute();
        } catch(SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
