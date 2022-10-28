package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDaoImpl;
import ru.kata.spring.boot_security.demo.dao.UserDaoImpl;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.MyUser;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final RoleDaoImpl roleDao;
    private final UserDaoImpl userDao;

    @Autowired
    public UserServiceImpl(RoleDaoImpl roleDao, UserDaoImpl userDao) {
        this.roleDao = roleDao;
        this.userDao = userDao;
    }

    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

    @Override
    public boolean addRole(Role role) {
        Role userPrim = roleDao.getByNameRole(role.getRole());
        if(userPrim != null) {return false;}
        roleDao.add(role);
        return true;
    }

    @Override
    public Role getByNameRole(String name) {
        return roleDao.getByNameRole(name);
    }

    @Override
    public List<Role> getListRoles() {
        return roleDao.getListRoles();
    }

    @Override
    public Role getByIdRole(Long id) {
        return roleDao.getByIdRole(id);
    }

    @Override
    public List<Role> getListByRole(List<String> name) {
        return roleDao.getListByName(name);
    }

    @Override
    public List<MyUser> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public boolean save(MyUser user) {
        MyUser userPrim = userDao.getUser(user.getId());
        if (userPrim != null) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        userDao.save(user);
        return true;
    }

    @Override
    public MyUser getUser(int id) {
        return userDao.getUser(id);
    }

    @Override
    @Transactional
    public void update(int id, MyUser updateUser) {
        userDao.update(id, updateUser);
    }

    @Override
    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }

    public MyUser getByName(String name) {
        return userDao.getByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = getByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                auth(user.getRoles()));
        return userDetails;
    }

    private Collection<? extends GrantedAuthority> auth(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole()))
                .collect(Collectors.toList());
    }
}