package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RolesService {

    List<Role> listRoles();

    Role saveRole(Role role);

    List<Role> findRolesByIds(List<Integer> roleIds);
}
