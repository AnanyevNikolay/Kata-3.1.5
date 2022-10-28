package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.MyUser;

import java.util.List;

public interface UserDao {

    List<MyUser> getAllUsers();
    void save(MyUser user);
    MyUser getUser(int id);
    void update(int id, MyUser updateUser);
    void delete(int id);
    MyUser getByName(String name);
}
