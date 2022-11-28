package service;

import jakarta.servlet.http.HttpSession;
import models.User;
import repositories.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository repository = new UserRepository();

    public void saveUser(User user) {
        repository.save(user);
    }

    public List<User> findAllUsers() {
        return repository.findAll();
    }

    public void updateUser(User user) {
        repository.update(user);
    }

    public static String getAuthUserLogin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return user.getLogin() + ",";
        } else {
            return "";
        }
    }

    public static String getBalance(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            return user.getBalance().toString() + " ₽";
        } else {
            return "";
        }
    }

}
