package com.unla.grupo26.repositories;


import com.unla.grupo26.entities.User;
import com.unla.grupo26.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserSQLRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByEmail(String email);

    User findByUserRole(UserRole admin);
}
