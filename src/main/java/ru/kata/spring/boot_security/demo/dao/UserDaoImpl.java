package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.MyUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MyUser> getAllUsers() {
        return entityManager.createQuery("SELECT u FROM MyUser u", MyUser.class).getResultList();
    }

    @Override
    public void save(MyUser user) {
        entityManager.persist(user);
    }

    @Override
    public MyUser getUser(int id) {
        TypedQuery<MyUser> query = entityManager.createQuery(
                "select u from MyUser u where u.id = :id", MyUser.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void update(int id, MyUser updateUser) {
        entityManager.merge(updateUser);
    }

    @Override
    public void delete(int id) {
        MyUser user = getUser(id);
        entityManager.remove(user);
    }

    public MyUser getByName(String name) {
        return (MyUser) entityManager.createQuery("select u from MyUser u join fetch u.roles where u.name = :name", MyUser.class)
                .setParameter("name", name);
    }
}
