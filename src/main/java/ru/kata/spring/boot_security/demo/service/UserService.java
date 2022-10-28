package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    boolean addRole(Role role);
    Role getByNameRole(String name);
    List<Role> getListRoles();
    Role getByIdRole(Long id);
    List<Role> getListByRole(List<String> name);
    List<User> getAllUsers();
    boolean save(User user);
    User getUser(int id);
    void update(int id, User updateUser);
    void delete(int id);
}
