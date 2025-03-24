package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.services.RolesService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/")
public class UsersController {
    private final UserService userService;
    private final RolesService rolesService;

    public UsersController(UserService userService, RolesService rolesService) {
        this.userService = userService;
        this.rolesService = rolesService;
    }

    @GetMapping("/login")
    public String loginByEmail() {
        return "login";
    }

    @GetMapping("/admin2")
    public String sayAdmin2(Principal principal, Model model) {
        String userName = principal.getName();
        User user = userService.getUserByName(userName);
        model.addAttribute("user", user);
        return "admin2";
    }

    @GetMapping("/user")
    public String sayHello(Principal principal, Model model) {
        String userName = principal.getName();
        User user = userService.getUserByName(userName);
        model.addAttribute("user", user);
        return "user";
    }

    @GetMapping("/admin")
    public String showUser(Principal principal, Model model) {
        String userName = principal.getName();
        User user1 = userService.getUserByName(userName);
        model.addAttribute("user1", user1);

        model.addAttribute("roles", rolesService.listRoles());

        List<User> user = userService.listUsers();
        model.addAttribute("users", user);
        return "users";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user, Model model) {
        model.addAttribute("roles", rolesService.listRoles());
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(@ModelAttribute User user, @RequestParam List<Integer> roles) {
        List<Role> roleList = rolesService.findRolesByIds(roles);
        userService.saveUser(user, roleList);
        return "redirect:/admin";
    }

    @PostMapping("/user-delete")
    public String deleteUser(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/user-update")
    public String сhangeUserId(@RequestParam("id") int id, Model model) {
        User user = userService.getUserId(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", rolesService.listRoles());
        return "user-update";
    }

    @PostMapping("/user-update")
    public String сhangeUser(@ModelAttribute User user, @RequestParam List<Integer> roles) {
        List<Role> roleList = rolesService.findRolesByIds(roles);
        userService.saveUser(user, roleList);
        return "redirect:/admin";
    }
}
