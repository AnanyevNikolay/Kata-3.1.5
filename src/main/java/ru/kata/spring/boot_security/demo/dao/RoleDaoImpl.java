package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Role getByIdRole(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public List<Role> getListRoles() {
        return entityManager.createQuery("select r from Role r", Role.class).getResultList();
    }

    @Override
    public Role getByNameRole(String name) {
        return (Role) entityManager.createQuery("select r from Role r where r.role = :id", Role.class)
                .setParameter("id", name);
    }

    @Override
    public List<Role> getListByName(List<String> name) {
        return entityManager.createQuery("select r from Role r where r.role in (:name)", Role.class)
                .setParameter("name", name).getResultList();
    }

    @Override
    public boolean add(Role user) {
        entityManager.persist(user);
        return true;
    }
}
