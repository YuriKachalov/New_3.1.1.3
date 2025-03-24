package ru.kata.spring.boot_security.demo.services;


import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.ArrayList;
import java.util.List;

@Service
public class FillingTableUsers implements ServletContextListener {
    private final UserService userService;

    public FillingTableUsers(UserService userService) {
        this.userService = userService;
    }

    public void fillingTableUsers() {
        List<Role> rolesAdmin = new ArrayList<>();
        rolesAdmin.add(new Role("ROLE_ADMIN"));
        userService.saveUser(new User("admin", "admin@mail.ru", "admin"), rolesAdmin);

        List<Role> rolesUser = new ArrayList<>();
        rolesUser.add(new Role("ROLE_USER"));
        userService.saveUser(new User("user", "user@mail.ru", "user"), rolesUser);

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        fillingTableUsers();
    }
}
