package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.MyUser;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("admin")
public class AdminController {

    private final UserServiceImpl userService;

    @Autowired
    public AdminController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("create")
    public String createUserForm(MyUser user, Model model) {
        model.addAttribute("roleList", userService.getListRoles());
        return "create";
    }

    @PostMapping("create")
    public String createUser(MyUser user) {
        List<String> list1 = user.getRoles().stream().map(r -> r.getRole()).collect(Collectors.toList());
        List<Role> list2 = userService.getListByRole(list1);
        user.setRoles(list2);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("update/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        MyUser user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("roleList", userService.getListRoles());
        return "update";
    }

    @PostMapping("update")
    public String updateUser(MyUser user) {
        List<String> list1 = user.getRoles().stream().map(r -> r.getRole()).collect(Collectors.toList());
        List<Role> list2 = userService.getListByRole(list1);
        user.setRoles(list2);
        userService.update(user.getId(), user);
        return "redirect:/admin";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}