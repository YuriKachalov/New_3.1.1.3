package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleRepositori extends JpaRepository<Role, Integer> {

    List<Role> findAllByIdIn(List<Integer> ids);

}
