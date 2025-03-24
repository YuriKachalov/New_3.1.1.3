package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepositori;

import java.util.List;
import java.util.Optional;

@Service
public class RolesServiceImp implements RolesService {
    private final RoleRepositori roleRepositori;

    public RolesServiceImp(RoleRepositori roleRepositori) {
        this.roleRepositori = roleRepositori;
    }

    @Override
    @Transactional
    public List<Role> listRoles() {
        return roleRepositori.findAll();
    }

    @Override
    @Transactional
    public List<Role> findRolesByIds(List<Integer> roleIds) {
        return roleRepositori.findAllByIdIn(roleIds);
    }

    @Override
    @Transactional
    public Role saveRole(Role role) {
        Optional<Role> existingRoleOpt = roleRepositori.findById(role.getId());

        if (existingRoleOpt.isPresent()) {
            Role existingRole = existingRoleOpt.get();
            existingRole.setRole(role.getRole());
            return roleRepositori.save(role);
        } else {
            return roleRepositori.save(role);
        }
    }
}
