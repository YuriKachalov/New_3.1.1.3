package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("Select u from User u left join fetch u.roleSet where u.name=:name")
    Optional<User> findByName(String name);

    @Query("Select u from User u left join fetch u.roleSet where u.email=:email")
    Optional<User> findByEmail(String email);
}
