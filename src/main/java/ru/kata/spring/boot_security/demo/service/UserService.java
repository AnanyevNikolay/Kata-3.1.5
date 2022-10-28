package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.MyUser;

import java.util.List;

public interface UserService extends UserDetailsService {

    boolean addRole(Role role);
    Role getByNameRole(String name);
    List<Role> getListRoles();
    Role getByIdRole(Long id);
    List<Role> getListByRole(List<String> name);
    List<MyUser> getAllUsers();
    boolean save(MyUser user);
    MyUser getUser(int id);
    void update(int id, MyUser updateUser);
    void delete(int id);
}
