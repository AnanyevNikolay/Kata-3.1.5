package ru.kata.spring.boot_security.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class Init {

    @Autowired
    private UserServiceImpl userService;

    @PostConstruct
    public void init() {
        Role role1 = new Role("ROLE_ADMIN");
        Role role2 = new Role("ROLE_USER");

        userService.addRole(role1);
        userService.addRole(role2);

        List<Role> roleAdmin = new ArrayList<>();
        List<Role> roleUser = new ArrayList<>();
        List<Role> allRoles = new ArrayList<>();

        allRoles.add(role1);
        allRoles.add(role2);
        roleAdmin.add(role1);
        roleUser.add(role2);

        User user1 = new User("admin", "admin", "Moscow", "admin@gmail.com", roleAdmin);
        User user2 = new User("user", "user",  "Saint-Petersburg", "user@gmail.com", roleUser);
        User user3 = new User("user2", "user2", "Kaluga", "user2@gmail.com", roleUser);
        User user4 = new User("admin2", "admin2", "Kaluga", "admin2@gmail.com", allRoles);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);
    }
}
