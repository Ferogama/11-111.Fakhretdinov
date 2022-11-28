package listeners;

import config.PostgresConnectionProvider;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class InitListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    public static List<Long> cart;
    public static HttpSession session;

    public InitListener() {}

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        cart = new ArrayList<>();

        // language=SQL
        String createUserTable = "create table if not exists users_table (" +
                "id bigserial primary key ," +
                "login varchar(20)," +
                "role varchar(20) default 'user'," +
                "balance integer default 0," +
                "password varchar(100))";

        // language=SQL
        String createProductTable = "create table if not exists products_table (" +
                "id bigserial primary key ," +
                "name varchar(200)," +
                "description varchar(1000)," +
                "price integer," +
                "img bytea," +
                "img_name varchar (200))";

        // language=SQL
        String createUserProductTable = "create table if not exists user_product (" +
                "id bigserial primary key ," +
                "user_id bigint references users_table(id)," +
                "product_id bigint references products_table(id)," +
                "is_processed boolean default false)";

        // language=SQL
        String createCommentTable = "create table if not exists comment(" +
                "id bigserial primary key ," +
                "product_id bigint references products_table," +
                "username varchar(20)," +
                "text varchar(1000))";



        try (Connection connection = PostgresConnectionProvider.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(createUserTable);
            statement.execute();

            statement = connection.prepareStatement(createProductTable);
            statement.execute();

            statement = connection.prepareStatement(createUserProductTable);
            statement.execute();

            statement = connection.prepareStatement(createCommentTable);
            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        this.session = se.getSession();
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
