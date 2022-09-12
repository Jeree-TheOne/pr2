package ru.spring.pr2.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.spring.pr2.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}