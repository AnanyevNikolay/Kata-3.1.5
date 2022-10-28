package ru.kata.spring.boot_security.demo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDaoImpl;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public class RoleServiceImpl implements RoleService {

    private RoleDaoImpl roleDao;

    public RoleServiceImpl(RoleDaoImpl roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public Role getByIdRole(Long id) {
        return roleDao.getByIdRole(id);
    }

    public List<Role> getListRoles() {
        return roleDao.getListRoles();
    }

    public Role getByNameRole(String name) {
        return roleDao.getByNameRole(name);
    }

    public List<Role> getListByName(List<String> name) {
        return roleDao.getListByName(name);
    }

    public boolean add(Role user) {
        return roleDao.add(user);
    }
}
